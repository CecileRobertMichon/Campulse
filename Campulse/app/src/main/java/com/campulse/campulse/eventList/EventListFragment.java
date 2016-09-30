package com.campulse.campulse.eventList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campulse.campulse.MainActivity;
import com.campulse.campulse.R;
import com.campulse.campulse.api.CampulseApi;
import com.campulse.campulse.eventList.EventListAdapter;
import com.campulse.campulse.model.Event;
import com.campulse.campulse.model.EventResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cecilerobertm on 16-09-11.
 */
public class EventListFragment extends Fragment {


    private List<Event> events;
    private List<ListItem> items;
    private RecyclerView rv;
    private CampulseApi mCampulseApi;  // TODO : Eduardo this isn't used??
    public final String TAG = "Event List Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.rv = (RecyclerView) getView().findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        this.rv.setLayoutManager(llm);
        this.rv.setHasFixedSize(true);

        initializeData();
    }

    private void initializeData(){
        this.events = new ArrayList<>();
        MainActivity main = (MainActivity) getActivity();
        CampulseApi mCampulseApi = main.getCampulseApi();

        // TODO Change Date String to be the last date of query done by user
        Call<EventResponse> eventsList = mCampulseApi.loadEvents("2016-09-10T20:00:00-0400");
        eventsList.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                EventResponse mEventResponse = response.body();
                if (mEventResponse != null) {
                    events = mEventResponse.getData();
                    if (events.size() > 0) {
                        // TODO Remove printing statement
                        for (Event event : events) {
                             // Log.d(TAG, "Event: " + event.getName());
                        }
                        initializeListItems();
                        initializeAdapter();
                    } else {
                        // TODO add empty response handler
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "Fragment: onResume");
    }

    private void initializeAdapter(){
        EventListAdapter adapter = new EventListAdapter(this.items);
        this.rv.setAdapter(adapter);
    }

    private void initializeListItems() {
        this.items = new ArrayList<>();
        Set dates = new HashSet<>();

        // get all the different event dates
        for (Event event : this.events) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date noTimeDate = formatter.parse(formatter.format(event.getStartTime()));
                if (!dates.contains(noTimeDate)) {
                    HeaderItem header = new HeaderItem();
                    header.setDate(noTimeDate);
                    items.add(header);
                    Log.d(TAG, "Date: " + noTimeDate.toString());
                }
                dates.add(noTimeDate);
            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
            }
            EventItem item = new EventItem();
            item.setEvent(event);
            items.add(item);
            Log.d(TAG, "Event: " + event.getName());
        }
    }
}
