package com.example.travel.model.domain;



public class TagResponse  {
    /**
     * tagType : 32
     * tagScore : 5
     * tagNum : 19
     * tagName : 服务好
     */

    private int tagType;
    private int tagScore;
    private int tagNum;
    private String tagName;

    public TagResponse(int tagType, int tagScore, int tagNum, String tagName) {
        this.tagType = tagType;
        this.tagScore = tagScore;
        this.tagNum = tagNum;
        this.tagName = tagName;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public int getTagScore() {
        return tagScore;
    }

    public void setTagScore(int tagScore) {
        this.tagScore = tagScore;
    }

    public int getTagNum() {
        return tagNum;
    }

    public void setTagNum(int tagNum) {
        this.tagNum = tagNum;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "TagResponse{" +
                "tagType=" + tagType +
                ", tagScore=" + tagScore +
                ", tagNum=" + tagNum +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
