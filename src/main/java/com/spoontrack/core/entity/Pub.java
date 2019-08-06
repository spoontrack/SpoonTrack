package com.spoontrack.core.entity;

public class Pub {
    private final String name;
    private final String address;
    private final String region;
    private final String country;
    private final String link;

    public Pub(String name, String address, String region, String country, String link) {
        this.name = name;
        this.address = address;
        this.region = region;
        this.country = country;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getLink() {
        return link;
    }

}
