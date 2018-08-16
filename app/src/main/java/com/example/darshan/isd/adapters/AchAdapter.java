package com.example.darshan.isd.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darshan.isd.R;
import com.example.darshan.isd.models.Achievements;
import com.example.darshan.isd.utils.utils;

import java.util.List;

public class AchAdapter extends RecyclerView.Adapter<AchAdapter.RecyclerViewHolders> {

    private List<Achievements> classList;
    private Context context;


    public AchAdapter(Context context, List<Achievements> classList) {
        this.classList = classList;
        this.context = context;
    }

    @Override
    public AchAdapter.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievements_layout, null);
        AchAdapter.RecyclerViewHolders rcv = new AchAdapter.RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(AchAdapter.RecyclerViewHolders holder, int position) {
        holder.achieve_textView.setText(classList.get(position).getAchievement());
    }

    @Override
    public int getItemCount() {
        return this.classList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        public TextView achieve_textView;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            achieve_textView = (TextView) itemView.findViewById(R.id.achieve_tv);

            achieve_textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = utils.openFaceBookUrl(view.getContext());
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    view.getContext().startActivity(facebookIntent);
                }
            });
        }
    }
}
