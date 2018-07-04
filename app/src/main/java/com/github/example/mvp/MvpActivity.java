package com.github.example.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.example.R;
import com.github.example.datamodel.DataModel;
import com.github.example.model.Repo;
import com.github.example.model.User;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MvpActivity extends AppCompatActivity implements IView {

    private static final String TAG = MvpActivity.class.getSimpleName();

    @NonNull
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        presenter = new Presenter(new DataModel(), this);

        findViewById(R.id.buttonSearch).setOnClickListener(view -> {
            String text = ((EditText) (findViewById(R.id.editText))).getText().toString();
            presenter.getUser(text);
            presenter.getUserRepos(text);
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind();
        presenter.add(RxTextView.textChanges(findViewById(R.id.editText))
                .filter(s -> s.length() > 2)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    presenter.getUser(s.toString());
                    presenter.getUserRepos(s.toString());
                }));
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbind();
    }

    @Override
    public void setUser(User user) {
        Log.d(TAG, "setUser: " + user);
        findViewById(R.id.buttonGo).setVisibility(View.VISIBLE);
    }

    @Override
    public void setRepos(List<Repo> repos) {
        Log.d(TAG, "setRepos: " + repos);
        findViewById(R.id.buttonGo).setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(Throwable throwable) {
        Log.d(TAG, "onFailed: " + throwable);
        findViewById(R.id.buttonGo).setVisibility(View.GONE);
    }
}
