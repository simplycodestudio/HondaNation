package com.example.jacek.hondadiagnostic;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Jacek on 2017-12-08.
 */

public class CustomListview extends ArrayAdapter<String> {

    private Integer[] GIF;
    private String[] NUMBERS;
    private String[] OPISY;
    private Activity context;
    public CustomListview(Activity context, String[] NUMBERS, String[] OPISY, Integer[] GIF) {
        super(context, R.layout.listview_kody_bledow,NUMBERS);

        this.context=context;
        this.NUMBERS=NUMBERS;
        this.OPISY=OPISY;
        this.GIF=GIF;
        }
        @NonNull
        @Override
   public View getView(int position, @Nullable View covertView,@NonNull ViewGroup parent) {
        View r=covertView;
        ViewHolder viewHolder=null;
        if(r==null) {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_kody_bledow, null, true);
            viewHolder=new ViewHolder (r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) r.getTag ();

        }
        viewHolder.ivw.setImageResource(GIF[position]);
        viewHolder.tvw1.setText(NUMBERS[position]);
        viewHolder.tvw2.setText(OPISY[position]);
        return r;
        }
class ViewHolder {
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder (View v)
        {
            tvw1= (TextView) v.findViewById(R.id.numer_bledu);
            tvw2= (TextView) v.findViewById(R.id.opis_bledu);
            ivw=(ImageView) v.findViewById(R.id.imageView);

        }
}
}
