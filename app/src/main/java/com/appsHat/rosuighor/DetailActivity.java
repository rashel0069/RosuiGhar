package com.appsHat.rosuighor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;

public class DetailActivity extends AppCompatActivity {
    JustifiedTextView foodDiscreption;
    ImageView foodImage;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        foodDiscreption = (JustifiedTextView) findViewById(R.id.dis_text_id);
        foodImage = (ImageView)findViewById(R.id.dis_img_Id);
        scrollView = (ScrollView)findViewById(R.id.text_scrollView);


        Bundle mBundle = getIntent().getExtras();
        if (mBundle!=null){
            foodDiscreption.setText(mBundle.getString("Description"));
           // foodImage.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodImage);
        }
    }
}
