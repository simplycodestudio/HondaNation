package com.example.jacek.hondadiagnostic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.facebook.AccessToken;

public class ResponseWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_welcome);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
          try
          {
             sleep(6000);
          }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           finally {
              Intent mainIntent = new Intent(ResponseWelcomeActivity.this, ResponseMainActivity.class);
              startActivity(mainIntent);
                   }
            }
        };
        thread.start();

    }



    @Override
    protected void onPause()
    {
        super.onPause();

        finish();
    }
}
