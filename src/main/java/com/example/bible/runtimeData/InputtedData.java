package com.example.bible.runtimeData;

import java.util.ArrayList;
import java.util.List;

public class InputtedData {
    private String language = "geo";
    private int versionIndex;
    private int bookIndex;
    private String version;
    private String book;
    private int chapter;
    private int verse;
    private int till;
    private String geoVersion;
    private String engVersion;
    private String rusVersion;
    private String schedule;
    private final List<String> scheduleBooks = new ArrayList<>();

    public InputtedData() {
    }


    public String getLanguage() {
        return language;
    }

    public int getVersionIndex() {
        return versionIndex;
    }

    public int getBookIndex() {
        return bookIndex;
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

    public String getGeoVersion() {
        return geoVersion;
    }

    public String getEngVersion() {
        return engVersion;
    }

    public String getRusVersion() {
        return rusVersion;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<String> getScheduleBooks() {
        return scheduleBooks;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setVersionIndex(int version) {
        this.versionIndex = version;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
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

    public void setGeoVersion(String geoVersion) {
        this.geoVersion = geoVersion;
    }

    public void setEngVersion(String engVersion) {
        this.engVersion = engVersion;
    }

    public void setRusVersion(String rusVersion) {
        this.rusVersion = rusVersion;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    public void addScheduleBook(String book){
        this.scheduleBooks.add(book);
    }
}
