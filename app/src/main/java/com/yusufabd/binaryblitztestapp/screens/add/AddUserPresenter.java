package com.yusufabd.binaryblitztestapp.screens.add;

import android.util.Patterns;

import com.yusufabd.data.repository.RepositoryProvider;
import com.yusufabd.domain.LoadingCallback;
import com.yusufabd.domain.UseCase;
import com.yusufabd.domain.User;

/**
 * Created by yusufabd on 9/9/2017.
 */

public class AddUserPresenter {

    private AddUserView view;
    private UseCase useCase;

    public AddUserPresenter(AddUserView view) {
        this.view = view;
        useCase = new UseCase(RepositoryProvider.getRepository());
    }

    public void addUser(String firstName, String lastName, String email, String avatarUrl){

        if (firstName.isEmpty()){
            view.showFirstNameError("Enter first name");
            return;
        }

        if (lastName.isEmpty()){
            view.showLastNameError("Enter last name");
            return;
        }

        if (email.isEmpty()){
            view.showEmailError("Enter email");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Enter valid email");
            return;
        }

        view.showProgress(true);
        useCase.addUser(firstName, lastName, email, avatarUrl, new LoadingCallback() {
            @Override
            public void onLoad(User object) {
                view.showMessage("User successfully added!");
                view.restartMain();
            }

            @Override
            public void onFail() {
                view.showMessage("An error occured");
            }
        });
    }
}
