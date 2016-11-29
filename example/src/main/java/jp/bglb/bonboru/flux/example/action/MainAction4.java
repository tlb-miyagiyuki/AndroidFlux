package jp.bglb.bonboru.flux.example.action;

import jp.bglb.bonboru.flux.action.Action;
import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.example.dto.MainDataBuilder;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class MainAction4 implements Action<MainData, ActionTypes> {

  @Override public ActionData<MainData, ActionTypes> execute() throws InterruptedException {
    Thread.sleep(5000);
    return new ActionData<>(new MainDataBuilder().setMessage("from cache").setError(null).build(),
        ActionTypes.UPDATE_MESSAGE);
  }
}
