package com.uit.ucompanion.objects;

/**
 * Created by aYoe on 7/19/16.
 */
public class EventObject {

    //private variables
    String title;
    String body;
    String eventDate;



    public EventObject(){
        super();
    }

    public EventObject(String body, String title, String eventDate){
        this.title = title;
        this.body = body;
        this.eventDate=eventDate;
    }
    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public String getTitle(){
        return this.title;
    }

    // setting name
    public void setTitle(String name){
        this.title = name;
    }

    // getting phone number
    public String getBody(){
        return this.body;
    }

    // setting phone number
    public void setBody(String phone_number){
        this.body = phone_number;
    }
}
