package com.campulse.campulse.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eduardo Coronado on 9/14/2016.
 */
public class Success {

    @SerializedName("status")
    int status;

    public int getStatus(){
        return status;
    }
}
