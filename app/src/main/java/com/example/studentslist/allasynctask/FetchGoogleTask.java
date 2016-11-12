package com.example.studentslist.allasynctask;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchGoogleTask extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {

        String googlePlusLink = "https://www.googleapis.com/plus/v1/people/";
        String KEY_PARAM = "key";
        String GOOGLE_API_KEY = "AIzaSyA-mwyNqHCvmMKEwa_a3OLP1JXz_f8th9g";

        Uri uri = Uri.parse(googlePlusLink).buildUpon().appendPath(params[0]).appendQueryParameter(KEY_PARAM, GOOGLE_API_KEY).build();
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(uri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

        } catch (Exception ignored) {
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return getAccountDataFromJSON(stringBuilder.toString());
    }

    private String[] getAccountDataFromJSON(String info) {

        String[] infoList = new String[13];

        final String AVATAR_URL = "url";
        final String HTML_URL = "url";
        final String NAME = "displayName";
        final String circle = "circledByCount";
        final String coverPhoto = "coverPhoto";

        try {
            JSONObject infoJson = new JSONObject(info);

            JSONObject imageObject = infoJson.getJSONObject("image");
            infoList[0] = imageObject.getString(AVATAR_URL);
            infoList[1] = infoJson.getString(NAME);
            infoList[2] = infoJson.getString(HTML_URL);
            infoList[3] = infoJson.getString(circle);
            //TODO добавить ветку
//            infoList[4] = infoJson.getString(coverPhoto);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infoList;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
