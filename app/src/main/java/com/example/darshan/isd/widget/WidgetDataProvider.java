package com.example.darshan.isd.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.darshan.isd.R;
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

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    List<ClassList> cl = new ArrayList<>();
    Context context;
    Intent intent;

    private FirebaseAuth auth;

    private DatabaseReference mainNode;

    private DatabaseReference childNode;

    private String userId;

    private FirebaseUser user;

    /*
    This method displays enrolled classes of logged in user. Widget will be empty if user is not enrolled in any class yet.
    Also note that : in order to see data in widget, user has to login and has to enrol in atleast one class.
    Recommended access of widget would be when user is logged in.
     */
    private void initializeData() throws NullPointerException {

        try {
            cl.clear();

            auth = FirebaseAuth.getInstance();

            user = auth.getCurrentUser();
            assert user != null;

            userId = auth.getCurrentUser().getUid();

            mainNode = FirebaseDatabase.getInstance().getReference().child(context.getResources().getString(R.string.fb_node));

            childNode = mainNode.child(userId);

            childNode.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ClassList cl1 = new ClassList();
                        cl1.setClassName(snapshot.child(context.getResources().getString(R.string.fb_node2)).getValue().toString());
                        cl.add(cl1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        initializeData();

    }

    @Override
    public void onDataSetChanged() {
        initializeData();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.d("TAG", "Total count is " + cl.size());
        return cl.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_enrolled_list_item);
        remoteViews.setTextViewText(R.id.widget_enrolled, cl.get(i).getClassName());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
