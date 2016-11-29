package jp.bglb.bonboru.flux.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import jp.bglb.bonboru.flux.Dispatcher;
import jp.bglb.bonboru.flux.example.MyDispatcher;
import jp.bglb.bonboru.flux.example.R;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.example.action.MainAction;
import jp.bglb.bonboru.flux.example.action.MainAction2;
import jp.bglb.bonboru.flux.example.action.MainAction3;
import jp.bglb.bonboru.flux.example.action.MainAction4;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.example.dto.MainDataStore;
import jp.bglb.bonboru.flux.example.middleware.LoadMiddleware;
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

  MainAction3 action3 = new MainAction3();

  MainAction4 action4 = new MainAction4();

  Dispatcher<MainData, ActionTypes> dispatcher;

  TextView message;

  Button button;
  Button button2;
  Button button3;
  ProgressBar progressBar;

  CompositeSubscription subscription;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    message = (TextView) findViewById(R.id.message);
    button = (Button) findViewById(R.id.button);
    button2 = (Button) findViewById(R.id.button2);
    button3 = (Button) findViewById(R.id.button3);
    progressBar = (ProgressBar) findViewById(R.id.progress);
    dispatcher = new MyDispatcher<>(mainReducer, store, new LoadMiddleware());
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dispatcher.dispatch(action4, action);
      }
    });
    button2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dispatcher.dispatch(action2);
      }
    });
    button3.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dispatcher.dispatch(action3);
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
        }), store.error.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
          @Override public void call(String str) {
            Log.d("Debug", "changed error");
            if (str == null) {
              message.setVisibility(View.VISIBLE);
            } else {
              message.setVisibility(View.GONE);
              Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
          }
        }), store.progressBarVisibility.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Boolean>() {
          @Override public void call(Boolean aBoolean) {
            progressBar.setVisibility(aBoolean ? View.VISIBLE : View.INVISIBLE);
          }
        }));
  }

  @Override protected void onPause() {
    super.onPause();
    subscription.unsubscribe();
  }
}
