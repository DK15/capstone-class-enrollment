package com.example.darshan.isd.fragments;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darshan.isd.R;
import com.example.darshan.isd.models.ClassList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentOne extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    @BindView(R.id.fav_btn)
    Button enrollButton;
    @BindView(R.id.checkbox1)
    CheckBox checkBox1;
    @BindView(R.id.checkbox2)
    CheckBox checkBox2;
    @BindView(R.id.checkbox3)
    CheckBox checkBox3;
    @BindView(R.id.checkbox4)
    CheckBox checkBox4;
    @BindView(R.id.class_name_tv)
    TextView class_name_text;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.profile_image)
    ImageView class_image;

    public FragmentOne() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(getContext()).load(R.drawable.founder_profile).into(class_image);

        Toast.makeText(view.getContext(), R.string.toast_msg, Toast.LENGTH_LONG).show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child(getString(R.string.fb_node));

        auth = FirebaseAuth.getInstance();

        final String className = class_name_text.getText().toString();

        final String userId = auth.getCurrentUser().getUid();

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                DatabaseReference childNode = databaseReference.child(userId);

                childNode.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()) {

                            Toast.makeText(view.getContext(), R.string.checkbox_val, Toast.LENGTH_SHORT).show();

                        } else if (checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked()) {

                            if (dataSnapshot.child(getString(R.string.fb_node2)).exists()) {
                                Toast.makeText(view.getContext(), R.string.enrol_msg, Toast.LENGTH_SHORT).show();
                            } else {

                                ClassList classList = new ClassList();
                                classList.setClassName(className);
                                DatabaseReference newRef = databaseReference.child(userId).push();
                                newRef.setValue(classList);
                                Toast.makeText(view.getContext(), R.string.enroll_success, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        return view;
    }
}

