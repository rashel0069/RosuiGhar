package com.appsHat.rosuighor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {
    String arrayName[]={"BanglaKhabar","Forgainkhabar","Vorta","Juice","Info","Admin"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#008577"),R.drawable.d1,R.drawable.d2)
                .addSubMenu(Color.parseColor("#86ADA6"),R.drawable.d3)
                .addSubMenu(Color.parseColor("#86ADA6"),R.drawable.d4)
                .addSubMenu(Color.parseColor("#86ADA6"),R.drawable.d6)
                .addSubMenu(Color.parseColor("#86ADA6"),R.drawable.d5)
                .addSubMenu(Color.parseColor("#86ADA6"),R.drawable.d7)
                .addSubMenu(Color.parseColor("#86ADA6"),R.drawable.admin)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(final int i) {

                final String name = String.valueOf(arrayName[i]);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms

                        if (name=="BanglaKhabar"){
                            Intent intent = new Intent(getApplicationContext(),Food_Items.class);
                            intent.putExtra("category",name);
                            startActivity(intent);
                        }if (name=="Forgainkhabar"){
                            Intent intent = new Intent(getApplicationContext(),Food_Items.class);
                            intent.putExtra("category",name);
                            startActivity(intent);
                        }if (name=="Vorta"){
                            Intent intent = new Intent(getApplicationContext(),Food_Items.class);
                            intent.putExtra("category",name);
                            startActivity(intent);
                        }if (name=="Juice"){
                            Intent intent = new Intent(getApplicationContext(),Food_Items.class);
                            intent.putExtra("category",name);
                            startActivity(intent);
                        }if (name=="Info"){
                            Intent intent = new Intent(getApplicationContext(),Food_Items.class);
                            intent.putExtra("category",name);
                            startActivity(intent);
                        }if (name=="Admin"){
                            Intent intent = new Intent(getApplicationContext(),AdminLogin.class);
                            intent.putExtra("category",name);
                            startActivity(intent);
                        }
                    }
                }, 1000);

            }
        });


    }
}

