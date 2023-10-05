package com.example.bible.runtimeData;

public class InputtedData {
    private String language = "geo";
    private int versionIndex;
    private String version;
    private String book;
    private int chapter;
    private int verse;
    private int till;

    public InputtedData() {
    }

    public InputtedData(String language, int versionIndex, String version, String book, int chapter, int verse, int till) {
        this.language = language;
        this.versionIndex = versionIndex;
        this.version = version;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.till = till;
    }

    public String getLanguage() {
        return language;
    }

    public int getVersionIndex() {
        return versionIndex;
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

    public void setVersionIndex(int version) {
        this.versionIndex = version;
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
