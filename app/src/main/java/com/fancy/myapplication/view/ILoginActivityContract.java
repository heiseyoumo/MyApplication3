package com.fancy.myapplication.view;

/**
 * @author pengkuanwang
 * @date 2019-07-19
 */
public interface ILoginActivityContract {
    interface IPresenter {
        void login(String phone, String pwd);
    }

    interface IModel {

    }

    interface IView {
        void onSuccess();

        void onFail();
    }
}
