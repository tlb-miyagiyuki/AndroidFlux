package jp.bglb.bonboru.flux.store;

/**
 * Created by tmasuda on 2016/04/14.
 * Storeが持つデータに変更が合った場合の通知先が実装すべきinterface.
 */
public interface StoreChangeListener<T> {

  void onChange(T data);

}
