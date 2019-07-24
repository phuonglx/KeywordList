package com.example.keywordlist.models;

public class Keyword {

    private String keyword;
    private String color;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Keyword() {
    }

    public Keyword(String keyword, String color) {
        setKeyword(keyword);
        setColor(color);
    }

}
