package com.campulse.campulse.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cecilerobertm on 16-09-11.
 */
public class Event implements Serializable{
    @SerializedName("_id")
    private String databaseId;
    @SerializedName("name")
    private String name;
    @SerializedName("facebookId")
    private String facebookId;
    @SerializedName("location")
    private String location;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("start_time")
    private Date startTime;
    @SerializedName("end_time")
    private Date endTime;
    @SerializedName("owner")
    private String owner;
    @SerializedName("description")
    private String description;
    @SerializedName("building")
    private String building;
    @SerializedName("campus")
    private String campus;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("fb_attending")
    private int fbAttending;
    @SerializedName("app_attending")
    private int appAttending;
    @SerializedName("attributes")
    private ArrayList<String> attributes;
    @SerializedName("scores")
    private ArrayList<Integer> scores;
    @SerializedName("ongoing")
    private Boolean ongoing;

    public Event(String name, String databaseId, String location, double latitude, double longitude, Date startTime, String description, String owner, String campus) {
        this.name = name;
        this.databaseId = databaseId;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = startTime;
        this.description = description;
        this.owner = owner;
        this.campus = campus;
    }

    // for testing UI
    public Event(String name, String campus, String imageUrl) {
        this.name = name;
        this.campus = campus;       // should be replaced by start time
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getFbAttending() {
        return fbAttending;
    }

    public void setFbAttending(int fbAttending) {
        this.fbAttending = fbAttending;
    }

    public int getAppAttending() {
        return appAttending;
    }

    public void setAppAttending(int appAttending) {
        this.appAttending = appAttending;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public Boolean getOngoing() {
        return ongoing;
    }

    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }
}