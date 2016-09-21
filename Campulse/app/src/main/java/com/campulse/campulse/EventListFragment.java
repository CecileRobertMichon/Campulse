package com.campulse.campulse;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campulse.campulse.api.CampulseApi;
import com.campulse.campulse.model.Event;
import com.campulse.campulse.model.EventResponse;
import com.campulse.campulse.model.Success;

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
    public Context c;

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
        Event e1 = new Event("Blues Pub", "4:00PM", "https://scontent-lga3-1.xx.fbcdn.net/t31.0-8/14435192_1051725604943295_969650258815470056_o.jpg");
        Event e2 = new Event("Hot Bagels in McConnell", "10:00AM", "https://scontent-lga3-1.xx.fbcdn.net/t31.0-8/12885818_1025747050833156_7812914289769150761_o.jpg");
        Event e3 = new Event("ECSESS Industry dinner", "6:00PM", "https://scontent-lga3-1.xx.fbcdn.net/t31.0-8/10633444_1001048313315963_4812118925620533830_o.jpg");
        Event e4 = new Event("Career Fair 2017", "9:00AM", "https://scontent-lga3-1.xx.fbcdn.net/t31.0-8/12038934_1044575462221736_2231994421450839872_o.jpg");
        this.events.add(e1);
        this.events.add(e2);
        this.events.add(e3);
        this.events.add(e4);

        MainActivity main = (MainActivity) getActivity();
        CampulseApi mCampulseApi = main.getCampulseApi();
        // Change Date String to be the last date of query done by user
        Call<EventResponse> eventsList = mCampulseApi.loadEvents("2016-09-10T20:00:00-0400");
        eventsList.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                events = response.body().getResult();
                for(Event event: events){
                    Log.d("Event List Fragment", "Event: " + event.getName());
                }
                initializeAdapter();
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });
//        Call<Success> test = mCampulseApi.ping();
//        test.enqueue(new Callback<Success>() {
//            @Override
//            public void onResponse(Call<Success> call, Response<Success> response) {
//                Log.d("Event List Fragment", response.body().getStatus() + " ");
//            }
//
//            @Override
//            public void onFailure(Call<Success> call, Throwable t) {
//
//            }
//        });
        // TODO : fetch events from database
    }

    private void initializeAdapter(){
        EventListAdapter adapter = new EventListAdapter(events);
        this.rv.setAdapter(adapter);
    }
}
