package com.humblecarrotstudios.android.drwords;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QueryUtils {

    public static Response responses = null;

    public static String getJsonFromUrl(String url) {

        String jsonData = "";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("X-RapidAPI-Key", "6GLLkVjJxmmshkpHE0TKpHRJASU5p1EKYrgjsnbj9kEoza5C7l")
                    .url(url)
                    .build();

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonData = responses.body().string();
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
