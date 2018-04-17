package com.ryandro.viewpagersliderdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int[] image = {R.drawable.n_one, R.drawable.n_two, R.drawable.n_three, R.drawable.n_four, R.drawable.n_five};
//    private int[] image = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private ArrayList<Integer> arrayListImages = new ArrayList<>();
    private ImagePagerAdapter pagerAdapter;
    private ViewPager mviewPager;
    private int pageCount = 0;
    LinearLayout ll_indicatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < image.length; i++)
            arrayListImages.add(image[i]);
        mviewPager = (ViewPager) findViewById(R.id.vp_images);
        ll_indicatorLayout = (LinearLayout) findViewById(R.id.ll_indicatorLayout);

        pagerAdapter = new ImagePagerAdapter(MainActivity.this, arrayListImages);
        mviewPager.setClipToPadding(false);
        mviewPager.setPageMargin(12);
        mviewPager.setAdapter(pagerAdapter);
        setIndicator(0);


        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (pageCount == arrayListImages.size()) {
                    pageCount = 0;
                }
                mviewPager.setCurrentItem(pageCount++);
                setIndicator(pageCount);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 2000, 2500);
    }

    private void setIndicator(int position) {
        ll_indicatorLayout.removeAllViews();
        for (int i = 0; i < arrayListImages.size(); i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.indicator_layout, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 10, 5, 30);
            view.setLayoutParams(layoutParams);
            if (position == i) {
                view.setBackgroundResource(R.drawable.dot_indicator_selected);
            } else {
                view.setBackgroundResource(R.drawable.dot_indicator);
            }
            ll_indicatorLayout.addView(view);

        }
    }
}
