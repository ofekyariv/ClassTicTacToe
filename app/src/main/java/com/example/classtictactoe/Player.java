package com.example.classtictactoe;

import android.database.Cursor;
import android.widget.Toast;

// class modeling!!!!
public class Player implements Comparable {
    // fillds
    private String name;
    private int score;
    private String gender;
    private long id;


    // constructors
    public Player (String name, int score, String gender) {
        this.name = name;
        this.score = score;
        this.gender = gender;
    }

    public Player(String name, int score, String gender, long id) {
        this.name = name;
        this.score = score;
        this.gender = gender;
        this.id = id;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //methods
    public void addScore(){
        score++;
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof Player){
        Player other = (Player) o;
        //return name.compareTo(other.name); //names comparison
        return other.score - this.score;}
        return 0;
    }
}
