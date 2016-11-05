package com.example.studentslist.allasynctask;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchGitHubTask extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {

        String gitHubLink = "https://api.github.com/users/";
        Uri uri = Uri.parse(gitHubLink).buildUpon().appendPath(params[0]).build();
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
        } catch (Exception e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return getAccountDataFromJSON(stringBuilder.toString());
    }

    private String[] getAccountDataFromJSON(String info) {

        String[] infoList = new String[12];

        final String LOGIN = "login";                   // "NikPikhmanets",
        final String AVATAR_URL = "avatar_url";         // "https://avatars.githubusercontent.com/u/22901635?v=3",
        final String HTML_URL = "html_url";             // "https://github.com/NikPikhmanets",
        final String NAME = "name";                     // "Пихманец Николай",
        final String COMPANY = "company";
        final String BLOG = "blog";
        final String LOCATION = "location";
        final String PUBLIC_REPOS = "public_repos";
        final String FOLLOWERS = "followers";
        final String FOLLOWING = "following";
        final String CREATED = "created_at";
        final String UPDATE = "updated_at";             //"2016-10-29T21:39:07Z"

        try {
            JSONObject infoJson = new JSONObject(info);

            infoList[0] = infoJson.getString(AVATAR_URL);
            infoList[1] = infoJson.getString(NAME);

            infoList[2] = infoJson.getString(LOGIN);
            infoList[3] = infoJson.getString(HTML_URL);
            infoList[4] = infoJson.getString(COMPANY);
            infoList[5] = infoJson.getString(BLOG);
            infoList[6] = infoJson.getString(LOCATION);
            infoList[7] = infoJson.getString(PUBLIC_REPOS);
            infoList[8] = infoJson.getString(FOLLOWERS);
            infoList[9] = infoJson.getString(FOLLOWING);
            infoList[10] = infoJson.getString(CREATED);
            infoList[11] = infoJson.getString(UPDATE);
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
