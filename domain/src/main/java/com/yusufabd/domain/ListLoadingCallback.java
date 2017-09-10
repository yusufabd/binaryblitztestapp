package com.yusufabd.domain;

import java.util.List;

/**
 * Created by yusufabd on 9/8/2017.
 */

public interface ListLoadingCallback {
    void onLoad(List<User> list);
    void onFail();
}
