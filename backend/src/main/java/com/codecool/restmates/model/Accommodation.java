package com.codecool.restmates.model;

public class Accommodation {
    private long id;
    private String name;
    private double pricePerNight;

    public Accommodation() {

    }

    public Accommodation(String name, double pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    public Accommodation(long id, String name, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
