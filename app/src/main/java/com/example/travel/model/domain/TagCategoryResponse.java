package com.example.travel.model.domain;

import java.io.Serializable;
import java.util.List;

public class TagCategoryResponse implements Serializable {
    /**
     * tagName : name
     * SightList : [{"title":"title","city":"海口","province":"province","address":"address","img":"img","info":"info"}]
     */

    private String tagName;
    private List<SightListBean> SightList;

    public TagCategoryResponse(String tagName, List<SightListBean> sightList) {
        this.tagName = tagName;
        SightList = sightList;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<SightListBean> getSightList() {
        return SightList;
    }

    public void setSightList(List<SightListBean> sightList) {
        SightList = sightList;
    }

    @Override
    public String toString() {
        return "TagCategoryResponse{" +
                "tagName='" + tagName + '\'' +
                ", SightList=" + SightList +
                '}';
    }


    public static class SightListBean implements Serializable {
        /**
         * title : title
         * city : 海口
         * province : province
         * address : address
         * img : img
         * info : info
         */

        private String title;
        private String city;
        private String province;
        private String address;
        private String img;
        private String info;

        public SightListBean(String title, String city, String province, String address, String img, String info) {
            this.title = title;
            this.city = city;
            this.province = province;
            this.address = address;
            this.img = img;
            this.info = info;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
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

        @Override
        public String toString() {
            return "SightListBean{" +
                    "title='" + title + '\'' +
                    ", city='" + city + '\'' +
                    ", province='" + province + '\'' +
                    ", address='" + address + '\'' +
                    ", img='" + img + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
    /**
     * tagName : name
     * SightList : [{"sight":{"title":"title","city":"海口","province":"province","address":"address","img":"img","info":"info"}}]
     */
    /*{
    "tagName": "name",
    "SightList": [{
        "title": "title",
        "city": "海口",
        "province": "province",
        "address": "address",
        "img": "img",
        "info": "info"
    }]
}*/
}