package com.club.clubapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Masaj on 22.04.2017.
 */

public class LoadAllClubs extends AsyncTask<String,String,String>{
    private static String urlAllClubs = "http://10.0.2.2/android/get_club_list.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLUBS = "clubdesc";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOC = "localization";
    private static final String TAG_SCORE = "score";

    JSONParser jParser = new JSONParser();
    JSONArray clubs = null;
    ArrayList<HashMap<String, String>> clubsList = new ArrayList<HashMap<String, String>>();

    private LoadAllClubsInterface loadAllClubsListener;

    public LoadAllClubs(LoadAllClubsInterface loadAllClubsListener) {
        this.loadAllClubsListener = loadAllClubsListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... args) {
        List params = new ArrayList();
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(urlAllClubs, params);
        // Check your log cat for JSON reponse
        Log.d("All Products: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                clubs = json.getJSONArray(TAG_CLUBS);

                // looping through All Products
                for (int i = 0; i < clubs.length(); i++) {
                    JSONObject c = clubs.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_PID);
                    String name = c.getString(TAG_NAME);
                    String loc = c.getString(TAG_LOC);
                    String score = c.getString(TAG_SCORE);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_PID, id);
                    map.put(TAG_NAME, name);
                    map.put(TAG_LOC, loc);
                    map.put(TAG_SCORE, score);

                    // adding HashList to ArrayList
                    clubsList.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loadAllClubsListener.finishDataLoad(clubsList);
    }
}
