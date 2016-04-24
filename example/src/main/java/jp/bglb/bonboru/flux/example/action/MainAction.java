package jp.bglb.bonboru.flux.example.action;

import jp.bglb.bonboru.flux.action.Action;
import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.example.action.data.MainActionResult;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class MainAction implements Action<MainActionResult, ActionTypes> {

  @Override public ActionData<MainActionResult, ActionTypes> execute() {
    return new ActionData<>(new MainActionResult("text"), ActionTypes.UPDATE_TEXT);
  }
}
