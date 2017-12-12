package com.example.fr_philippe.gestionemploye;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by FR-PHILIPPE on 11/25/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private int[] mResources;
    private Integer[] images={R.drawable.img,R.drawable.img2,R.drawable.img3,R.drawable.img4};

    public ViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {return images.length;}

    @Override
    public boolean isViewFromObject(View view, Object object) {return view ==  object;}

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
        layoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.pager_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
