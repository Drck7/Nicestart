package com.example.nicestart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openApp();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ImageView ghost=findViewById(R.id.LogoView);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.blink);
        ghost.startAnimation(myanim);

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