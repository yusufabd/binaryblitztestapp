package com.yusufabd.binaryblitztestapp.screens.list;

import com.yusufabd.domain.User;

/**
 * Created by yusufabd on 9/8/2017.
 */

public interface UsersListView {
    void showProgress(boolean show);
    void showMessage(String message);
    void showUsersList(UsersListAdapter adapter);
    void setFirstUserSelected(User user);
}
