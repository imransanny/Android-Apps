package edu.ewubd.newsapp;

public class Event {
    String key = "";
    String name = "";
    String place = "";
    String datetime = "";
    String capacity = "";
    String budget = "";
    String email = "";
    String phone = "";
    String description = "";
    String eventType = "";

    public Event(String key, String name, String place, String capacity, String budget, String email, String phone, String description, String datetime, String eventType){
        this.key = key;
        this.name = name;
        this.place = place;
        this.capacity = capacity;
        this.budget = budget;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.datetime = datetime;
        this.eventType = eventType;
    }
}
