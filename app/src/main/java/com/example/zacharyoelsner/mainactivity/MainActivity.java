package com.example.zacharyoelsner.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;

    final int hasSeenRequestHolder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        // data to display
        final ArrayList<Movie> MovieList = Movie.getMoviesFromFile("movies.json", this);

        final MovieAdaptor adapter = new MovieAdaptor(this, MovieList);

        //set listview adapter to
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        //listview items are now selectable
        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Movie selectedMovie = MovieList.get(position);

                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                // put title and instruction URL
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("poster", selectedMovie.poster);
                detailIntent.putExtra("position", position);
                detailIntent.putExtra("episode_number", selectedMovie.episode_number);
                detailIntent.putExtra("Update_hasSeen" ,adapter.checkHasSeenText(position));
                startActivityForResult(detailIntent, hasSeenRequestHolder);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == hasSeenRequestHolder) {
            if (resultCode == RESULT_OK) {
                // Get adapter
                MovieAdaptor movieAdaptor = (MovieAdaptor) mListView.getAdapter();

                // Update the movie has seen status
                int position = data.getIntExtra("position", 0);

                String Update_hasSeen = data.getStringExtra("Update_hasSeen");
                // Update has seen status where the data is stored for the ListView
                movieAdaptor.movieSeenUpdate(position, Update_hasSeen);

                // Notify data has changed
                movieAdaptor.notifyDataSetChanged();
            }
        }
    }
}




