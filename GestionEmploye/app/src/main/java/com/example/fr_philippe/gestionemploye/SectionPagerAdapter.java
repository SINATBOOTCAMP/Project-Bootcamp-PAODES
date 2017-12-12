package com.example.fr_philippe.gestionemploye;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by FR-PHILIPPE on 11/26/2017.
 */

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public SectionPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }
    //Retourne le nombre de fragment
    @Override
    public int getCount() {
        return 3;
    }

    //retourne le fragment a utiliser selon sa posotion
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //HomeServicesChart tab1=new HomeServicesChart();
                //Drawable image = ContextCompat.getDrawable(tab1, images[position]);
                return null;
            case 1:
                //SearchTimeline tab2=new SearchTimeline();
                return null;
            case 2:
                //NotificationTimeline tab3=new NotificationTimeline();
                return null;
        }
        return null;
    }
}
