package com.example.bible;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class LinkInfo {
    public String language;
    public int book;
    public int chapter;
    public int verse;
    public int till;
    public List<String> translations = new ArrayList<>();
    public List<String> books = new ArrayList<>();

    public void setLinkInfo(String language, int book, int chapter, int verse, int till) {
        try {
            LinkConstructor link = new LinkConstructor(language, book, chapter, verse);
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
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
