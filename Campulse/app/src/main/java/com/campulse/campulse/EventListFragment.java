package com.campulse.campulse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campulse.campulse.api.CampulseApi;
import com.campulse.campulse.model.Event;
import com.campulse.campulse.model.EventResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cecilerobertm on 16-09-11.
 */
public class EventListFragment extends Fragment {


    private List<Event> events;
    private RecyclerView rv;
    private CampulseApi mCampulseApi;
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
                            Log.d(TAG, "Event: " + event.getName());
                        }
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
        EventListAdapter adapter = new EventListAdapter(events);
        this.rv.setAdapter(adapter);
    }
}
