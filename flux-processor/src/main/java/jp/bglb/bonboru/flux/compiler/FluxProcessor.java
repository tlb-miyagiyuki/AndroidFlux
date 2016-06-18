package jp.bglb.bonboru.flux.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableClass;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableField;
import jp.bglb.bonboru.flux.compiler.type.CheckType;
import jp.bglb.bonboru.flux.store.Store;
import rx.subjects.BehaviorSubject;

/**
 * AnnotationされているDataクラスからViewの制御を行うためのStateクラスを作る
 */
@AutoService(Processor.class) @SupportedAnnotationTypes({
    "jp.bglb.bonboru.flux.compiler.annotation.ObservableClass",
    "jp.bglb.bonboru.flux.compiler.annotation.ObservableField"
}) @SupportedSourceVersion(SourceVersion.RELEASE_7) public class FluxProcessor
    extends AbstractProcessor {

  Filer filer;

  @Override public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    List<Element> dataClasses =
        new ArrayList<>(roundEnv.getElementsAnnotatedWith(ObservableClass.class));
    for (Element element : dataClasses) {
      final String stateClassName = element.getSimpleName().toString() + "Store";
      TypeSpec.Builder classBuilder =
          TypeSpec.classBuilder(stateClassName).addModifiers(Modifier.PUBLIC, Modifier.FINAL);

      MethodSpec.Builder constructorBuilder =
          MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);

      ClassName dataName = ClassName.bestGuess(element.asType().toString());
      ClassName superClass = ClassName.get(Store.class);
      classBuilder.superclass(ParameterizedTypeName.get(superClass, dataName));
      constructorBuilder.addStatement("this.$N = new $T()", "data", dataName);

      // DataクラスのBuilderクラス
      final String builderName = element.getSimpleName().toString() + "Builder";
      TypeSpec.Builder dataBuilder = TypeSpec.classBuilder(builderName)
          .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
          .addField(FieldSpec.builder(dataName, "data", Modifier.PRIVATE).build())
          .addMethod(MethodSpec.constructorBuilder()
              .addModifiers(Modifier.PUBLIC)
              .addStatement("this.$N = new $T()", "data", dataName)
              .build())
          .addMethod(MethodSpec.methodBuilder("build")
              .addModifiers(Modifier.PUBLIC)
              .addStatement("return this.$N", "data")
              .returns(dataName)
              .build());

      // 自分の持ってるデータと渡されるデータの差分を検出して反映するメソッド
      MethodSpec.Builder onChangeBuilder = MethodSpec.methodBuilder("setData")
          .addAnnotation(Override.class)
          .addModifiers(Modifier.PUBLIC)
          .addParameter(dataName, "data");

      for (Element member : element.getEnclosedElements()) {
        ObservableField annotation = member.getAnnotation(ObservableField.class);
        if (annotation != null) {
          ClassName behavior = ClassName.get(BehaviorSubject.class);
          TypeMirror typeMirror = member.asType();
          TypeName behaviorOfType =
              ParameterizedTypeName.get(behavior, ParameterizedTypeName.get(typeMirror));

          try {
            final String field = member.getSimpleName().toString();
            final String setter = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
            final String getter =
                "get" + field.substring(0, 1).toUpperCase() + field.substring(1) + "()";
            FieldSpec fieldSpec = FieldSpec.builder(behaviorOfType, field)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .build();
            classBuilder.addField(fieldSpec);
            if (annotation.hasDefaultValue()) {
              constructorBuilder.addStatement("this.$N = $T.create(this.$N." + getter + ")", field,
                  BehaviorSubject.class, "data");
            } else {
              constructorBuilder.addStatement("this.$N = $T.create()", field,
                  BehaviorSubject.class);
            }

            // 各fieldの違いを確認して反映するようにする
            CheckType checkType = annotation.checkType();
            StringBuilder controlFlow = new StringBuilder();
            Object[] args;
            switch (checkType) {
              case NULLABLE:
                args = new Object[3];
                controlFlow.append("if (")
                    .append(
                        String.format("(this.$N.%s == null || !this.$N.%s.equals($N.%s))", getter,
                            getter, getter))
                    .append(")");
                break;

              case PASS:
                args = new Object[0];
                controlFlow.append("if (1 == 1)");
                break;

              case STRICT:
              default:
                args = new Object[4];
                controlFlow.append("if (")
                    .append(String.format("$N.%s != null", getter))
                    .append(" && ")
                    .append(
                        String.format("(this.$N.%s == null || !this.$N.%s.equals($N.%s))", getter,
                            getter, getter))
                    .append(")");
                break;
            }
            for (int i = 0; i < args.length; i++) {
              args[i] = "data";
            }
            onChangeBuilder.beginControlFlow(controlFlow.toString(), args)
                .addStatement("this.$N." + setter + "($N." + getter + ")", "data", "data")
                .addStatement("this.$N.onNext($N." + getter + ")", field, "data")
                .endControlFlow();

            // Builderのsetter
            MethodSpec.Builder setterBuilder = MethodSpec.methodBuilder(
                "set" + field.substring(0, 1).toUpperCase() + field.substring(1))
                .addModifiers(Modifier.PUBLIC);
            setterBuilder.addParameter(ParameterizedTypeName.get(typeMirror), field);
            setterBuilder.addStatement("this.$N." + setter + "($N)", "data", field)
                .addStatement("return this")
                .returns(ClassName.get(dataName.packageName(), builderName));
            dataBuilder.addMethod(setterBuilder.build());
          } catch (MirroredTypeException t) {
          }
        }
      }
      classBuilder.addMethod(constructorBuilder.build()).addMethod(onChangeBuilder.build());

      try {
        JavaFile.builder(dataName.packageName(), classBuilder.build()).build().writeTo(filer);
        JavaFile.builder(dataName.packageName(), dataBuilder.build()).build().writeTo(filer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return true;
  }
}