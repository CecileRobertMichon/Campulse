package com.campulse.campulse;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.campulse.campulse.model.Event;

import java.io.Serializable;

public class EventPageFragment extends Fragment {
    ImageButton goBack;
    ImageView imageLayout;
    TextView titleLayout;
    TextView locationLayout;
    TextView timeLayout;
    TextView descriptionText;
    ImageButton show, hide;
    Event event;
    Activity activity = getActivity();

    public static final EventPageFragment newInstance(Serializable event)
    {
        EventPageFragment f = new EventPageFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("EXTRA_EVENT", event);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        event = (Event) getArguments().getSerializable(getActivity().getResources().getString(R.string.extra_event_key));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_page, container, false);
        identifyAndListen(rootView);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                //(ActionBarActivity) getActivity().getSupportActionBar().hide;
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void identifyAndListen(View root) {
        updateUI(root);
        goBack = (ImageButton) root.findViewById(R.id.go_back);
        hide = (ImageButton) root.findViewById(R.id.hide);
        show = (ImageButton) root.findViewById(R.id.show);

        descriptionText.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = descriptionText.getLineCount();
                if (lineCount > 5) {
                    descriptionText.setMaxLines(5);
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("Show button");
                            show.setVisibility(View.INVISIBLE);
                            hide.setVisibility(View.VISIBLE);
                            descriptionText.setMaxLines(Integer.MAX_VALUE);

                        }
                    });

                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("Hide button");
                            hide.setVisibility(View.INVISIBLE);
                            show.setVisibility(View.VISIBLE);
                            descriptionText.setMaxLines(5);
                        }
                    });
                } else {
                    show.setVisibility(View.INVISIBLE);
                    hide.setVisibility(View.INVISIBLE);
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment();
            }
        });
    }

    private void closeFragment() {
        getActivity().getFragmentManager().popBackStack();
        //((ActionBarActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        //updateUI(getIntent());
    }

    public void updateUI(View root) {
        imageLayout = (ImageView) root.findViewById(R.id.eventPage_photo);
        // TODO : set image
        titleLayout = (TextView) root.findViewById(R.id.eventPage_eventTitle);
        titleLayout.setText(event.getName());
        locationLayout = (TextView) root.findViewById(R.id.eventPage_location);
        locationLayout.setText(event.getBuilding());
        timeLayout = (TextView) root.findViewById(R.id.eventPage_start_time);
        timeLayout.setText(event.getStartTime().toString());

        descriptionText = (TextView) root.findViewById(R.id.eventPage_descriptionField);
        descriptionText.setText(event.getDescription());
    }
}
