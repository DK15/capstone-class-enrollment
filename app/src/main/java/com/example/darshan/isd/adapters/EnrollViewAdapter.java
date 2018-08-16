package com.example.darshan.isd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darshan.isd.R;
import com.example.darshan.isd.models.ClassList;

import java.util.List;

public class EnrollViewAdapter extends RecyclerView.Adapter<EnrollViewAdapter.EnrolledViewHolders> {

    private List<ClassList> classLists;
    private Context context;

    public EnrollViewAdapter(List<ClassList> cl, Context context) {
        this.classLists = cl;
        this.context = context;
    }

    @Override
    public EnrolledViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        EnrolledViewHolders viewHolder = null;

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.enroll_item, parent, false);
        viewHolder = new EnrolledViewHolders(layoutView);
        return viewHolder;
    }

    public class EnrolledViewHolders extends RecyclerView.ViewHolder {

        public TextView enrolled_class_textView;

        public EnrolledViewHolders(View itemView) {
            super(itemView);

            enrolled_class_textView = (TextView) itemView.findViewById(R.id.enrolled_class_tv);
        }
    }

    @Override
    public int getItemCount() {
        return this.classLists.size();
    }

    @Override
    public void onBindViewHolder(final EnrolledViewHolders holder, final int position) {
        holder.enrolled_class_textView.setText(classLists.get(position).getClassName());
    }

}
