package com.example.abdulazeezojetola.abdulaze_subbook;

import java.io.Serializable;

/**
 * Created by abdulazeezojetola on 2018-01-30.
 */

public class Subscription implements Serializable{

    private String subName;
    private String subDate;
    private double subPrice;
    private String subComment;

    public Subscription(String subName, String subDate, String subComment, double subPrice) {
    }


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



    @Override
    public String toString(){

        return "Subscription{" +
                "subName='" + subName + '\'' +
                ", subDate='" + subDate + '\'' +
                ", subPrice=" + subPrice + '}';
    }
}
