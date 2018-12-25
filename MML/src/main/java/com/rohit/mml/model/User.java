package com.rohit.mml.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.rohit.mml.jacksonviews.UserViews;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;

    @JsonIgnore
    private String password;
    private Set<Role> roles = new HashSet<>();

    @JsonView(UserViews.ExtendedPublic.class)
    private WatchList watchList;

    @JsonView(UserViews.ExtendedPublic.class)
    private Profile profile;

    public User() {
        this.profile = new Profile();
        this.watchList = new WatchList();
    }

    public User(String username, String password) {
        this.profile = new Profile();
        this.watchList = new WatchList();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Set<Role> roles) {
        this.profile = new Profile();
        this.watchList = new WatchList();
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public boolean addItem(Map<String, Movie> map) {
        Map.Entry<String, Movie> entry = map.entrySet().iterator().next();
        String key = entry.getKey();
        Movie movie = entry.getValue();
        boolean res = watchList.addItem(key, movie);
        updateWatchListProfile();
        return res;
    }

    public void removeItem(Movie movie) {
        watchList.removeMovieIfPresentAnywhereElse(movie, true);
        updateWatchListProfile();
    }

    public Profile updateWatchListProfile() {
        profile.setCurrentlyWatching(watchList.getCurrentlyWatching().size());
        profile.setOnHold(watchList.getOnHold().size());
        profile.setPlanToWatch(watchList.getPlanToWatch().size());
        profile.setNotInterested(watchList.getNotInterested().size());
        profile.setCompleted(watchList.getCompleted().size());
        profile.setDropped(watchList.getDropped().size());
        return profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public WatchList getWatchList() {
        return watchList;
    }

    public void setWatchList(WatchList watchList) {
        this.watchList = watchList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
