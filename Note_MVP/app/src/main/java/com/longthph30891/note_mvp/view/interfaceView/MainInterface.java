package com.longthph30891.note_mvp.view.interfaceView;

import android.content.Context;

public interface MainInterface {
    Context getContext();
    void addSuccess();
    void addFailure();
    void updateSuccess();
    void updateFailure();
    void deleteSuccess();
    void deleteFailure();
}
