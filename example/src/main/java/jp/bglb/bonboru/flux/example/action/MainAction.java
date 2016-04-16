package jp.bglb.bonboru.flux.example.action;

import jp.bglb.bonboru.flux.action.Action;
import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.example.dto.MainData;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class MainAction implements Action<MainData, ActionTypes> {

  @Override public ActionData<MainData, ActionTypes> execute() {
    return new ActionData<>(new MainData("text"), ActionTypes.UPDATE_TEXT);
  }
}
