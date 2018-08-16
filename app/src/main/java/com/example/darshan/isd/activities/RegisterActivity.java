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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darshan.isd.R;
import com.example.darshan.isd.models.User;
import com.example.darshan.isd.utils.CheckConnection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.btn_signup)
    Button register_btn;
    @BindView(R.id.input_name)
    EditText name_editText;
    @BindView(R.id.input_email)
    EditText email_editText;
    @BindView(R.id.input_password)
    EditText password_editText;
    @BindView(R.id.link_login)
    TextView login_tv;
    @BindView(R.id.progressBar)
    ProgressBar registerProgressBar;
    @BindView(R.id.profile_image)
    ImageView logo;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    String input_name;
    String input_email;
    String input_password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.isdm_logo1).into(logo);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        // Get firebase auth instance

        databaseReference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckConnection check = new CheckConnection(view.getContext());
                check.execute();
                validateRegister();
            }
        });

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    /*
    This method validates user input on Register screen and displays error for invalid input.
     */

    private void validateRegister() {

        input_name = name_editText.getText().toString();
        input_email = email_editText.getText().toString();
        input_password = password_editText.getText().toString();

        if ((TextUtils.isEmpty(input_name)) && (TextUtils.isEmpty(input_email)) && (TextUtils.isEmpty(input_password))) {
            name_editText.setError(getString(R.string.login_val5));
            email_editText.setError(getString(R.string.login_val1));
            password_editText.setError(getString(R.string.login_val2));
        } else if (input_name.length() == 0) {
            name_editText.setError(getString(R.string.login_val5));
        } else if (input_email.length() == 0) {
            email_editText.setError(getString(R.string.login_val1));
        } else if (input_password.length() == 0) {
            password_editText.setError(getString(R.string.login_val2));
        } else if (input_password.length() < 5) {
            password_editText.setError(getString(R.string.login_val6));
        } else if (!TextUtils.isEmpty(input_name) && !TextUtils.isEmpty(input_email)) {
            User user = new User();
            user.setName(input_name);
            user.setEmail(input_email);
            registerUser();
        }
    }

    /*
    This method validates successful registration and saves data in the cloud backend.
     */

    private void registerUser() {

        registerProgressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(input_email, input_password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, getString(R.string.account_success), Toast.LENGTH_SHORT).show();
                            registerProgressBar.setVisibility(View.GONE);
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, getString(R.string.account_error) + task.getException(), Toast.LENGTH_SHORT).show();
                            registerProgressBar.setVisibility(View.GONE);
                        } else {
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }
}
