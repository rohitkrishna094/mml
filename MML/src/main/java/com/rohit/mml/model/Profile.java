package com.rohit.mml.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@JsonIgnoreProperties(value = { "currentlyWatching", "completed", "onHold", "dropped", "notInterested", "planToWatch" }, allowGetters = true)
public class Profile {

    private Integer currentlyWatching = 0;
    private Integer completed = 0;
    private Integer onHold = 0;
    private Integer dropped = 0;
    private Integer notInterested = 0;
    private Integer planToWatch = 0;
    private String firstName = "";
    private String lastName = "";
    private String birthday = "";
    private String joinDate = "";
    private String profileImageUrl = "";
    private String gender = "";
    private String location = "";

    public Profile() {
    }

    public Profile(Integer currentlyWatching, Integer completed, Integer onHold, Integer dropped, Integer notInterested, Integer planToWatch, String firstName, String lastName, String birthday,
            String joinDate, String profileImageUrl, String gender, String location) {
        super();
        this.currentlyWatching = currentlyWatching;
        this.completed = completed;
        this.onHold = onHold;
        this.dropped = dropped;
        this.notInterested = notInterested;
        this.planToWatch = planToWatch;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.joinDate = joinDate;
        this.profileImageUrl = profileImageUrl;
        this.gender = gender;
        this.location = location;
    }

    public Integer getCurrentlyWatching() {
        return currentlyWatching;
    }

    public void setCurrentlyWatching(Integer currentlyWatching) {
        this.currentlyWatching = currentlyWatching;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getOnHold() {
        return onHold;
    }

    public void setOnHold(Integer onHold) {
        this.onHold = onHold;
    }

    public Integer getDropped() {
        return dropped;
    }

    public void setDropped(Integer dropped) {
        this.dropped = dropped;
    }

    public Integer getNotInterested() {
        return notInterested;
    }

    public void setNotInterested(Integer notInterested) {
        this.notInterested = notInterested;
    }

    public Integer getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(Integer planToWatch) {
        this.planToWatch = planToWatch;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(this);
    }

}
