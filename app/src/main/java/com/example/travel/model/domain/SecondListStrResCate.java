package com.example.travel.model.domain;

public class SecondListStrResCate {
    public String province;
    public String city;
    public String title;
    public String address;
    public String img;
    public String info;
    private String tagList;
    private String wordCloud;

    public SecondListStrResCate(String province, String city, String title, String address, String img, String info, String tagList, String wordCloud) {
        this.province = province;
        this.city = city;
        this.title = title;
        this.address = address;
        this.img = img;
        this.info = info;
        this.tagList = tagList;
        this.wordCloud = wordCloud;
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

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public String getWordCloud() {
        return wordCloud;
    }

    public void setWordCloud(String wordCloud) {
        this.wordCloud = wordCloud;
    }
}
