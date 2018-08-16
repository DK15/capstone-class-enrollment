package com.example.darshan.isd.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darshan.isd.models.Events;
import com.example.darshan.isd.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.RecyclerViewHolders> {

    private List<Events> classList;
    private Context context;


    public EventsAdapter(Context context, List<Events> classList) {
        this.classList = classList;
        this.context = context;
    }

    @Override
    public EventsAdapter.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_layout, null);
        EventsAdapter.RecyclerViewHolders rcv = new EventsAdapter.RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(EventsAdapter.RecyclerViewHolders holder, int position) {
        holder.eventName.setText(classList.get(position).getEvent_name());
        holder.dateTime.setText(classList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return this.classList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        @BindView(R.id.event_name_tv)
        TextView eventName;
        @BindView(R.id.datetime_tv)
        TextView dateTime;
        @BindView(R.id.card_view)
        CardView cardView;


        public RecyclerViewHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog();
                }
            });
        }
    }

    private void showAlertDialog() {

        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle((R.string.dialog_title));
        alertDialog.setMessage(context.getResources().getString(R.string.dialog_body));
        alertDialog.setButton(context.getResources().getString(R.string.dialog_btn), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
