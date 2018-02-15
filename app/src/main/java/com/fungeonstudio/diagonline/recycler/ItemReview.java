package com.fungeonstudio.diagonline.recycler;

import java.sql.Timestamp;

/**
 * Created by Admin on 2/15/2018.
 */

public class ItemReview {
    private String message;
    private Long rate;
    private String author;
    private String timestamp;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
