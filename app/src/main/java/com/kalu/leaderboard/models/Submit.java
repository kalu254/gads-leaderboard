package com.kalu.leaderboard.models;

public class Submit {

    private String first_name;
    private String last_name;
    private String email_address;
    private String project_link;

    public Submit(String first_name, String last_name, String email_address, String project_link) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.project_link = project_link;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getProject_link() {
        return project_link;
    }

    public void setProject_link(String project_link) {
        this.project_link = project_link;
    }
}
