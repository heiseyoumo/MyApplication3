package com.fancy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fancy.myapplication.view.DaggerILoginActivityComponent;
import com.fancy.myapplication.view.ILoginActivityContract;
import com.fancy.myapplication.view.LoginActivityModule;

import javax.inject.Inject;

/**
 * @author pengkuanwang
 * @date 2019-07-19
 */
public class LoginActivity extends AppCompatActivity implements ILoginActivityContract.IView {
    @Inject
    ILoginActivityContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerILoginActivityComponent.builder()
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);
        mPresenter.login("15316161570", "1234556");
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}
