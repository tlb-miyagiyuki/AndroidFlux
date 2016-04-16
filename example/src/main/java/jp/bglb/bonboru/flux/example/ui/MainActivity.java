package jp.bglb.bonboru.flux.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import jp.bglb.bonboru.flux.Dispatcher;
import jp.bglb.bonboru.flux.component.Component;
import jp.bglb.bonboru.flux.example.reducer.MainReducer;
import jp.bglb.bonboru.flux.example.R;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.action.MainAction;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.store.Store;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class MainActivity extends AppCompatActivity {

  Component<MainData> component = new Component<>();

  Store<MainData> store = new Store<>();

  MainReducer mainReducer = new MainReducer();

  MainAction action = new MainAction();

  Dispatcher<MainData, ActionTypes> dispatcher = new Dispatcher<>();

  TextView message;

  Button button;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    message = (TextView) findViewById(R.id.message);
    button = (Button) findViewById(R.id.button);
    store.addListener(component);
    component.subscribe()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<MainData>() {
          @Override public void call(MainData state) {
            message.setText(state.message);
          }
        });
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dispatcher.dispatch(action, mainReducer, store);
      }
    });
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    store.removeListener(component);
  }
}
