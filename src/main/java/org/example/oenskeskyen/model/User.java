package org.example.oenskeskyen.model;

import java.util.List;

public class User {

    private String username;
    private String password;
    private int id;
    private String email;
    private List<String> wishlist;

    public User(String username, String password, int id, List<String> wishlist, String email) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.wishlist = wishlist;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setPassword(String code) {
        this.password = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> whislist) {
        this.wishlist = whislist;
    }

}
