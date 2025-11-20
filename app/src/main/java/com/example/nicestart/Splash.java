package com.example.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ImageView ghost=findViewById(R.id.LogoView);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.blink);
        ghost.startAnimation(myanim);
        openApp();
        ImageView mSea=findViewById(R.id.backView);

        Glide.with(this)
                .load("https://cdn.pixabay.com/photo/2020/03/23/12/08/plant-4960675_1280.jpg")
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop()
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                //.placeholder(new ColorDrawable(this.getResources().getColor(R.color.rojo)))
                //.circleCrop()
                .into(mSea);

    }
    private void openApp(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent =new Intent(Splash.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },5000);
    }
}