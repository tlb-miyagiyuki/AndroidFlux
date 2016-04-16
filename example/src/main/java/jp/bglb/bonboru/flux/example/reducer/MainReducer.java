package jp.bglb.bonboru.flux.example.reducer;

import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.reducer.Reducer;

/**
 * Created by tmasuda on 2016/04/16.
 */
public class MainReducer extends Reducer<MainData, ActionTypes> {

  private MainData state = new MainData("Hello, World");

  @Override public MainData received(MainData data, ActionTypes action) {
    switch (action) {
      case UPDATE_TEXT:
        return data;

      default:
        return state;
    }
  }

  @Override public MainData onError(Throwable error) {
    return null;
  }
}
