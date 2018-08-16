package com.example.darshan.isd.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.darshan.isd.models.ClassList;
import com.example.darshan.isd.R;
import com.example.darshan.isd.activities.PageViewActivity;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.RecyclerViewHolders> {

    private List<ClassList> classList;
    private Context context;


    public ClassAdapter(Context context, List<ClassList> classList) {
        this.classList = classList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_catalog, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.className.setText(classList.get(position).getClassName());
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return this.classList.size();
    }


    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView className;
        public CardView cardView;
        private AdapterView.OnItemClickListener listener;

        public RecyclerViewHolders(final View itemView) {
            super(itemView);

            className = (TextView) itemView.findViewById(R.id.classes);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) view.getTag();

                    Intent classIntent = new Intent(view.getContext(), PageViewActivity.class);
                    view.getContext().startActivity(classIntent);

                }
            });
        }

        @Override
        public void onClick(View view) {

        }


    }
}

