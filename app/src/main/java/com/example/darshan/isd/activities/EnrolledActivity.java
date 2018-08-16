package com.example.darshan.isd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.darshan.isd.R;
import com.example.darshan.isd.adapters.EnrollViewAdapter;
import com.example.darshan.isd.models.ClassList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnrolledActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.class_btn)
    Button class_catalog;

    private EnrollViewAdapter enrollViewAdapter;

    private LinearLayoutManager linearLayoutManager;

    private List<ClassList> classList;

    private DatabaseReference rootRef, testRoof;

    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrolled_layout);
        ButterKnife.bind(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        class_catalog.setVisibility(View.INVISIBLE);

        classList = new ArrayList<ClassList>();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;

        userId = user.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference().child(getString(R.string.fb_node));
        testRoof = rootRef.child(userId);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        // To display a toast message if not enrolled in any class yet.

        testRoof.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    Toast.makeText(EnrolledActivity.this, R.string.not_enrolled, Toast.LENGTH_LONG).show();
                    class_catalog.setVisibility(View.VISIBLE);
                    class_catalog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(EnrolledActivity.this, ClassDetailsActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // To display retrieved classnames from backend.

        testRoof.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ClassList> classList = new ArrayList<>();

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ClassList cl = new ClassList();
                    cl.setClassName(snapshot.child(getString(R.string.fb_node2)).getValue().toString());
                    classList.add(cl);

                }
                enrollViewAdapter = new EnrollViewAdapter(classList, EnrolledActivity.this);
                recyclerView.setAdapter(enrollViewAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}



