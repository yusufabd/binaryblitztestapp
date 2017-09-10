package com.yusufabd.binaryblitztestapp.screens.add;

/**
 * Created by yusufabd on 9/9/2017.
 */

public interface AddUserView {
    void showProgress(boolean show);
    void showMessage(String error);
    void showFirstNameError(String error);
    void showLastNameError(String error);
    void showEmailError(String error);

    void restartMain();
}
