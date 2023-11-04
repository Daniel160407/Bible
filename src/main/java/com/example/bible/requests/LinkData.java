package com.example.bible.requests;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LinkData {
    public int chapterCount;
    public int verseCount;
    public String apiUrl = "";
    public String linkData;
    public List<String> verses = new ArrayList<>();
    public List<List<String>> versePath = new ArrayList<>();
    public String search = "";

    public synchronized void setLinkInfo(String language, String bibleVersion, int book, int chapter, int verse, int till, boolean getFullBible) {
        try {
            LinkConstructor link = new LinkConstructor(language, bibleVersion, book, chapter, verse);
            link.setSearch(search);
            if (!apiUrl.equals(link.getLink())) {
                apiUrl = link.getLink();
                System.out.println(apiUrl);
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    linkData = response.toString();
                    System.out.println(linkData);
                    JSONObject jsonObject = new JSONObject(linkData);
                    if (till == 0) {
                        till = verse;
                    }
                    chapterCount = Integer.parseInt(jsonObject.getJSONArray("tavi").getJSONObject(0).getString("cc"));
                    verseCount = Integer.parseInt(jsonObject.getJSONArray("muxli").getJSONObject(0).getString("cc"));
                    if (search != null && !search.isEmpty()) {
                        for (int i = verse - 1; i < jsonObject.getJSONArray("bibleData").length(); i++) {
                            verses.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv").replaceAll("<span class='markedText'>|</span>", ""));
                            List<String> list = new ArrayList<>();
                            list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("tavi"));
                            list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("muxli"));
                            versePath.add(list);
                        }
                    } else {
                        if (getFullBible) {
                            for (int i = 0; i < verseCount; i++) {
                                verses.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv"));
                                System.out.println(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv"));
                                List<String> list = new ArrayList<>();
                                list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("tavi"));
                                list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("muxli"));
                                versePath.add(list);
                            }
                        } else {
                            for (int i = verse - 1; i < till; i++) {
                                verses.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv"));
                                List<String> list = new ArrayList<>();
                                list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("tavi"));
                                list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("muxli"));
                                versePath.add(list);
                            }
                        }
                    }


                } else {
                    System.err.println("API request failed with response code: " + responseCode);
                }
            } else {
                JSONObject jsonObject = new JSONObject(linkData);
                if (till == 0) {
                    till = verse;
                }
                chapterCount = Integer.parseInt(jsonObject.getJSONArray("tavi").getJSONObject(0).getString("cc"));
                verseCount = Integer.parseInt(jsonObject.getJSONArray("muxli").getJSONObject(0).getString("cc"));
                if (search != null && !search.isEmpty()) {
                    for (int i = verse - 1; i < jsonObject.getJSONArray("bibleData").length(); i++) {
                        verses.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv").replaceAll("<span class='markedText'>|</span>", ""));
                        List<String> list = new ArrayList<>();
                        list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("tavi"));
                        list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("muxli"));
                        versePath.add(list);
                    }
                } else {
                    for (int i = verse - 1; i < till; i++) {
                        verses.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv"));
                        List<String> list = new ArrayList<>();
                        list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("tavi"));
                        list.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("muxli"));
                        versePath.add(list);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
