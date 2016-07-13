package jp.bglb.bonboru.flux.example.middleware;

import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.example.dto.MainDataBuilder;
import jp.bglb.bonboru.flux.middleware.Middleware;
import jp.bglb.bonboru.flux.store.Store;

/**
 * Created by Tetsuya Masuda on 2016/07/01.
 */

public class LoadMiddleware extends Middleware<MainData, ActionTypes> {

  @Override public void before(Store<MainData> store) {
    MainData data = store.getData();
    store.setData(new MainDataBuilder().setProgressBarVisibility(true)
        .setError(data.error)
        .setMessage(data.message)
        .setMessages(data.messages)
        .build());
  }
}
