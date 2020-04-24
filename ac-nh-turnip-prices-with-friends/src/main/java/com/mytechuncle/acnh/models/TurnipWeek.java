package com.mytechuncle.acnh.models;

import javax.persistence.*;

@Entity
public class TurnipWeek {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    private ACNHUser user;
    private Integer year;
    private Integer week;
    private Integer boughtFor;
    private Integer monAM;
    private Integer monPM;
    private Integer tueAM;
    private Integer tuePM;
    private Integer wedAM;
    private Integer wedPM;
    private Integer thuAM;
    private Integer thuPM;
    private Integer friAM;
    private Integer friPM;
    private Integer satAM;
    private Integer satPM;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public ACNHUser getUser() {
        return user;
    }

    public void setUser(ACNHUser user) {
        this.user = user;
    }

    public Integer getBoughtFor() {
        return boughtFor;
    }

    public void setBoughtFor(Integer boughtFor) {
        this.boughtFor = boughtFor;
    }

    public Integer getMonAM() {
        return monAM;
    }

    public void setMonAM(Integer monAM) {
        this.monAM = monAM;
    }

    public Integer getMonPM() {
        return monPM;
    }

    public void setMonPM(Integer monPM) {
        this.monPM = monPM;
    }

    public Integer getTueAM() {
        return tueAM;
    }

    public void setTueAM(Integer tueAM) {
        this.tueAM = tueAM;
    }

    public Integer getTuePM() {
        return tuePM;
    }

    public void setTuePM(Integer tuePM) {
        this.tuePM = tuePM;
    }

    public Integer getWedAM() {
        return wedAM;
    }

    public void setWedAM(Integer wedAM) {
        this.wedAM = wedAM;
    }

    public Integer getWedPM() {
        return wedPM;
    }

    public void setWedPM(Integer wedPM) {
        this.wedPM = wedPM;
    }

    public Integer getThuAM() {
        return thuAM;
    }

    public void setThuAM(Integer thuAM) {
        this.thuAM = thuAM;
    }

    public Integer getThuPM() {
        return thuPM;
    }

    public void setThuPM(Integer thuPM) {
        this.thuPM = thuPM;
    }

    public Integer getFriAM() {
        return friAM;
    }

    public void setFriAM(Integer friAM) {
        this.friAM = friAM;
    }

    public Integer getFriPM() {
        return friPM;
    }

    public void setFriPM(Integer friPM) {
        this.friPM = friPM;
    }

    public Integer getSatAM() {
        return satAM;
    }

    public void setSatAM(Integer satAM) {
        this.satAM = satAM;
    }

    public Integer getSatPM() {
        return satPM;
    }

    public void setSatPM(Integer satPM) {
        this.satPM = satPM;
    }
}
