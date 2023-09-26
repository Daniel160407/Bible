package com.example.bible;

public class InputtedData {
    private String language = "geo";
    private String version;
    private String book;
    private int chapter;
    private int verse;
    private int till;

    public String getLanguage() {
        return language;
    }

    public String getVersion() {
        return version;
    }

    public String getBook() {
        return book;
    }

    public int getChapter() {
        return chapter;
    }

    public int getVerse() {
        return verse;
    }

    public int getTill() {
        return till;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public void setTill(int till) {
        this.till = till;
    }
}
