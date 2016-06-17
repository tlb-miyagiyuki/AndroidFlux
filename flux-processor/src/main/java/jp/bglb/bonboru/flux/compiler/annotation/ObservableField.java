package jp.bglb.bonboru.flux.compiler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import jp.bglb.bonboru.flux.compiler.type.CheckType;

/**
 * Created by tmasuda on 2016/05/20.
 */
@Target(ElementType.FIELD)
public @interface ObservableField {

  /**
   * 値のnullを許可し、null値でも伝搬させるようにする
   */
  CheckType checkType() default CheckType.STRICT;

  /**
   * Storeの初期値を設定するかどうか
   */
  boolean hasDefaultValue() default false;
}
