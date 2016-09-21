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
    @SerializedName("result")
    ArrayList<Event> result;


    public ArrayList<Event> getResult() {
        return result;
    }

    public void setResult(ArrayList<Event> result) {
        this.result = result;
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
