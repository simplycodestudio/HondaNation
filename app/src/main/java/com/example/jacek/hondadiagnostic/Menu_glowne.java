package com.example.jacek.hondadiagnostic;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.view.View.OnClickListener;

import com.example.jacek.hondadiagnostic.SharePreerences.SharedPreferencesManager;


public class Menu_glowne extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferencesManager prefs; //added

    private Button tips;
    public ImageView img;
    public CardView crd;
    boolean favSelected = false;
    boolean iscolor = true;
    public void init(){
        img=(ImageView)findViewById(R.id.checkEngine);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//info dla clickview
                Intent toy = new Intent(Menu_glowne.this, engine_failure_codes.class);
                startActivity(toy);
            }
        });
        img=(ImageView)findViewById(R.id.Dziennik_napraw);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//info dla clickview
                Intent toy = new Intent(Menu_glowne.this, Dziennik_napraw.class);
                startActivity(toy);
            }
        }
        );
//        img=(ImageView)findViewById(R.id.Response);
//        img.setOnClickListener(new View.OnClickListener() {
//                                   @Override
//                                   public void onClick(View view) {//info dla clickview
//                                       Intent toy = new Intent(Menu_glowne.this, ResponseWelcomeActivity.class);
//                                       startActivity(toy);
//                                   }
//                               }
//        );
        img=(ImageView)findViewById(R.id.tips);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu_glowne.this, Tips_main.class);
                startActivity(toy);
            }
        });
        img=(ImageView)findViewById(R.id.serwisy);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu_glowne.this, Serwisy.class);
                startActivity(toy);
            }
        });
//        img=(ImageView)findViewById(R.id.tips);
//        img.setOnClickListener(new View.OnClickListener() {
//                                   @Override
//                                   public void onClick(View view) {//info dla clickview
//                                       Intent toy = new Intent(Menu_glowne.this, Tips_main.class);
//                                       startActivity(toy);
//                                   }
//                               }
//        );
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new SharedPreferencesManager(this); //get SharedPreferencesManager  instance

        int t = prefs.retrieveInt("theme", 1); //get stored theme, zero is default

        Utils.setTheme(t);  //Set the stored theme, will default to Black
        Utils.onActivityCreateSetTheme(this);

        setContentView(R.layout.activity_menu_glowne);

        init();//inicjuje clickview
        findViewById(R.id.R_fab).setOnClickListener(this);
        findViewById(R.id.eco_fab).setOnClickListener(this);
        getSupportActionBar().setElevation(0);

        final LinearLayout linearMain  = (LinearLayout)findViewById(R.id.linear_main);

//        this.tips = (Button) findViewById(R.id.tips);
//        tips.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Menu_glowne.this, Tips_main.class);
//                startActivity(intent);
//            }
//        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        actionBar.setTitle(null);
//


    }
    @Override
    public void onClick(View v) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_main);
        switch (v.getId()) {
            case R.id.eco_fab:
                prefs.storeInt("theme", 0); //store black theme
                Utils.changeToTheme(this, Utils.ECO_THEME);
                overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
                break;

            case R.id.R_fab:
                prefs.storeInt("theme", 1); //store black theme
                Utils.changeToTheme(this, Utils.R_THEME);
                overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
                break;
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.about_app){
            Intent intenthowtokody=new Intent(Menu_glowne.this, About_app.class);
            startActivity(intenthowtokody);
            return true;
        }
        return true;
    }


}
