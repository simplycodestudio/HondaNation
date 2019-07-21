package com.example.jacek.hondadiagnostic.Tipsy;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jacek.hondadiagnostic.R;


import java.io.ByteArrayOutputStream;

/**
 * Created by Jacek on 2018-06-14.
 */

public class SliderAdapter extends PagerAdapter {

    static Resources res = null;
    Context context;
    LayoutInflater layoutInflater;
    static Bitmap bitmap;




    public SliderAdapter(Context context)
    {
        this.context = context;
        slide_headings =  context.getResources().getStringArray(R.array.p06_naglowek);
        slide_descs = context.getResources().getStringArray(R.array.p06_opis);


    }

    public int [] slide_images = {
            R.drawable.komputer_startowe,
            R.drawable.ecu_version,
            R.drawable.potrzebne_elementy,
            R.drawable.co_gdzie,
            R.drawable.rm11,
            R.drawable.p06_finish


    };


    String[] slide_headings;

    String[] slide_descs;


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);


        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inSampleSize = 1;
//        o.inDither = false;
        o.inScaled = true;
        o.inDensity = 300;
        o.inTargetDensity = 400*o.inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), slide_images[position], o);



        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);



        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);
        slideImageView.setImageBitmap(bitmap);
        container.addView(view);



        return view;

    }










    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);

    }
}
