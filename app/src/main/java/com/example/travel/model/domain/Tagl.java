package com.example.travel.model.domain;

import java.io.Serializable;

public class Tagl implements Serializable {
    /**
     * tagName : 服务好
     */

    private String tagName;

    public Tagl(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tagl{" +
                "tagName='" + tagName + '\'' +
                '}';
    }
}
