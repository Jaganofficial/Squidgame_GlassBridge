package com.example.gfg_squidgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int side;
    boolean selected,correctchoice=true;
    ObjectAnimator animator,animator1,animator3,animator4;
    MediaPlayer mediaPlayer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar a1=getSupportActionBar();
        a1.hide();
        ImageView glass1=findViewById(R.id.glass1);
        View view = glass1;
        ImageView glass2 = findViewById(R.id.glass2);
        View glass2View=glass2;
        animator=ObjectAnimator.ofFloat(view,"translationY",1400f);
        animator.setDuration(4000);
        animator1=ObjectAnimator.ofFloat(glass2View,"translationY",1400f);
        animator1.setDuration(4000);

        ImageView glass3=findViewById(R.id.glass3);
        View view3 = glass3;
        ImageView glass4 = findViewById(R.id.glass4);
        View glass4View=glass4;
        animator3=ObjectAnimator.ofFloat(view3,"translationY",1400f);
        animator3.setDuration(4000);
        animator4=ObjectAnimator.ofFloat(glass4View,"translationY",1400f);
        animator4.setDuration(4000);
        Intent i = new Intent(MainActivity.this,MainActivity.class);


        selected=false;
        side= (int) (Math.random() * 2);

        Dialog start = new Dialog(this);
        start.setContentView(R.layout.start);
        start.setCancelable(false);
        start.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        start.show();

        Dialog retry = new Dialog(this);
        retry.setContentView(R.layout.retry);
        retry.setCancelable(false);
        retry.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button play=start.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start();
                start.cancel();
            }
        });

        Button restart=retry.findViewById(R.id.retry1);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry.cancel();
                startActivity(i);
            }
        });

        //media player
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.glassbrake);
        mediaPlayer1 = MediaPlayer.create(this,R.raw.heartbeat);
        mediaPlayer1.setLooping(true);
        MediaPlayer mediaPlayer2 = MediaPlayer.create(this,R.raw.jump1);

     glass1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         if(side!=0) {
             glass1.setImageResource(R.drawable.broken_glass);
             selected=true;
             correctchoice=false;
             animator.cancel();
             animator1.cancel();
             animator3.cancel();
             animator4.cancel();
             mediaPlayer.start();
             mediaPlayer1.stop();
             retry.show();
         }
         else {
             selected=true;
             correctchoice=true;
             Toast.makeText(MainActivity.this, "Lucky!", Toast.LENGTH_SHORT).show();
             mediaPlayer2.start();
         }
     }
     });

        glass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(side!=1) {
                    glass2.setImageResource(R.drawable.broken_glass);
                    selected=true;
                    correctchoice=false;
                    animator.cancel();
                    animator1.cancel();
                    animator3.cancel();
                    animator4.cancel();
                    mediaPlayer.start();
                    mediaPlayer1.stop();
                    retry.show();
                }
                else {
                    selected=true;
                    correctchoice=true;
                    Toast.makeText(MainActivity.this, "Lucky!", Toast.LENGTH_SHORT).show();
                    mediaPlayer2.start();
                }
            }
        });

        glass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(side!=0) {
                    glass3.setImageResource(R.drawable.broken_glass);
                    selected=true;
                    correctchoice=false;
                    animator.cancel();
                    animator1.cancel();
                    animator3.cancel();
                    animator4.cancel();
                    mediaPlayer.start();
                    mediaPlayer1.stop();
                    retry.show();
                }
                else {
                    selected=true;
                    correctchoice=true;
                    Toast.makeText(MainActivity.this, "Lucky!", Toast.LENGTH_SHORT).show();
                    mediaPlayer2.start();
                }
            }
        });

        glass4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(side!=1) {
                    glass4.setImageResource(R.drawable.broken_glass);
                    selected=true;
                    correctchoice=false;
                    animator.cancel();
                    animator1.cancel();
                    animator3.cancel();
                    animator4.cancel();
                    mediaPlayer.start();
                    mediaPlayer1.stop();
                    retry.show();
                }
                else {
                    selected=true;
                    correctchoice=true;
                    Toast.makeText(MainActivity.this, "Lucky!", Toast.LENGTH_SHORT).show();
                    mediaPlayer2.start();
                }
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(view.getY()>=1200f)
                {
                    view3.setVisibility(View.VISIBLE);
                    glass4View.setVisibility(View.VISIBLE);
                    animator3.start();
                    animator4.start();
                    //animator.removeAllUpdateListeners();
                }
            }
        });

        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(view3.getY()>=1200f)
                {
                    animator.start();
                    animator1.start();
                   // animator.removeAllUpdateListeners();

                }
            }
        });

       animator.addListener(new AnimatorListenerAdapter() {
           @Override
           public void onAnimationEnd(Animator animation11) {
               super.onAnimationEnd(animation11);



               if(selected && correctchoice) {
                   view.setY(-10f);
                   animator.start();
                   glass2View.setY(-10f);
                   animator1.start();
                   side= (int) (Math.random() * 2);
               }
               else if(!selected){
                   glass1.setImageResource(R.drawable.broken_glass);
                   glass2.setImageResource(R.drawable.broken_glass);
                   animator.cancel();
                   animator1.cancel();
                   animator3.cancel();
                   animator4.cancel();
                   mediaPlayer.start();
                   mediaPlayer1.stop();
                   retry.show();
               }
               selected=false;
               correctchoice=false;
           }
       });

        animator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation11) {
                super.onAnimationEnd(animation11);

                if(selected && correctchoice) {
                    view3.setY(-10f);
                    animator3.start();
                    glass4View.setY(-10f);
                    animator4.start();
                    side= (int) (Math.random() * 2);
                }
                else if(!selected){
                    glass3.setImageResource(R.drawable.broken_glass);
                    glass4.setImageResource(R.drawable.broken_glass);
                   animator1.cancel();
                    animator.cancel();
                    animator3.cancel();
                    animator4.cancel();
                    mediaPlayer.start();
                    mediaPlayer1.stop();
                    retry.show();
                }
                selected=false;
                correctchoice=false;
            }
        });

    }

    public  void Start()
    {
        animator.start();
        animator1.start();
        mediaPlayer1.start();
    }
}