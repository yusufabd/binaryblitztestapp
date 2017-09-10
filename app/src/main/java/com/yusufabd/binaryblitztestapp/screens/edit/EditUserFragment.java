package com.yusufabd.binaryblitztestapp.screens.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yusufabd.binaryblitztestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yusufabd on 9/9/2017.
 */

public class EditUserFragment extends Fragment implements EditUserView{

    public static final String ARG_FIRST_NAME = "first_name";
    public static final String ARG_LAST_NAME = "last_name";
    public static final String ARG_EMAIL = "email";
    public static final String ARG_ID = "id";

    @BindView(R.id.parent)
    LinearLayout parentLayout;

    @BindView(R.id.input_first_name)
    EditText inputFirstName;

    @BindView(R.id.input_last_name)
    EditText inputLastName;

    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.button_save)
    Button buttonSave;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private EditUserPresenter presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null){
            String firstName = args.getString(ARG_FIRST_NAME);
            String lastName = args.getString(ARG_LAST_NAME);
            String email = args.getString(ARG_EMAIL);
            int id = args.getInt(ARG_ID);
            swapUser(firstName, lastName, email, id);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this, view);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
            }
        });
        presenter = new EditUserPresenter(this);
        return view;
    }

    public void swapUser(String firstName, String lastName, String email, int id){
        presenter.setId(id);
        inputFirstName.setText(firstName);
        inputLastName.setText(lastName);
        inputEmail.setText(email);
    }

    public void applyChanges(){
        presenter.saveChanges(inputFirstName.getText().toString(),
                inputLastName.getText().toString(), inputEmail.getText().toString());
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showMessage(String error) {
        Snackbar.make(parentLayout, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFirstNameError(String error) {
        inputFirstName.setError(error);
    }

    @Override
    public void showLastNameError(String error) {
        inputLastName.setError(error);
    }

    @Override
    public void showEmailError(String error) {
        inputEmail.setError(error);
    }
}
