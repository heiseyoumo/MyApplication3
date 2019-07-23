package com.fancy.myapplication.view;

import dagger.Module;
import dagger.Provides;

/**
 * @author pengkuanwang
 * @date 2019-07-19
 */
@Module
public class LoginActivityModule {
    private ILoginActivityContract.IView mView;

    public LoginActivityModule(ILoginActivityContract.IView view) {
        mView = view;
    }

    @Provides
    public ILoginActivityContract.IPresenter providerPresenter() {
        return new LoginActivityPresenter();
    }

    public void loginFromNetWork() {
        mView.onSuccess();
    }
}
