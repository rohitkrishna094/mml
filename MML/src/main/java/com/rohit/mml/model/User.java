package com.rohit.mml.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<WatchList> watchLists;

    public User() {
        this.watchLists = new ArrayList<>();
    }

    public User(String username, String password) {
        this.watchLists = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Set<Role> roles) {
        this.watchLists = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public List<WatchList> getWatchLists() {
        return watchLists;
    }

    public void setWatchLists(List<WatchList> watchLists) {
        this.watchLists = watchLists;
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
