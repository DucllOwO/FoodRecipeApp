package com.nmuddd.foodrecipeapp.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.nmuddd.foodrecipeapp.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    /*@BindView(R.id.splash_background)
    ImageView imageView;

    @BindView(R.id.lottie)
    LottieAnimationView lottie;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        //imageView.animate().translationY(-1800).alpha(1).setDuration(3000).setStartDelay(4000).start();
        //lottie.animate().translationY(1400).alpha(1).setDuration(3000).setStartDelay(4000).start();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        service.schedule(this::startLogin, 5, TimeUnit.SECONDS);
    }

    private void startLogin() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}