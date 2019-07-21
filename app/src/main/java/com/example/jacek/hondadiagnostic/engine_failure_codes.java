package com.example.jacek.hondadiagnostic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class engine_failure_codes extends AppCompatActivity{
ListView lst;
android.content.res.Resources res;
Context mcontext;
    Integer[] GIF = {R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty,R.drawable.check_engine_zolty};
//    String[] NUMBERS = {"1", "2", "3", "4", "5", "6","7","8","9","10","12","13","14","15","16","17","19","20","21","22","23","30","31","41", "43", "45","48","54","58","61","63","65","67","70","71","72","73","74","75","76"};


//    String[] OPISY = {"Sonda lambda przy kolektorze", "Sonda lambda za kolektorem", "Czujnik MAP", "Czujnik położenia wału", "Czujnik MAP - bezwzględny czujnik ciśnienia w kolektorze", "Czujnik ECT - Wskaźnik temperatury cieczy chłodzącej","Czujnik TP - czujnik położenia przepustnicy","Czujnik TDC (Top Dead Center)","Czujnik CYP (czujnik Pocition Cylinder)","Czujnik IAT (czujnik temperatury powietrza dolotowego)","Czujnik podnoszenia EGR","Baro Sensor (ciśnienie atmosferyczne)","IAC Valve (Idle Air Control)","Sygnał wyjściowy zapłonu","Układ wtrysku paliwa","VSS (czujnik prędkości pojazdu)","AT - zawór elektromagnetyczny","ELD (elektryczny czujnik obciążenia)","Zawór elektromagnetyczny VTEC","Przełącznik ciśnienia oleju VTEC","Czujnik spalania stukowego","sygnał A - automatyczna skrzynia biegów","sygnał B - automatyczna skrzynia biegów","Główny podgrzewacz czujnika tlenu","układ paliwowy","System Too Lean, Or To Reach - zbyt uboga lub zbyt bogata mieszanka paliwowa","Czujnik LAF (Lean Air Fuel) - sonda szerokopasmowa","Czujnik CKF - czujnik odchylenia prędkości wału korbowego","Czujnik TDC 2 - Top Dead Center","podgrzewany czujnik tlenu - 1","podgrzewany czujnik tlenu - 2","Grzałka czujnika tlenu","Katalizator - sprawność poniżej normy ","Błąd automatycznej skrzyni biegów","Brak zapłonu na 1 cylindrze","Brak zapłonu na 2 cylindrze","Brak zapłonu na 3 cylindrze","Brak zapłonu na 4 cylindrze","Brak zapłonu na 5 cylindrze","Brak zapłonu na 6 cylindrze"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = engine_failure_codes.this;
        setContentView(R.layout.activity_engine_failure_codes);
        lst = (ListView) findViewById(R.id.listview);

        String[] NUMBERS = getResources().getStringArray(R.array.numerki_check);
        String[] OPISY = getResources().getStringArray(R.array.bledy);

        CustomListview customListview = new CustomListview(this, NUMBERS, OPISY, GIF);
        lst.setAdapter(customListview);





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.podmenu_kody, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.id_How_to){
            Intent intenthowtokody=new Intent(engine_failure_codes.this, How_to_kody.class);
            startActivity(intenthowtokody);
            return true;
        }
        return true;
    }

}
