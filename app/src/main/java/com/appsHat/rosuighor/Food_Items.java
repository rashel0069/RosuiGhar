package com.appsHat.rosuighor;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Food_Items extends AppCompatActivity {
    private TextView textView;
    RecyclerView mRecyclerView;
    List<FoodData>myFoodList;
    FoodData mFoodData;
    Myadapter myadapter;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__items);
        textView = findViewById(R.id.categoryId);
        textView.setText(getIntent().getExtras().getString("category"));
        mRecyclerView = (RecyclerView)findViewById(R.id.recylerview_id);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Recipes....");
        myFoodList = new ArrayList<>();

        myadapter = new Myadapter(getApplicationContext(),myFoodList);
        mRecyclerView.setAdapter(myadapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

       /* Queue queue = (Queue) FirebaseDatabase.getInstance().getReference("Recipe")
                .orderByChild("itemCategory").equalTo(textView.getText().toString());*/

        //dialog show
        progressDialog.show();
        eventListener = databaseReference.orderByChild("itemCategory").equalTo(textView.getText().toString())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myFoodList.clear();
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    myFoodList.add(foodData);
                }
                myadapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });
    }
}
