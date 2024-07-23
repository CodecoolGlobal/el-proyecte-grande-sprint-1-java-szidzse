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

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
