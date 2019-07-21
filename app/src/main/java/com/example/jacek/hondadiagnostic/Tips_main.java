package com.example.jacek.hondadiagnostic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jacek.hondadiagnostic.Tipsy.P06_vtec_conversion;

public class Tips_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_main);



        String[] tipsy = getResources().getStringArray(R.array.tipsy);
        ListView listView = (ListView) findViewById(R.id.listvew_tips);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipsy);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Tips_main.this, P06_vtec_conversion.class);
                startActivity(intent);

            }
        });
    }

}
