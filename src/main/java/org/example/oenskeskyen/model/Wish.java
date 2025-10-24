package org.example.oenskeskyen.model;

public class Wish {
    private String name;
    private double price;
    private String link;

    public Wish(String name, double price, String link) {
        this.name = name;
        this.price = price;
        this.link = link;
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
}
