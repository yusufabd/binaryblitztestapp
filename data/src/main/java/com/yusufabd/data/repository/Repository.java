package com.yusufabd.data.repository;

import com.yusufabd.data.model.User;
import com.yusufabd.data.network.NetworkFactory;
import com.yusufabd.domain.ListLoadingCallback;
import com.yusufabd.domain.LoadingCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yusufabd on 9/8/2017.
 */

public class Repository implements com.yusufabd.domain.Repository {

    @Override
    public void getUsersList(final ListLoadingCallback callback) {
        NetworkFactory.getService().getUsersList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> body = response.body();
                    List<com.yusufabd.domain.User> list = new ArrayList<>();
                    for (User item : body) {
                        list.add(new com.yusufabd.domain.User(item.getId(), item.getFirstName(),
                                item.getLastName(), item.getEmail(), item.getAvatarUrl()));
                    }
                    callback.onLoad(list);
                }else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFail();
            }
        });
    }

    @Override
    public void editUser(com.yusufabd.domain.User user, final LoadingCallback callback) {
        User userObject = new User();
        userObject.setFirstName(user.getFirstName());
        userObject.setLastName(user.getLastName());
        userObject.setEmail(user.getEmail());
        userObject.setAvatarUrl(user.getAvatarUrl());
        NetworkFactory.getService().editUser(userObject, user.getId()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User body = response.body();
                    callback.onLoad(new com.yusufabd.domain.User(body.getId(), body.getFirstName(), body.getLastName(), body.getEmail(), body.getAvatarUrl()));
                }else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFail();
            }
        });
    }

    @Override
    public void addUser(com.yusufabd.domain.User user, final LoadingCallback callback) {
        User userObject = new User();
        userObject.setFirstName(user.getFirstName());
        userObject.setLastName(user.getLastName());
        userObject.setEmail(user.getEmail());
        userObject.setAvatarUrl(user.getAvatarUrl());
        NetworkFactory.getService().addUser(userObject).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User body = response.body();
                    callback.onLoad(new com.yusufabd.domain.User(body.getId(), body.getFirstName(), body.getLastName(), body.getEmail(), body.getAvatarUrl()));
                }else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFail();
            }
        });
    }

}
