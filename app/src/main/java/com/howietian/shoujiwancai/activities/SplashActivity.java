package com.howietian.shoujiwancai.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.howietian.shoujiwancai.R;

public class SplashActivity extends AppCompatActivity {
private Handler handler = new Handler();
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
      }
    },2000);
  }
}
