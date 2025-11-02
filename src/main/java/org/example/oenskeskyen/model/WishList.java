package org.example.oenskeskyen.model;

public class WishList {

    private int id;
    private String name;

    //Til framework
    public WishList() {
    }

    //Forsøgt at tydeliggøre nedenstående getters og setter mm.

    public WishList(String name) {
        this.name = name;
    }

    public WishList(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

