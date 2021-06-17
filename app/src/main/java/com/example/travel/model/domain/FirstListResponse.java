package com.example.travel.model.domain;

public class FirstListResponse {
    String title;
    String img;

    public FirstListResponse(String title, String img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "FirstListResponse{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
