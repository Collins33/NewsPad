package com.example.root.newspad.models;

/**
 * Created by root on 9/23/17.
 */

public class News {
    private String author;
    private String title;
    private String description;
    private String website;
    private String image;

    private String date;

    public News(String title,String description,String author,String image,String website,String date){
        this.title=title;
        this.description=description;
        this.author=author;
        this.image=image;
        this.website=website;
        this.date=date;

    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }
}
