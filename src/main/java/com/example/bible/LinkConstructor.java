package com.example.bible;

public class LinkConstructor {
    private String language;
    private String bibleVersion;
    private int book;
    private int chapter;
    private int verse;
    private int till;

    public LinkConstructor(String language, String bibleVersion, int book, int chapter, int verse) {
        this.language = language;
        this.bibleVersion = bibleVersion;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.till = 0;
    }

    public LinkConstructor(String language, String bibleVersion, int book, int chapter, int verse, int till) {
        this.language = language;
        this.bibleVersion = bibleVersion;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.till = till;
    }

    public String getLink() {
        if (language.equals("rus")) {
            language = "russian";
        }
        if (till == 0) {
            return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=&mv=" + bibleVersion + "&language=" + language + "&page=1&verse=" + verse;
        }
        return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=&mv=" + bibleVersion + "&language="
                + language + "&page=1&verse" + verse + "&versemde=" + till;

    }
}
