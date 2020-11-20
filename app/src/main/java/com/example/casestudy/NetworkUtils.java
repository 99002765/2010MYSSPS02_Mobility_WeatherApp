package com.example.casestudy;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
//    private static final String WEATHER_BASE_URL = "https://dataservice.accuweather.com/locations/v1/cities/search?";// Base URI for the Books API
//    private static final String API_KEY="apikey=KUGWA6AQVHDVybELAan2Vt798dSl5GAV";
//    private static final String QUERY_PARAM = "q"; // Parameter for the search string


    //URL example: http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=71b64f353050ff8bf156c74691f7513a
    private static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";// Base URI for the Books API
    private static final String API_KEY="71b64f353050ff8bf156c74691f7513a";
    private static final String API_PARAM="appid";
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    static String getCityInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String weatherJSONString = null;
        Log.d(LOG_TAG, "getCityInfo: "+queryString);
        try {
            //Build up your query URI, limiting results to 10 items and printed books
            Uri builtURI = Uri.parse(WEATHER_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(API_PARAM,API_KEY)
                    .build();
            URL requestURL = new URL(builtURI.toString());
            Log.i(LOG_TAG,"url="+requestURL.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            //urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput=scanner.hasNext();
            if(hasInput){
                Log.d(LOG_TAG, scanner.next());
            }
            if (inputStream == null) {
// Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
/* Since it's JSON, adding a newline isn't necessary (it won't affect
parsing) but it does make debugging a *lot* easier if you print out the
completed buffer for debugging. */
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
// Stream was empty. No point in parsing.
                return null;
            }
            weatherJSONString = buffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        } finally {
            if (urlConnection != null) {
                //urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Log.d(LOG_TAG, bookJSONString);
            return  weatherJSONString;
        }
    }


}


