package org.example.oenskeskyen.model;

public class Wish {
    private String name;
    private double price;
    private String link;
    private int id;

    public Wish(String name, double price, String link, int id) {
        this.name = name;
        this.price = price;
        this.link = link;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

