package jp.bglb.bonboru.flux.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.bglb.bonboru.flux.Dispatcher;
import jp.bglb.bonboru.flux.example.R;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.action.MainAction;
import jp.bglb.bonboru.flux.example.action.MainAction2;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.example.dto.MainDataStore;
import jp.bglb.bonboru.flux.example.reducer.MainReducer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class MainActivity extends AppCompatActivity {

  MainDataStore store = new MainDataStore();

  MainReducer mainReducer = new MainReducer();

  MainAction action = new MainAction();

  MainAction2 action2 = new MainAction2();

  Dispatcher<MainData, ActionTypes> dispatcher;

  TextView message;

  Button button;
  Button button2;

  CompositeSubscription subscription;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    message = (TextView) findViewById(R.id.message);
    button = (Button) findViewById(R.id.button);
    button2 = (Button) findViewById(R.id.button2);
    dispatcher = new Dispatcher<>(mainReducer, store);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dispatcher.dispatch(action);
      }
    });
    button2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dispatcher.dispatch(action2);
      }
    });
  }

  @Override protected void onResume() {
    super.onResume();
    subscription = new CompositeSubscription(store.message.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
          @Override public void call(String str) {
            Log.d("Debug", "changed message");
            message.setText(str);
          }
        }));
  }

  @Override protected void onPause() {
    super.onPause();
    subscription.unsubscribe();
  }
}
