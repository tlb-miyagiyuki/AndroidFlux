package jp.bglb.bonboru.flux.compiler;

import jp.bglb.bonboru.flux.compiler.FluxProcessor;
import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;
import org.junit.Test;

/**
 * Created by tetsuya on 2016/06/17.
 */
public class ProcessorTest {

  @Test public void test_generateStore() {
    Truth.assert_()
        .about(JavaSourceSubjectFactory.javaSource())
        .that(JavaFileObjects.forResource("TestData.java"))
        .processedWith(new FluxProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(JavaFileObjects.forResource("TestDataStore.java"));
  }
}
