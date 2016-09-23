package com.campulse.campulse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eduardo Coronado on 9/21/2016.
 */
public class EventResponse {

    @SerializedName("status")
    int status;
    @SerializedName("date")
    Date dateOfquery;
    @SerializedName("data")
    ArrayList<Event> data;

    public ArrayList<Event> getData() {
        return this.data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateOfquery() {
        return dateOfquery;
    }

    public void setDateOfquery(Date dateOfquery) {
        this.dateOfquery = dateOfquery;
    }
}
