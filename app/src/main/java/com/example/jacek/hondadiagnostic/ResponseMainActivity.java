package com.example.jacek.hondadiagnostic;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
//import android.widget.Toolbar;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResponseMainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsPagerAdapter myTabsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_response_main);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (AccessToken.getCurrentAccessToken() == null)
//        {
//            goResponseStartPage();
//        }

        mAuth = FirebaseAuth.getInstance(); //// nie wiem czy potrzebne

        //Tabs for Response main activity
        myViewPager = (ViewPager) findViewById(R.id.response_main_tabs_pager);
        myTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsPagerAdapter);
        myTabLayout = (TabLayout) findViewById(R.id.response_main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);


        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_response_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Response");



    }


    private void goResponseStartPage() {
        Intent intent = new Intent(this, ResponseStartPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
//    public void logout (View view){
//        FirebaseAuth.getInstance().signOut();
//        LoginManager.getInstance().logOut();
//        goResponseStartPage();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.response_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_account_settings_button)
        {
            Intent settingsIntent = new Intent(ResponseMainActivity.this, ResponseSettingsActivity.class);
            startActivity(settingsIntent);
        }
         if (item.getItemId() == R.id.logoutBtn)
         {
             FirebaseAuth.getInstance().signOut();
             LoginManager.getInstance().logOut();
             goResponseStartPage();
         }

         if (item.getItemId() == R.id.main_all_users_button)
         {
             Intent allusersIntent = new Intent(ResponseMainActivity.this, ResponseAllUsersActivity.class);
             startActivity(allusersIntent);
         }



        return true;

    }
    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
            Intent startPageIntent = new Intent(ResponseMainActivity.this, ResponseStartPage.class);
            startPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startPageIntent);
            finish();
        }
    }
}
