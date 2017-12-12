package com.example.fr_philippe.gestionemploye;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    List<DataItem> lstData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lstData=new ArrayList<>();
        lstData.add(new DataItem(R.drawable.ic_perm_identity_black_24dp,"Username"));
        lstData.add(new DataItem(R.drawable.ic_phone_black_24dp,"Phone Number"));
        lstData.add(new DataItem(R.drawable.ic_mail_black_24dp,"Email"));

        ListView listView=(ListView)findViewById(R.id.lstProfil);
        CustomAdapter adapter=new CustomAdapter(this,R.layout.item_row,lstData);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
