package com.campulse.campulse.eventList;
import com.campulse.campulse.model.Event;

/**
 * Created by cecilerobertm on 2016-09-28.
 */

public class EventItem extends ListItem {

    private Event event;

    // here getters and setters
    // for title and so on, built
    // using event

    @Override
    public int getType() {
        return TYPE_EVENT;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

}