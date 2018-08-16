package com.example.darshan.isd.activities;

import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.darshan.isd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends AppCompatActivity {


    @BindView(R.id.btn_reset_password)
    Button reset_button;
    @BindView(R.id.btn_back)
    Button back_button;
    @BindView(R.id.email)
    EditText email_editText;
    @BindView(R.id.progressBar)
    ProgressBar resetProgressBar;
    String email;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);
        ButterKnife.bind(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);


        auth = FirebaseAuth.getInstance();


        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(backIntent);
            }
        });
    }

    /*
    This method validates user input and displays error on screen.
     */

    private void validateEmail() {

        email = email_editText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            email_editText.setError(getString(R.string.login_val1));
        } else {
            resetPassword();
        }
    }

    /*
    This method validates successful reset password.
     */

    private void resetPassword() {
        resetProgressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.reset_msg), Toast.LENGTH_SHORT).show();
                    resetProgressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.reset_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

