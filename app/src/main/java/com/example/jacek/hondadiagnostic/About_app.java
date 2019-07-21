package com.example.jacek.hondadiagnostic;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacek.hondadiagnostic.SharePreerences.SharedPreferencesManager;

import java.util.HashMap;
import java.util.List;


public class About_app extends AppCompatActivity {
private ExpandableListView listView;
private ExpandableListAdapter listAdapter;
private List<String> listDataHeader;
private HashMap<String, List<String>> listHash;
    private SharedPreferencesManager prefs; //added
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new SharedPreferencesManager(this); //get SharedPreferencesManager  instance

        int t = prefs.retrieveInt("theme", 1); //get stored theme, zero is default

        Utils.setTheme(t);  //Set the stored theme, will default to Black
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_about_app);

        TextView abouttxt= findViewById(R.id.opis_about);
        String opis = getString(R.string.about_opis);
        TextView contactUs=findViewById(R.id.contact_us);
        TextView openSource=findViewById(R.id.open_source);


//        Button buttonEmail =findViewById(R.id.buttonEmail);
//        buttonEmail.getBackground().setAlpha(0);


        openSource.setText(
                Html.fromHtml(

                        "<centre>Icons made by</centre>" +
                                "<br><a href=\"http://www.freepik.com\">Freepik</a></br>" +
                                "<br><a href=\"https://www.flaticon.com/authors/monkik\">monkik</a></br>"+
                                "<br><a href=\"https://www.flaticon.com/authors/kirill-kazachek\">Kirill Kazachek</a></br>"+
                                "<br><a href=\"https://www.flaticon.com/authors/roundicons\">Roundicons</a></br>" +
                                "<br><a href=\"https://www.flaticon.com/authors/nikita-golubev\">Nikita Golubev</a></br>" +
                                "<br><a href=\"https://www.flaticon.com/authors/gregor-cresnar\">Gregor Cresnar</a></br>"+
                                "<br>From </br>"+"<a href=\"https://www.flaticon.com\">Flaticon</a> "+"is licensed by " +"<a href=\"http://creativecommons.org/licenses/by/3.0\"><br>Creative Commons BY 3.0</a> "+"CC 3.0 BY.</br>"
                                ));
      openSource.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString ss = new SpannableString(opis);


//        ClickableSpan clickableSpan1 = new ClickableSpan() {
//
//            @Override
//            public void onClick(View widget) {
////
//                Toast.makeText(About_app.this, "dupa", Toast.LENGTH_SHORT).show();
//
//            }
////
////            @Override
////            public void updateDrawState(TextPaint ds) {
////                super.updateDrawState(ds);
////                ds.setColor(Color.BLUE);
////                ds.setUnderlineText(false);
////            }
//        };
//        ss.setSpan(clickableSpan1, 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        abouttxt .setText(Html.fromHtml(getString(R.string.about_opis)));
//        openSource.setText(Html.fromHtml(getString(R.string.licencje_open_source)));
//        openSource.setMovementMethod(LinkMovementMethod.getInstance());
//        abouttxt.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void Facebook(View view) {
        Intent browserintent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://pl-pl.facebook.com/LubelskiKlubHondy/"));
        startActivity(browserintent);
    }
    public void HTCgarage(View view) {
        Intent browserintent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/HTCgarage/"));
        startActivity(browserintent);
    }

public void Email(View view) {

    Intent intent = null, chooser = null;

      if (view.getId()==R.id.contact_us)
      {
          intent=new Intent(Intent.ACTION_SEND);
          intent.setData(Uri.parse("mailto:"));
          String[] to = {"foxcodelab@gmail.com"};
          intent.putExtra(Intent.EXTRA_EMAIL, to);
          intent.putExtra(Intent.EXTRA_SUBJECT, "Honda Nation question");
          intent.setType("message/rfc822");
          chooser = Intent.createChooser(intent, "Send Email");
          startActivity(chooser);
      }

    }


}
