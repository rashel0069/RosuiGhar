package com.appsHat.rosuighor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class UploadRecipe extends AppCompatActivity {
    private Button selectimage,uploadrecipe;
    private Spinner selectcategory;
    private EditText recipeTitle,reciDeiscription;
    private ImageView recipeImage;
    String imageUrl;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);
        selectimage = (Button) findViewById(R.id.select_image_id);
        uploadrecipe = (Button) findViewById(R.id.upload_recipe_id);
        selectcategory = (Spinner) findViewById(R.id.spinner_id);
        recipeTitle = (EditText) findViewById(R.id.edit_title_id);
        reciDeiscription = (EditText) findViewById(R.id.edit_description_id);
        recipeImage = (ImageView)findViewById(R.id.up_image_id);

        //String s3 = selectroute.getSelectedItem().toString();
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker,1);
            }
        });

        uploadrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(recipeTitle.getText().toString()) && !TextUtils.isEmpty(reciDeiscription.getText().toString())){
                    uploadImages();
                }else {
                    Toast.makeText(UploadRecipe.this, "Enter Title and Recipe Description", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void uploadRecipes() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading.....");
        progressDialog.show();

        FoodData foodData = new FoodData(
                recipeTitle.getText().toString(),
                reciDeiscription.getText().toString(),
                selectcategory.getSelectedItem().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        String category = selectcategory.getSelectedItem().toString();
        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if (task.isSuccessful()){
                  progressDialog.dismiss();
                  Toast.makeText(UploadRecipe.this, "Upload Recipe", Toast.LENGTH_SHORT).show();
                  recipeTitle.setText("");
                  reciDeiscription.setText("");
                  selectcategory.setSelection(0);
                  recipeImage.setImageResource(R.drawable.prev);
              }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(UploadRecipe.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImages() {
        String foldername = selectcategory.getSelectedItem().toString();
        int position = selectcategory.getSelectedItemPosition();
        if (position != 0){
            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReference().child(foldername).child(uri.getLastPathSegment());

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Recipe Uploading.....");
            progressDialog.show();
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    Uri urlImage = uriTask.getResult();
                    imageUrl = urlImage.toString();
                    uploadRecipes();
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadRecipe.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }else {
            Toast.makeText(this, "Select Category", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            uri = data.getData();
            recipeImage.setImageURI(uri);
        }else {
            Toast.makeText(this, "You haven't picked images", Toast.LENGTH_SHORT).show();
        }
    }
}
