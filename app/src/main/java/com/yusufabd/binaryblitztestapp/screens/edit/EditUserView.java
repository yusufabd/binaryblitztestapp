package com.yusufabd.binaryblitztestapp.screens.edit;

/**
 * Created by yusufabd on 9/9/2017.
 */

public interface EditUserView {
    void showProgress(boolean show);
    void showMessage(String error);
    void showFirstNameError(String error);
    void showLastNameError(String error);
    void showEmailError(String error);
}
