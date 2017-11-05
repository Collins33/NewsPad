package com.example.root.newspad.models;

import org.parceler.Parcel;

/**
 * Created by root on 9/23/17.
 */
@Parcel
public class News {
     String author;
     String title;
     String description;
     String website;
     String image;
     String date;
     private String pushId;
    public News(String title,String description,String author,String image,String website,String date){
        this.title=title;
        this.description=description;
        this.author=author;
        this.image=image;
        this.website=website;
        this.date=date;

    }
    public News(){}

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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
