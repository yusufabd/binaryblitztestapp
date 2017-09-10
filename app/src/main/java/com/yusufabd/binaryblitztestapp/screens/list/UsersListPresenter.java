package com.yusufabd.binaryblitztestapp.screens.list;

import com.yusufabd.data.repository.RepositoryProvider;
import com.yusufabd.domain.ListLoadingCallback;
import com.yusufabd.domain.UseCase;
import com.yusufabd.domain.User;

import java.util.List;

/**
 * Created by yusufabd on 9/8/2017.
 */

public class UsersListPresenter {

    private UsersListView view;
    private UseCase useCase;

    public UsersListPresenter(UsersListView view) {
        this.view = view;
        useCase = new UseCase(RepositoryProvider.getRepository());
    }

    public void loadList(){
        useCase.getFilmsList(new ListLoadingCallback() {
            @Override
            public void onLoad(List<User> list) {
                UsersListAdapter adapter = new UsersListAdapter(list);
                view.showUsersList(adapter);
                view.showProgress(false);
                //view.setFirstUserSelected(list.get(0));
            }

            @Override
            public void onFail() {
                view.showMessage("An error occured");
            }
        });
    }
}
