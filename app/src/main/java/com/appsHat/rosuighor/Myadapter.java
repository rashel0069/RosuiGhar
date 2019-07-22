package com.appsHat.rosuighor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<FoodViewHolder> {

    private Context mContext;
    private List<FoodData> myFoodList;

    public Myadapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item_book,viewGroup,false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {

        Glide.with(mContext)
                .load(myFoodList.get(i).getItemImage())
                .into(foodViewHolder.imageView);
        //foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
       foodViewHolder.mTitle.setText(myFoodList.get(i).getItemName());
       foodViewHolder.mDescription.setText(myFoodList.get(i).getItemDescription());
       foodViewHolder.mCategory.setText(myFoodList.get(i).getItemCategory());

       foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(mContext,DetailActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("Image",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemImage());
               intent.putExtra("Description",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
               mContext.getApplicationContext().startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}
class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription,mCategory;
    CardView mCardView;

    public FoodViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.food_img_id);
        mTitle = itemView.findViewById(R.id.food_title_id);
        mDescription = itemView.findViewById(R.id.food_discrept_id);
        mCategory = itemView.findViewById(R.id.food_cate_id);
        mCardView = itemView.findViewById(R.id.cardviewId);
    }
}
