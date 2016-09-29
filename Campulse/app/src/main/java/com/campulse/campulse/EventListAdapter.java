package com.campulse.campulse;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.campulse.campulse.model.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    Context context;

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView eventName;
        TextView eventTime;
        TextView eventLocation;
        ImageView eventPhoto;

        EventViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            eventName = (TextView) itemView.findViewById(R.id.event_name);
            eventTime = (TextView) itemView.findViewById(R.id.event_time);
            eventLocation = (TextView) itemView.findViewById(R.id.event_location);
            eventPhoto = (ImageView) itemView.findViewById(R.id.event_photo);
        }
    }

    List<Event> events;

    EventListAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event_list, viewGroup, false);
        EventViewHolder evh = new EventViewHolder(v);
        this.context = viewGroup.getContext();
        return evh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder, int i) {
        // TODO : handle null name, time and location
        eventViewHolder.eventName.setText(events.get(i).getName());

        Date time = events.get(i).getStartTime();
        eventViewHolder.eventTime.setText(getFormattedTime(time));
        eventViewHolder.eventLocation.setText(events.get(i).getLocation());
        Picasso.Builder builder = new Picasso.Builder(this.context);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });
        builder.build().load(events.get(i).getImageUrl())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(eventViewHolder.eventPhoto);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    private String getFormattedTime(Date time) {
        try {
            SimpleDateFormat outgoingFormat = new SimpleDateFormat("hh:mm a", java.util.Locale.getDefault());
            return outgoingFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
            return "All Day"; //TODO : move to string values
        }
    }
}