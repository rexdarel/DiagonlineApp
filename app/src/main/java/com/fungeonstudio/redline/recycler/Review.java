package com.fungeonstudio.redline.recycler;

/**
 * Created by Admin on 2/17/2018.
 */

public class Review {
    public String id, author, message;
    public Long rate;

    public Review(String id, String author, String message, Long rate){
        this.id = id;
        this.author = author;
        this.message = message;
        this.rate = rate;
    }
}
