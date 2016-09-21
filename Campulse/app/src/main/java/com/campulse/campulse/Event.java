package com.campulse.campulse;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cecilerobertm on 16-09-11.
 */
class Event {
    private String databaseId;
    private String name;
    private String facebookId;
    private String location;
    private double latitude;
    private double longitude;
    private Date startTime;
    private Date endTime;
    private String owner;
    private String description;
    private String building;
    private String campus;
    private String imageUrl;
    private int fbAttending;
    private int appAttending;
    private ArrayList<String> attributes;
    private ArrayList<Integer> scores;
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