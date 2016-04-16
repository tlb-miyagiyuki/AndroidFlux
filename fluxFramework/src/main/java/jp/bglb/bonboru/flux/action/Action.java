package jp.bglb.bonboru.flux.action;

/**
 * Created by tmasuda on 2016/04/14.
 * ViewLayerで発生するイベントに対応する処理ロジックを定義するinterface
 */
public interface Action<T, E extends ActionType> {

  ActionData<T, E> execute();

}
