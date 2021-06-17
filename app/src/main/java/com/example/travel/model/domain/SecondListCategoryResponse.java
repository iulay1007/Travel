package com.example.travel.model.domain;

import java.io.Serializable;
import java.util.List;

public class SecondListCategoryResponse implements Serializable {
    public String province;
    public String city;
    public String title;
    public String address;
    public String img;
    public String info;

    public String getWordCloud() {
        return wordCloud;
    }

    public void setWordCloud(String wordCloud) {
        this.wordCloud = wordCloud;
    }

    public String wordCloud;
    /**
     * tagList : [{'tagType': 32, 'tagScore': 5, 'tagNum': 19, 'tagName': '服务好'}, {'tagType': 16, 'tagScore': 5, 'tagNum': 24, 'tagName': '靠谱'}, {'tagType': 8, 'tagScore': 5, 'tagNum': 28, 'tagName': '便宜'}, {'tagType': 4, 'tagScore': 5, 'tagNum': 45, 'tagName': '取票方便'}]
     */

    private List<TagResponse> tagList;


    public SecondListCategoryResponse(String province, String city, String title, String address, String img, String info, String wordCloud, List<TagResponse> tagList) {
        this.province = province;
        this.city = city;
        this.title = title;
        this.address = address;
        this.img = img;
        this.info = info;
        this.wordCloud = wordCloud;
        this.tagList = tagList;
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
