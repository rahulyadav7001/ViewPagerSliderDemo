package com.ryandro.viewpagersliderdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {
    private ArrayList<Integer> imageArrayList;
    LayoutInflater mInflater;
    Context mContext;

    public ImagePagerAdapter(Context mContext,ArrayList<Integer> imageArrayList) {
        this.imageArrayList = imageArrayList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;

    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }

    @Override
    public int getCount() {
        if (imageArrayList != null && imageArrayList.size() > 0)
            return imageArrayList.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.viewpager_cell,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        imageView.setImageResource(imageArrayList.get(position));
        container.addView(view);
        return view;
    }

}
