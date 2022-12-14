package com.example.slaughterhouse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Animal {
    private @Id @GeneratedValue Long registrationNumber;
    public int weight;
    public String date;
    public String origin;

    public Animal(){}
    Animal(int weight, String date, String origin) {
        this.weight = weight;
        this.date = date;
        this.origin = origin;
    }

    public int getWeight() {
        return weight;
    }

    public String getDate() {
        return date;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


}
