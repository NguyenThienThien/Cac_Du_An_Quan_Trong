package com.longthph30891.note_mvp.view.interfaceView;

public interface LoginInterface {
    void loginSuccess();
    void loginFailure();
    void isValidUsername();
    void isValidPassword();
    void clearSetErrorUsername();
    void clearSetErrorPassword();
}
