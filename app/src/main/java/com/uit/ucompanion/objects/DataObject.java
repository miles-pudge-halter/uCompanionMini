package com.uit.ucompanion.objects;

/**
 * Created by aYoe on 7/11/16.
 */
public class DataObject {
    private String Lecturer;
    private String Name;
    private String Room;
    private String subjID;

    public DataObject(){
        super();
    }
    public DataObject(String lecturer, String name, String room, String subjID) {
        Lecturer = lecturer;
        Name = name;
        Room = room;
        this.subjID = subjID;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public String getName() {
        return Name;
    }

    public String getRoom() {
        return Room;
    }

    public String getSubjID() {
        return subjID;
    }



    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public void setSubjID(String subjID) {
        this.subjID = subjID;
    }
}
