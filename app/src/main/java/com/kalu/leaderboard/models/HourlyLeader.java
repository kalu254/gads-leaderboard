package com.kalu.leaderboard.models;


public class HourlyLeader {

    private String name;
    private Integer hours;
    private String country;
    private String badgeUrl;


    public HourlyLeader(String name, Integer hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}
