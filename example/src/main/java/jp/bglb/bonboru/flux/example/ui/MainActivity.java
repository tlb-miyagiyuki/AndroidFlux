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
import jp.bglb.bonboru.flux.example.action.data.MainActionResult;
import jp.bglb.bonboru.flux.example.dto.MainData;
import jp.bglb.bonboru.flux.example.dto.MainDataState;
import jp.bglb.bonboru.flux.example.reducer.MainReducer;
import jp.bglb.bonboru.flux.store.Store;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class MainActivity extends AppCompatActivity {

    MainDataState state = new MainDataState();

    Store<MainData> store = new Store<>();

    MainReducer mainReducer = new MainReducer();

    MainAction action = new MainAction();

    Dispatcher<MainData, ActionTypes, MainActionResult> dispatcher;

    TextView message;

    Button button;

    CompositeSubscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.message);
        button = (Button) findViewById(R.id.button);
        dispatcher = new Dispatcher<>(mainReducer, store);
        store.addListener(state);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatcher.dispatch(action);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscription = new CompositeSubscription(
                state.message.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String str) {
                                Log.d("Debug", "changed message");
                                message.setText(str);
                            }
                        }),
                state.text.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String str) {
                                Log.d("Debug", "changed text");
                                message.setText(str);
                            }
                        })
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        store.removeListener(state);
    }
}
