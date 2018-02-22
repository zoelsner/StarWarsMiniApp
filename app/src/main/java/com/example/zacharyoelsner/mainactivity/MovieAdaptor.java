package com.example.zacharyoelsner.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdaptor extends BaseAdapter {


// adapter is needed when you want to do any sort of list or table view
// gets data and decides where to display in the activity

        // adapter takes the app itself and a list of data to display
        private Context mContext;
        private ArrayList<Movie> mMovieList;
        private LayoutInflater mInflater;
        private String [] hasSeenOptions;

        // constructor
        public MovieAdaptor(Context mContext, ArrayList<Movie> mMovieList){

            // initialize instances variables
            this.mContext = mContext;
            this.mMovieList = mMovieList;
            //this.hasSeenUpdate = hasSeen;
            this.hasSeenOptions = new String[mMovieList.size()];
            this.mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        public void movieSeenUpdate(int k, String update){
        hasSeenOptions[k] = update;
    }

        public String checkHasSeenText(int k) {
            String hasSeenText = hasSeenOptions[k];
            if(hasSeenText == null) {
                hasSeenText = mContext.getString(R.string.has_seen_question);

            }
            return hasSeenText;
        }


    //Overrides

        @Override
        public int getCount(){
            return mMovieList.size();
        }

        // returns the item at specific position in the data source

        @Override
        public Object getItem(int position){
            return mMovieList.get(position);
        }

        // returns the row id associated with the specific position in the list
        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            com.example.zacharyoelsner.mainactivity.MovieAdaptor.ViewHolder holder;


            // check if the view already exists
            // if yes, you don't need to inflate and findViewbyID again
            if (convertView == null){
                // inflate
                convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
                // add the views to the holder
                holder = new com.example.zacharyoelsner.mainactivity.MovieAdaptor.ViewHolder();

                holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
                holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
                holder.hasSeenTextView = convertView.findViewById(R.id.movie_list_hasSeen);
                holder.characterTextView = convertView.findViewById(R.id.movie_list_characters);
                holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);
                // add the holder to the view
                // for future use
                convertView.setTag(holder);
            }
            else {
                holder = (com.example.zacharyoelsner.mainactivity.MovieAdaptor.ViewHolder)convertView.getTag();
            }


            // get relative subview of the row view
            TextView titleTextView = holder.titleTextView;
            TextView descriptionTextView = holder.descriptionTextView;
            TextView hasSeenTextView = holder.hasSeenTextView;
            ImageView thumbnailImageView = holder.thumbnailImageView;
            TextView characterTextView = holder.characterTextView;

            // get corresponding movie from each row
            Movie movie = (Movie)getItem(position);

            // update the row view's textviews and imageview to display the information

            titleTextView.setText(movie.title);
            descriptionTextView.setText(movie.description);
            characterTextView.setText(movie.getCharacterString());
            String hasSeenOptions = checkHasSeenText(position);
            hasSeenTextView.setText(hasSeenOptions);


            // imageView
            // use Picasso library to load image from the image url
            Picasso.with(mContext).load(movie.poster).into(thumbnailImageView);
            return convertView;
        }

        private static class ViewHolder{
            public TextView titleTextView;
            public TextView descriptionTextView;
            public ImageView thumbnailImageView;
            public TextView characterTextView;
            public TextView hasSeenTextView;
        }

    }




