package com.example.abdulazeezojetola.abdulaze_subbook;

import java.io.Serializable;

/**
 * Created by abdulazeezojetola on 2018-01-30.
 */

/*
*
*   Class intended to store, return and set the data inputted by the user
*   and is used by every activity.
*
*
* */
public class Subscription implements Serializable{

    // subName is the name of the subscription, subDate is the starting date
    // subPrice is the price and subComment is the comment
    private String subName;
    private String subDate;
    private double subPrice;
    private String subComment;

    public Subscription(String subName, String subDate, String subComment, double subPrice) {
    }

    // Getters and setters to set and return the values of the subscription
    public double getSubPrice(){return this.subPrice;}

    public String getSubName() {
        return this.subName;
    }

    public String getSubDate() {
        return this.subDate;
    }

    public String getSubComment(){
        return this.subComment;
    }

    public void setSub(String subName, double subPrice, String subDate, String subComment){

        this.subName = subName;
        this.subPrice = subPrice;
        this.subDate = subDate;
        this.subComment = subComment;

    }


    // this is the format it is printed with in subAdapter
    @Override
    public String toString(){

        return "Subscription{" +
                "subName='" + subName + '\'' +
                ", subDate='" + subDate + '\'' +
                ", subPrice=" + subPrice + '}';
    }
}
