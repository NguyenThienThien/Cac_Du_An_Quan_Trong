package com.longthph30891.note_mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.longthph30891.note_mvp.database.LoginDao;
import com.longthph30891.note_mvp.view.interfaceView.LoginInterface;

public class LoginPresenter {
    private LoginInterface loginInterface;
    private LoginDao loginDao;
    public LoginPresenter(LoginInterface loginInterface){
        this.loginInterface = loginInterface;
    }
    public void login(Context context, String username, String password){
        loginDao = new LoginDao(context);

        if (TextUtils.isEmpty(username)) {
            loginInterface.isValidUsername();
        } else {
            loginInterface.clearSetErrorUsername();
        }

        if (TextUtils.isEmpty(password)) {
            loginInterface.isValidPassword();
        } else {
            loginInterface.clearSetErrorPassword();
        }

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            if (!loginDao.checkAcount(username, password)) {
                loginInterface.loginFailure();
            } else {
                loginInterface.loginSuccess();
            }
        }
    }
}
