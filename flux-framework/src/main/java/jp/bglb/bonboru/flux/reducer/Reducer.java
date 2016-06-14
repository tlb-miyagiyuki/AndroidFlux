package jp.bglb.bonboru.flux.reducer;

import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.action.ActionType;

/**
 * @param <T> state
 * @param <E> action
 */
public abstract class Reducer<T, E extends ActionType> {

  /**
   * @param state Storeが保持しているstateが渡される
   * @param action Actionの結果
   * @return Actionの処理内容を反映したstate
   */
  public abstract T received(T state, ActionData<T, E> action);

  /**
   * @param state Storeが保持しているstateが渡される
   * @param error action実行時のerrorが渡される
   * @return Actionの処理内容を反映したstate
   */
  public abstract T onError(T state, Throwable error);
}
