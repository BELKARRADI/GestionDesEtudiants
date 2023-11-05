package com.example.ecole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SplashView extends AppCompatActivity {

    private ImageView image1;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
        image1 = findViewById(R.id.imageView1);
        image1.setImageResource(R.drawable.eleves);
        image = findViewById(R.id.iconRotation);


        image.setImageResource(R.drawable.chargement);
        RotateAnimation anim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
        image.startAnimation(anim);



        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(6000);
                    Intent intent = new Intent(SplashView.this, MainActivity.class);
                    startActivity(intent);
                    SplashView.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
    }
}