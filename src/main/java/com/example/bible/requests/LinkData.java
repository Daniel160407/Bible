package com.example.bible.requests;

import com.example.bible.requests.LinkConstructor;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LinkData {
    public int chaptersCount;
    public int versesCount;
    public List<String> verses = new ArrayList<>();
    public List<String> versePath = new ArrayList<>();
    public String searchText;

    public void setLinkInfo(String language, String bibleVersion, int book, int chapter, int verse, int till) {
        try {
            LinkConstructor link = new LinkConstructor(language, bibleVersion, book, chapter, verse);
            link.setSearchText(searchText);
            String apiUrl = link.getLink();
            System.out.println(apiUrl);
            System.out.println("________");
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
                String linkData = response.toString();
                System.out.println(linkData);
                JSONObject jsonObject = new JSONObject(linkData);
                if (till == 0) {
                    till = verse;
                    System.out.println("Error3");
                }
                System.out.println("Till: " + till);
                System.out.println("Verse: " + verse);
                if (verse == 0) {
                    verse++;
                    till++;
                }
                for (int i = verse - 1; i < till; i++) {
                    System.out.println(i + " ::::::::: " + jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv"));
                    verses.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("bv"));
                    versePath.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("tavi"));
                    versePath.add(jsonObject.getJSONArray("bibleData").getJSONObject(i).getString("muxli"));
                }
                System.out.println("Chapter: " + Integer.parseInt(jsonObject.getJSONArray("tavi").getJSONObject(0).getString("cc")));
                chaptersCount = Integer.parseInt(jsonObject.getJSONArray("tavi").getJSONObject(0).getString("cc"));
                versesCount = Integer.parseInt(jsonObject.getJSONArray("muxli").getJSONObject(0).getString("cc"));
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
