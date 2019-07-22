package com.appsHat.rosuighor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    private EditText email,password;
    private ImageButton login;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email = (EditText)findViewById(R.id.image_email_id);
        password = (EditText)findViewById(R.id.image_pass_id);
        login = (ImageButton)findViewById(R.id.login_button_id);
        progressBar = findViewById(R.id.progressBar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();
                String s2 = password.getText().toString();
                if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s1)){
                    progressBar.setVisibility(View.VISIBLE);
                    signin();
                }else {
                    Toast.makeText(AdminLogin.this, "Please Enter Valid Email and Password", Toast.LENGTH_SHORT).show();
                    email.setError("Enter Valid Email");
                    password.setError("Enter valid Password");
                }
            }
        });

    }

    private void signin() {
        String e1 = email.getText().toString();
        String e2 = password.getText().toString();
        mAuth.signInWithEmailAndPassword(e1, e2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(),UploadRecipe.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Email and Password does not match.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}