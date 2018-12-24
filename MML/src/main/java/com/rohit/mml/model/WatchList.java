package com.rohit.mml.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WatchList {

    private String _id;
    private List<Movie> currentlyWatching;
    private List<Movie> completed;
    private List<Movie> onHold;
    private List<Movie> dropped;
    private List<Movie> planToWatch;
    private List<Movie> notInterested;

    public WatchList() {
        this._id = ObjectId.get().toString();
    }

    public WatchList(String _id, List<Movie> currentlyWatching, List<Movie> completed, List<Movie> onHold, List<Movie> dropped, List<Movie> planToWatch, List<Movie> notInterested) {
        this._id = ObjectId.get().toString();
        this.currentlyWatching = currentlyWatching;
        this.completed = completed;
        this.onHold = onHold;
        this.dropped = dropped;
        this.planToWatch = planToWatch;
        this.notInterested = notInterested;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Movie> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public void setCurrentlyWatching(List<Movie> currentlyWatching) {
        this.currentlyWatching = currentlyWatching;
    }

    public List<Movie> getCompleted() {
        return completed;
    }

    public void setCompleted(List<Movie> completed) {
        this.completed = completed;
    }

    public List<Movie> getOnHold() {
        return onHold;
    }

    public void setOnHold(List<Movie> onHold) {
        this.onHold = onHold;
    }

    public List<Movie> getDropped() {
        return dropped;
    }

    public void setDropped(List<Movie> dropped) {
        this.dropped = dropped;
    }

    public List<Movie> getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(List<Movie> planToWatch) {
        this.planToWatch = planToWatch;
    }

    public List<Movie> getNotInterested() {
        return notInterested;
    }

    public void setNotInterested(List<Movie> notInterested) {
        this.notInterested = notInterested;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(this);
    }

}
