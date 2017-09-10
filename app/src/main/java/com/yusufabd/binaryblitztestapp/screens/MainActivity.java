package com.yusufabd.binaryblitztestapp.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.yusufabd.binaryblitztestapp.R;
import com.yusufabd.binaryblitztestapp.RecyclerClickCallback;
import com.yusufabd.binaryblitztestapp.screens.edit.EditUserFragment;
import com.yusufabd.binaryblitztestapp.screens.list.UsersListFragment;
import com.yusufabd.domain.User;

public class MainActivity extends AppCompatActivity implements RecyclerClickCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getBoolean(R.bool.multiPaneMode)){
            return;
        }

        UsersListFragment list = new UsersListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, list).commit();
    }

    @Override
    public void onItemClick(User user) {
        if (getResources().getBoolean(R.bool.multiPaneMode)){
            EditUserFragment fragmentEdit = (EditUserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_edit);
            fragmentEdit.swapUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
        }else {
            Bundle bundle = new Bundle();
            bundle.putString(EditUserFragment.ARG_FIRST_NAME, user.getFirstName());
            bundle.putString(EditUserFragment.ARG_LAST_NAME, user.getLastName());
            bundle.putString(EditUserFragment.ARG_EMAIL, user.getEmail());
            bundle.putInt(EditUserFragment.ARG_ID, user.getId());

            EditUserFragment edit = new EditUserFragment();
            edit.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, edit);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
