package com.github.example.mvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.example.R;
import com.github.example.datamodel.DataModel;
import com.github.example.model.User;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MvvmActivity extends AppCompatActivity {

    private static final String TAG = MvvmActivity.class.getSimpleName();

    @NonNull
    private CompositeDisposable disposable;

    @NonNull
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);

        viewModel = new ViewModel(new DataModel());

        findViewById(R.id.buttonSearch).setOnClickListener(view -> {
            viewModel.getUser("iamdeveloper-lopez")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "onSubscribe()");
                            disposable.add(d);
                        }

                        @Override
                        public void onNext(User user) {
                            Log.d(TAG, "onNext() -> " + user);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError() -> ", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete()");
                        }
                    });
            ;
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disposable.dispose();
    }
}
