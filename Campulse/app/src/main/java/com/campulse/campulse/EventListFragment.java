package com.campulse.campulse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cecilerobertm on 16-09-11.
 */
public class EventListFragment extends Fragment {


    private List<Event> events;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rv = (RecyclerView) getView().findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        events = new ArrayList<>();
        // TODO : fetch events from database
    }

    private void initializeAdapter(){
        EventListAdapter adapter = new EventListAdapter(events);
        rv.setAdapter(adapter);
    }
}
