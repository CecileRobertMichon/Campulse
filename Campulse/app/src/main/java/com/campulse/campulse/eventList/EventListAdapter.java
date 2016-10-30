package com.campulse.campulse.eventList;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.campulse.campulse.R;
import com.squareup.picasso.Picasso;
import com.campulse.campulse.model.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class EventListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView eventName;
        TextView eventTime;
        TextView eventLocation;
        ImageView eventPhoto;

        public EventViewHolder(final View itemView) {
            super(itemView);
            final Context context = itemView.getContext();
            cv = (CardView) itemView.findViewById(R.id.cv);
            eventName = (TextView) itemView.findViewById(R.id.event_name);
            eventTime = (TextView) itemView.findViewById(R.id.event_time);
            eventLocation = (TextView) itemView.findViewById(R.id.event_location);
            eventPhoto = (ImageView) itemView.findViewById(R.id.event_photo);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView date;

        HeaderViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date_item);
        }
    }

    List<ListItem> items;

    EventListAdapter(List<ListItem> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        this.context = viewGroup.getContext();
        if (viewType == ListItem.TYPE_HEADER) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header_list, viewGroup, false);
            return new HeaderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event_list, viewGroup, false);
           return new EventViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int type = getItemViewType(position);
        if (type == ListItem.TYPE_HEADER) {
            HeaderItem header = (HeaderItem) this.items.get(position);
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.date.setText(getFormattedDate(header.getDate()));
        } else {
            Event event = ((EventItem) this.items.get(position)).getEvent();
            EventViewHolder eventViewHolder = (EventViewHolder) viewHolder;
            // TODO : handle null name, time and location
            eventViewHolder.eventName.setText(event.getName());
            Date time = event.getStartTime();
            eventViewHolder.eventTime.setText(getFormattedTime(time));
            eventViewHolder.eventLocation.setText(event.getLocation());
            // TODO : only load event once
            Picasso.Builder builder = new Picasso.Builder(this.context);
            builder.listener(new Picasso.Listener()
            {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
                {
                    exception.printStackTrace();
                }
            });
            builder.build().load(event.getImageUrl())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .into(eventViewHolder.eventPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private String getFormattedTime(Date time) {
        try {
            SimpleDateFormat outgoingFormat = new SimpleDateFormat("hh:mm a", java.util.Locale.getDefault());
            return outgoingFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
            return context.getString(R.string.all_day);
        }
    }

    protected String getFormattedDate(Date date) {
        Calendar eventDate = Calendar.getInstance();
        eventDate.setTime(date);
        Calendar today = Calendar.getInstance();
        int year = eventDate.get(Calendar.YEAR);
        int month = eventDate.get(Calendar.MONTH) + 1;
        int day = eventDate.get(Calendar.DAY_OF_MONTH);
        int yearNow = today.get(Calendar.YEAR);
        int monthNow = today.get(Calendar.MONTH) + 1;
        int dayNow = today.get(Calendar.DAY_OF_MONTH);
        if (year == yearNow && month == monthNow && (day - dayNow < 6)) {
            switch (day - dayNow) {
                case 0:
                    return "Today";
                case 1:
                    return "Tomorrow";
                default:
                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
                    return dayFormat.format(date.getTime());
            }
        } else {
            SimpleDateFormat dayFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
            return dayFormat.format(date.getTime());
        }
    }
}