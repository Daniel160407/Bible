package com.example.bible;

public class LinkConstructor {
    private String language;
    private int book;
    private int chapter;
    private int verse;
    private int till;

    public LinkConstructor(String language, int book, int chapter, int verse) {
        this.language = language;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.till = 0;
    }

    public LinkConstructor(String language, int book, int chapter, int verse, int till) {
        this.language = language;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.till = till;
    }

    public String getLink() {
        if (till == 0) {
            return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=&" +
                    "mv=%E1%83%90%E1%83%AE%E1%83%90%E1%83%9A%E1%83%98%20%E1%83%92%E1%83%90%E1%83%93%E1%83%90%E1%83%9B%E1%83%A3%E1%83%A8%E1%83" +
                    "%90%E1%83%95%E1%83%94%E1%83%91%E1%83%A3%E1%83%9A%E1%83%98%20%E1%83%92%E1%83%90%E1%83%9B%E1%83%9D%E1%83%AA%E1%83%94%E1%83%9B%E1%83%90%202015&language=" + language + "&page=1&verse=" + verse;
        }
        return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=&" +
                "mv=%E1%83%90%E1%83%AE%E1%83%90%E1%83%9A%E1%83%98%20%E1%83%92%E1%83%90%E1%83%93%E1%83%90%E1%83%9B%E1%83%A3%E1%83%A8%E1%83" +
                "%90%E1%83%95%E1%83%94%E1%83%91%E1%83%A3%E1%83%9A%E1%83%98%20%E1%83%92%E1%83%90%E1%83%9B%E1%83%9D%E1%83%AA%E1%83%94%E1%83%9B%E1%83%90%202015&language="
                + language + "&page=1&verse" + verse + "&versemde=" + till;

    }
}
