package com.example.studentslist.allasynctask;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchGoogleTask extends AsyncTask <String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {

        String googlePlusLink = "https://www.googleapis.com/plus/v1/people/";
        String KEY_PARAM = "key";
        String GOOGLE_API_KEY = "AIzaSyBp-X7QdQ-DeABFxFFTUYOkHeLufm-iR80";

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

        }
        catch (Exception ignored) {}
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return getAccountDataFromJSON(stringBuilder.toString());
    }

    private String[] getAccountDataFromJSON(String accountJSONStr){
        final String OWM_IMAGE_LIST = "image";
        final String OWM_NAME_LIST = "name";
        final String OWM_SURNAME = "familyName";
        final String OWM_NAME = "givenName";
        String[] studentInfo = new String[3];

        try {
            JSONObject jsonObject = new JSONObject(accountJSONStr);
            JSONObject image = jsonObject.getJSONObject(OWM_IMAGE_LIST);
            String OWM_IMAGE_URL = "url";
            studentInfo[0] = image.getString(OWM_IMAGE_URL);

            JSONObject student = jsonObject.getJSONObject(OWM_NAME_LIST);
            studentInfo[1] = student.getString(OWM_NAME);
            studentInfo[2] = student.getString(OWM_SURNAME);
        } catch (JSONException e) {
        }
        return studentInfo;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
