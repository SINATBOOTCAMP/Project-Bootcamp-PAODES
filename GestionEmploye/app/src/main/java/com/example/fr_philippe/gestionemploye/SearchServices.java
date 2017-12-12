package com.example.fr_philippe.gestionemploye;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FR-PHILIPPE on 11/27/2017.
 */

public class SearchServices extends Fragment {
    public SearchServices(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_services, container, false);
        return rootView;
    }
}
