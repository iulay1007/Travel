package com.example.travel.model.domain;

import java.util.List;

public class SecondListResponse {
    public String sightId;
    public String province;
    public String city;
    public String title;
    public String address;
    public List<TagResponse> tagList;
    public String img;
    public String info;

    public SecondListResponse(String sightId, String province, String city, String title, String address, List<TagResponse> tagList, String img, String info) {
        this.sightId = sightId;
        this.province = province;
        this.city = city;
        this.title = title;
        this.address = address;
        this.tagList = tagList;
        this.img = img;
        this.info = info;
    }


    public String getSightId() {
        return sightId;
    }

    public void setSightId(String sightId) {
        this.sightId = sightId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<TagResponse> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagResponse> tagList) {
        this.tagList = tagList;
    }
}
