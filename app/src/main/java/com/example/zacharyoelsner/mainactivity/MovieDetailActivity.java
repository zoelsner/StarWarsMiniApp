package com.example.zacharyoelsner.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class MovieDetailActivity extends AppCompatActivity {

        private String Update_hasSeen;

        // override onCreate method
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_movie_detail);

            //DONE


            String title = this.getIntent().getExtras().getString("title");
            String poster = this.getIntent().getExtras().getString("poster");
            String description = this.getIntent().getExtras().getString("description");
            String hasSeen = this.getIntent().getExtras().getString("Update_hasSeen");
            final int position = this.getIntent().getExtras().getInt("position");

            this.Update_hasSeen= hasSeen;

            // set the title on the action bar
            setTitle(title);
            //DONE TO HEREE

            TextView textViewTitle = findViewById(R.id.movie_list_title);
            textViewTitle.setText(title);

            TextView descriptionTextView = findViewById(R.id.movie_list_description);
            descriptionTextView.setText(description);

            //button
            Button submitButton = findViewById(R.id.submit_button);
            submitButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("Update_hasSeen", Update_hasSeen);
                    intent.putExtra("position", position);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });



            // use Picasso library to load image from the image url
            ImageView thumbnailImageView = findViewById(R.id.movie_list_thumbnail);
            Picasso.with(this).load(poster).into(thumbnailImageView);


            //Find button then input text from strings
            RadioButton buttonAlreadySeen = findViewById(R.id.radioButton);
            RadioButton buttonWantToSee = findViewById(R.id.radioButton_2);
            RadioButton buttonDoNotLike = findViewById(R.id.radioButton_3);
            buttonAlreadySeen.setChecked(getString(R.string.hasSeen_AlreadySeen).equals(hasSeen));
            buttonWantToSee.setChecked(getString(R.string.hasSeen_WantToSee).equals(hasSeen));
            buttonDoNotLike.setChecked(getString(R.string.hasSeen_DoNotLike).equals(hasSeen));
        }

    public void onRadioSwitch(View view) {
        this.Update_hasSeen = (String) ((RadioButton) view).getText();
    }
}




