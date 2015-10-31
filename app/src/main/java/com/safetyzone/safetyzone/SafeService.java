package com.safetyzone.safetyzone;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Created by Zak on 31/10/2015.
 */
public class SafeService {
    public SafeService() {
        // Do nothing inside the constructor just yet
    }
    public int getSafetyRating(double longitude, double latitude) {
        String result = getData(longitude, latitude);
        JSONObject resultAsJson = null;
        try { resultAsJson = new JSONObject(result); }
        catch (JSONException e) { resultAsJson = null; }
        return new Random().nextInt(3);
    }
    public String getData(double longitude, double latitude) {
        //return "";
        try {
            URL url = new URL("http://safetyzone.azurewebsites.net/api/CrimeStats?latitude={0}&longitude={1}".format(String.valueOf(latitude), String.valueOf(longitude)));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line+'\n');
            }
            String result = sb.toString();
            return result;
        }
        catch (Exception e) {
            System.out.println("Failed connecting to server");
            return null;
        }
    }
}
