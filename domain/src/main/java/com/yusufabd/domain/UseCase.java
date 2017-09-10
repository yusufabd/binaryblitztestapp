package com.yusufabd.domain;

/**
 * Created by yusufabd on 9/8/2017.
 */

public class UseCase {

    private Repository repository;

    public UseCase(Repository repository) {
        this.repository = repository;
    }

    public void getFilmsList(ListLoadingCallback callback){
        repository.getUsersList(callback);
    }

    public void editUser(String firstName, String lastName, String email, int id, LoadingCallback callback){
        User user = new User(id, firstName, lastName, email);
        repository.editUser(user, callback);
    }

    public void addUser(String firstName, String lastName, String email, String avatarUrl, LoadingCallback callback){
        User user = new User(firstName, lastName, email, avatarUrl);
        repository.addUser(user, callback);
    }

}
