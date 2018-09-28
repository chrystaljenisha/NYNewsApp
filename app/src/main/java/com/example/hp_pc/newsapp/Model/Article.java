package com.example.hp_pc.newsapp.Model;

public class Article {
    public String url;
    public String section;
    public String byline;
    public String title;
    public String content;
    public String published_date;

    public Article() {
    }

    public Article(String url, String section, String byline, String title, String content, String published_date) {
        this.url = url;
        this.section = section;
        this.byline = byline;
        this.title = title;
        this.content = content;
        this.published_date = published_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByLine() {
        return byline;
    }

    public void setByLine(String byLine) {
        this.byline = byLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return published_date;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", section='" + section + '\'' +
                ", byline='" + byline + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", published_date='" + published_date + '\'' +
                '}';
    }

    public void setDate(String date) {
        this.published_date = date;
    }
}
