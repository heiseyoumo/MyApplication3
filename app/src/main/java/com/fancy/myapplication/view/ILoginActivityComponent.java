package com.fancy.myapplication.view;

import com.fancy.myapplication.LoginActivity;

import dagger.Component;

/**
 * @author pengkuanwang
 * @date 2019-07-19
 */
@Component(modules = LoginActivityModule.class)
public interface ILoginActivityComponent {
    void inject(LoginActivity view);
}
