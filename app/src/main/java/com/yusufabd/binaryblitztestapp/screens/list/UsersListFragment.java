package com.yusufabd.binaryblitztestapp.screens.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yusufabd.binaryblitztestapp.R;
import com.yusufabd.binaryblitztestapp.RecyclerClickCallback;
import com.yusufabd.binaryblitztestapp.screens.add.AddUserActivity;
import com.yusufabd.domain.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yusufabd on 9/9/2017.
 */

public class UsersListFragment extends Fragment implements UsersListView{

    @BindView(R.id.parent)
    CoordinatorLayout parentLayout;

    @BindView(R.id.recycler_user_list)
    RecyclerView recyclerUsersList;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private RecyclerClickCallback clickCallback;
    private UsersListPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecyclerClickCallback){
            clickCallback = (RecyclerClickCallback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        presenter = new UsersListPresenter(this);
        presenter.loadList();
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUsersList(UsersListAdapter adapter) {
        adapter.setClickCallback(clickCallback);
        recyclerUsersList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerUsersList.setAdapter(adapter);
    }

    @Override
    public void setFirstUserSelected(User user) {
        clickCallback.onItemClick(user);
    }
}
