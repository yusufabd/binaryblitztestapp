package com.yusufabd.binaryblitztestapp.screens.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yusufabd.binaryblitztestapp.R;
import com.yusufabd.binaryblitztestapp.screens.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddUserActivity extends AppCompatActivity implements AddUserView {

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

    private AddUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ButterKnife.bind(this);
        presenter = new AddUserPresenter(this);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addUser(inputFirstName.getText().toString(),
                        inputLastName.getText().toString(), inputEmail.getText().toString(), "");
            }
        });
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

    @Override
    public void restartMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
