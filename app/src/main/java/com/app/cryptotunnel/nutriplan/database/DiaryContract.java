package com.app.cryptotunnel.nutriplan.database;

/**
 * Created by codephillip on 6/20/15.
 */
public class DiaryContract {

    //private variables
    int _id;
    String _note;
    String _time;

    // Empty constructor
    public DiaryContract(){

    }
    // constructor
    public DiaryContract(int id, String name, String _time){
        this._id = id;
        this._note = name;
        this._time = _time;
    }

    // constructor
    public DiaryContract(String _note, String _time){
        this._note = _note;
        this._time = _time;
    }
    public DiaryContract(int id){
        this._id = id;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting the note from the DB
    public String getNote(){
        return this._note;
    }

    // saving the note entered by the user
    public void setNote(String name){
        this._note = name;
    }

    // getting the current time
    public String getTime(){
        return this._time;
    }

    // setting the current time
    public void setTime(String _time){
        this._time = _time;
    }
}


