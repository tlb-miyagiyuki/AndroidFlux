package jp.bglb.bonboru.flux.compiler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by tmasuda on 2016/05/20.
 */
@Target(ElementType.FIELD)
public @interface ObservableField {
  boolean isNullable() default false;
  boolean hasDefaultValue() default false;
}
