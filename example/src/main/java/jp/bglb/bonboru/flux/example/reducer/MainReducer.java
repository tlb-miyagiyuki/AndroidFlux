package jp.bglb.bonboru.flux.example.reducer;

import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.example.dto.MainDataBuilder;

/**
 * Created by tmasuda on 2016/04/16.
 */
public class MainReducer extends MyReducer<MainData> {

  @Override public MainData received(MainData state, ActionData<MainData, ActionTypes> action) {
    switch (action.type) {
      case UPDATE_TEXT:
        return new MainDataBuilder().setMessage(action.data.message)
            .setError(action.data.error)
            .build();

      case UPDATE_MESSAGE:
        return new MainDataBuilder().setMessage(action.data.message)
            .setError(action.data.error)
            .build();

      case UPDATE_ERROR:
        return new MainDataBuilder().setError(action.data.error).build();

      default:
        return state;
    }
  }

  @Override public MainData onError(MainData state, Throwable error) {
    return null;
  }
}
