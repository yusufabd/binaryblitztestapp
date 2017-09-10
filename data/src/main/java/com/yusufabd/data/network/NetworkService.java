package com.yusufabd.data.network;

import com.yusufabd.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yusufabd on 9/8/2017.
 */

public interface NetworkService {

    @GET("users.json")
    Call<List<User>> getUsersList();

    @POST("users.json")
    Call<User> addUser(@Body User userObject);

    @PATCH("users/{id}.json")
    Call<User> editUser(@Body User userObject, @Path("id") int id);

}
