package com.example.darshan.isd.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.darshan.isd.activities.AchieveActivity;
import com.example.darshan.isd.activities.ClassDetailsActivity;
import com.example.darshan.isd.models.Hub;
import com.example.darshan.isd.R;
import com.example.darshan.isd.activities.AboutUsActivity;
import com.example.darshan.isd.activities.ContactActivity;
import com.example.darshan.isd.activities.EventsActivity;
import com.example.darshan.isd.utils.utils;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolders> {

    private List<Hub> hubList;
    private Context context;


    public RecyclerViewAdapter(Context context, List<Hub> hubList) {
        this.hubList = hubList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.hubEntry.setText(hubList.get(position).getHubEntry());
    }

    @Override
    public int getItemCount() {
        return this.hubList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView hubEntry;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            hubEntry = (TextView) itemView.findViewById(R.id.hub_entry);
        }

        @Override
        public void onClick(View view) {

            if (getAdapterPosition() == 0) {
                Intent classIntent = new Intent(view.getContext(), ClassDetailsActivity.class);
                view.getContext().startActivity(classIntent);
            } else if (getAdapterPosition() == 1) {
                Intent eventsIntent = new Intent(view.getContext(), EventsActivity.class);
                view.getContext().startActivity(eventsIntent);
            } else if (getAdapterPosition() == 2) {
                Intent achIntent = new Intent(view.getContext(), AchieveActivity.class);
                view.getContext().startActivity(achIntent);
            } else if (getAdapterPosition() == 3) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = utils.openFaceBookUrl(view.getContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                view.getContext().startActivity(facebookIntent);
            } else if (getAdapterPosition() == 4) {
                Intent aboutIntent = new Intent(view.getContext(), AboutUsActivity.class);
                view.getContext().startActivity(aboutIntent);
            } else if (getAdapterPosition() == 5) {
                Intent contactIntent = new Intent(view.getContext(), ContactActivity.class);
                view.getContext().startActivity(contactIntent);
            }
        }
    }
}
