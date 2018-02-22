package com.example.zacharyoelsner.mainactivity;

/**
 * Created by zacharyoelsner on 2/19/18.
 */

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;

public class Movie {

    public int episode_number;
    public String title;
    public String Url;
    public String description;
    public String poster;
    public String[] main_characters;
    public ArrayList <String> character_2 = new ArrayList<>();

    public StringBuilder getCharacterString() {
        StringBuilder Character_String = new StringBuilder();

        Character_String.append(character_2.get(0));
        int character_bound = Math.min(character_2.size(), 3);
        for(int i = 1; i < character_bound; i++){
            Character_String.append(", ");
            Character_String.append(character_2.get(i));

        } return Character_String;
    }


    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> MovieList = new ArrayList<>();

        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each movie in your movies array

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();

                JSONObject movieJSON = movies.getJSONObject(i);

                movie.title = movieJSON.getString("title");
                movie.episode_number = movieJSON.getInt("episode_number");
                movie.description = movieJSON.getString("description");
                movie.Url = movieJSON.getString("url");
                movie.poster = movieJSON.getString("poster");

                JSONArray main_characters_array = movieJSON.getJSONArray("main_characters");

                //create array of characters
                for (int j = 0; j < main_characters_array.length(); j++){
                    String temp = main_characters_array.getString(j);
                    movie.character_2.add(temp);
                }
                // add to arraylist
                MovieList.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return MovieList;
    }

    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
