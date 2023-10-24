package com.example.bible.requests;

public class LinkConstructor {
    private String language;
    private final String bibleVersion;
    private final int book;
    private final int chapter;
    private final int verse;
    private final int till;
    private String search;

    public LinkConstructor(String language, String bibleVersion, int book, int chapter, int verse) {
        this.language = language;
        this.bibleVersion = bibleVersion;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.till = 0;
    }

    public void setSearch(String search) {
        this.search = search;
    }


    public String getLink() {
        if (language.equals("rus")) {
            language = "russian";
        }
        if (search != null && !search.isEmpty()) {
            return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=" + search + "&mv=" + bibleVersion + "&language="
                    + language + "&page=1";
        }else if (till == 0) {
            return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=&mv=" + bibleVersion + "&language=" + language + "&page=1&verse=" + verse;
        } else {
            return "https://holybible.ge/service.php?w=" + book + "&t=" + chapter + "&m=&s=&mv=" + bibleVersion + "&language="
                    + language + "&page=1&verse" + verse + "&versemde=" + till;
        }
    }
}
