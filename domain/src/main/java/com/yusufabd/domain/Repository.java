package com.yusufabd.domain;

public interface Repository {
    void getUsersList(ListLoadingCallback callback);
    void editUser(User user, LoadingCallback callback);
    void addUser(User user, LoadingCallback callback);
}
