package com.yusufabd.domain;

/**
 * Created by yusufabd on 9/9/2017.
 */

public interface LoadingCallback {
    void onLoad(User object);
    void onFail();
}
