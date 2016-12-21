package com.example.abdullahkucuk.fruittuin.Global;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by pplom on 19-12-2016.
 */

public class Session implements Serializable {

    private UUID id;
    private String name;
    private int age;
    private Date dateStart;
    private Date dateEnd;
    private String location;
    private String rating;
    private String feedback;
    private int progressIndex;

    public Session() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getProgressIndex() {
        return progressIndex;
    }

    public void setProgressIndex(int progressIndex) {
        this.progressIndex = progressIndex;
    }


}
