package com.yusufabd.binaryblitztestapp.screens.edit;

import android.util.Patterns;

import com.yusufabd.data.repository.RepositoryProvider;
import com.yusufabd.domain.LoadingCallback;
import com.yusufabd.domain.UseCase;
import com.yusufabd.domain.User;

/**
 * Created by yusufabd on 9/9/2017.
 */

public class EditUserPresenter {

    private EditUserView view;
    private UseCase useCase;
    private int id = -1;

    public EditUserPresenter(EditUserView view) {
        this.view = view;
        useCase = new UseCase(RepositoryProvider.getRepository());
    }

    public void saveChanges(String firstName, String lastName, String email){

        if (id == -1){
            view.showMessage("Please, choose user to edit");
            return;
        }

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
        useCase.editUser(firstName, lastName, email, id, new LoadingCallback() {

            @Override
            public void onLoad(User object) {
                view.showProgress(false);
                view.showMessage("User info successfully changed!");
            }

            @Override
            public void onFail() {
                view.showProgress(false);
                view.showMessage("Error occured");
            }
        });
    }

    public void setId(int id) {
        this.id = id;
    }
}
