package com.example.travel.model.domain;

import java.io.Serializable;
import java.util.List;

public class TagList implements Serializable {

    private String city;
    private List<String> tagName;

    public TagList(String city, List<String> tagName) {
        this.city = city;
        this.tagName = tagName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getTagName() {
        return tagName;
    }

    public void setTagName(List<String> tagName) {
        this.tagName = tagName;
    }
}
    /**
     * tagName : 服务好
     */
    /*[
    {"tagName": "服务好"},
    {"tagName": "hh"}
]*/


