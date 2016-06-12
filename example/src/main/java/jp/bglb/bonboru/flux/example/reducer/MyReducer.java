package jp.bglb.bonboru.flux.example.reducer;

import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.reducer.Reducer;

/**
 * Created by tmasuda on 2016/04/24.
 */
public abstract class MyReducer<T> extends Reducer<T, ActionTypes> {
}
