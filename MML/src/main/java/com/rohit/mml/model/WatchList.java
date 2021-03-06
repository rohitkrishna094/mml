package com.rohit.mml.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WatchList {

    private List<Movie> currentlyWatching;
    private List<Movie> completed;
    private List<Movie> onHold;
    private List<Movie> dropped;
    private List<Movie> planToWatch;
    private List<Movie> notInterested;

    public WatchList() {
        this.currentlyWatching = new ArrayList<>();
        this.completed = new ArrayList<>();
        this.onHold = new ArrayList<>();
        this.dropped = new ArrayList<>();
        this.planToWatch = new ArrayList<>();
        this.notInterested = new ArrayList<>();
    }

    public WatchList(List<Movie> currentlyWatching, List<Movie> completed, List<Movie> onHold, List<Movie> dropped, List<Movie> planToWatch, List<Movie> notInterested) {
        this.currentlyWatching = currentlyWatching;
        this.completed = completed;
        this.onHold = onHold;
        this.dropped = dropped;
        this.planToWatch = planToWatch;
        this.notInterested = notInterested;
    }

    public boolean isMoviePresentInAnyList(Movie movie) {
        List<List<Movie>> lists = Arrays.asList(currentlyWatching, completed, onHold, dropped, planToWatch, notInterested);
        for (int i = 0; i < lists.size(); i++) {
            if (isMoviePresentInList(lists.get(i), movie))
                return true;
        }
        return false;
    }

    private boolean isMoviePresentInList(List<Movie> movies, Movie movie) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getImdbID().equals(movie.getImdbID()))
                return true;
        }
        return false;
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

    // key represents which list this movie should go into
    public boolean addItem(String key, Movie movie) {
        removeMovieIfPresentAnywhereElse(movie, false);
        if (key.equals("currentlyWatching")) {
            currentlyWatching.add(movie);
            return true;
        } else if (key.equals("completed")) {
            completed.add(movie);
            return true;
        } else if (key.equals("onHold")) {
            onHold.add(movie);
            return true;
        } else if (key.equals("dropped")) {
            dropped.add(movie);
            return true;
        } else if (key.equals("planToWatch")) {
            planToWatch.add(movie);
            return true;
        } else if (key.equals("notInterested")) {
            notInterested.add(movie);
            return true;
        }
        return false;
    }

    // 3rd argument: true-mongoId, false: imdbId
    public void removeMovieIfPresentAnywhereElse(Movie movie, boolean mongoId) {
        List<List<Movie>> lists = Arrays.asList(currentlyWatching, completed, onHold, dropped, planToWatch, notInterested);
        for (int i = 0; i < lists.size(); i++) {
            if (isMoviePresentInList(lists.get(i), movie)) {
                List<Movie> list = lists.get(i);
                removeMovieFromList(list, movie, mongoId);
            }
        }
    }

    private void removeMovieFromList(List<Movie> list, Movie movie, boolean mongoId) {
        for (int i = 0; i < list.size(); i++) {
            if ((mongoId && list.get(i).getId().equals(movie.getId())) || (list.get(i).getImdbID().equals(movie.getImdbID()))) {
                list.remove(i);
                break;
            }
        }
    }

}
