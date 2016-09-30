package com.campulse.campulse.eventList;

import com.campulse.campulse.model.Event;

import java.util.Date;

/**
 * Created by cecilerobertm on 2016-09-28.
 */

public abstract class ListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_EVENT = 1;

    abstract public int getType();
}

