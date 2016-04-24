package jp.bglb.bonboru.flux.example.reducer;

import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.action.data.MainActionResult;
import jp.bglb.bonboru.flux.example.dto.MainData;

/**
 * Created by tmasuda on 2016/04/16.
 */
public class MainReducer extends MyReducer<MainData, MainActionResult> {

  @Override
  public MainData received(MainData state, ActionData<MainActionResult, ActionTypes> action) {
    switch (action.type) {
      case UPDATE_TEXT:
        return new MainData(action.data.text);

      default:
        return state;
    }
  }

  @Override public MainData onError(MainData state, Throwable error) {
    return null;
  }
}
