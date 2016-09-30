package com.campulse.campulse.eventList;

import java.util.Date;

/**
 * Created by cecilerobertm on 2016-09-28.
 */

public class HeaderItem extends ListItem {

    private Date date;

    // here getters and setters
    // for title and so on, built
    // using date

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}