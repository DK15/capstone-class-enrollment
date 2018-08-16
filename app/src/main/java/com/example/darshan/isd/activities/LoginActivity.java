package com.example.darshan.isd.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darshan.isd.R;
import com.example.darshan.isd.utils.CheckConnection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button login_button;
    @BindView(R.id.input_email)
    EditText email_edit;

    @BindView(R.id.input_password)
    EditText password_edit;
    @BindView(R.id.link_signup)
    TextView signup_tv;

    @BindView(R.id.progressBar)
    ProgressBar loginProgressBar;
    private FirebaseAuth auth;
    String email;
    String password;
    @BindView(R.id.link_resetpass)
    TextView reset_tv;
    @BindView(R.id.profile_image)
    ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.isdm_logo1).into(logo);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        // Get auth instance of firebase

        auth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    CheckConnection check = new CheckConnection(view.getContext());
                    check.execute();
                    validateLogin();
                }
        });

        signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        reset_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resetIntent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                startActivity(resetIntent);
            }
        });

    }

    /*
    This method validates user input on login screen. Displays error message if fields are empty.
     */

    private void validateLogin() {
        email = email_edit.getText().toString();
        password = password_edit.getText().toString();

        if ((TextUtils.isEmpty(email)) && (TextUtils.isEmpty(password))) {
            email_edit.setError(getString(R.string.login_val1));
            password_edit.setError(getString(R.string.login_val2));

        } else if (email.length() == 0) {
            email_edit.setError(getString(R.string.login_val1));

        } else if (password.length() == 0) {
            password_edit.setError(getString(R.string.login_val2));
        } else {
            loginUser();
        }
    }

    /*
    This method validates successful login after tapping Login button and takes user to hub activity.
     */

    private void loginUser() {
        loginProgressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                loginProgressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                }
                if (!task.isSuccessful()) {
                    if (password.length() < 6) {
                        password_edit.setError(getString(R.string.login_val3));
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.login_val4), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent = new Intent(LoginActivity.this, HubActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
