package com.example.jacek.hondadiagnostic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.jacek.hondadiagnostic.Zaklady.*;

public class Serwisy extends AppCompatActivity {
    private static final String TAG = "Serwisy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serwisy);
        String[] tipsy = getResources().getStringArray(R.array.serwisy);
        ListView listView = (ListView) findViewById(R.id.listvew_serwisy);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipsy);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    Intent intent = new Intent(Serwisy.this, Backyard.class);
                    startActivity(intent);
                }
                if (position==1){
                    Intent intent = new Intent(Serwisy.this, Kylo.class);
                    startActivity(intent);
                }


            }
        });
    }
}
