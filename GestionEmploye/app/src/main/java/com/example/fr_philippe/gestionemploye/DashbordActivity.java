package com.example.fr_philippe.gestionemploye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DashbordActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    showFragment(new HomeServicesChart());
                    return true;
                case R.id.menu_activite:
                    showFragment(new SearchServices());
                    return true;
                case R.id.menu_notifications:
                    showFragment(new NotificationServices());
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        showFragment(new HomeServicesChart());

        //Creation et initialisation d'un toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //slide menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //NavigationBottom


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashbord, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        View mv = null;
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
            viewProfil(mv);
        } else if (id == R.id.nav_people) {
            newOmployed(mv);
        } else if (id == R.id.nav_payroll) {
            newPayroll(mv);
        } else if (id == R.id.nav_conge) {
            newConge(mv);
        } else if (id == R.id.nav_fonction) {

        } else if (id == R.id.nav_promotion) {
            newPromotion(mv);
        } else if (id == R.id.nav_money) {
            newSalaire(mv);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    public void newOmployed(View v){
        Intent p=new Intent(this,ListEmployeActivity.class);
        startActivity(p);
    }

    public void newConge(View v){
        Intent p=new Intent(this,CongeActivity.class);
        startActivity(p);
    }
    public void viewProfil(View v){
        Intent p=new Intent(this,ProfilActivity.class);
        startActivity(p);
    }
    public void newPayroll(View v){
        Intent p=new Intent(this,PayrollActivity.class);
        startActivity(p);
    }

    public void newPromotion(View v){
        Intent p=new Intent(this,PromotionActivity.class);
        startActivity(p);
    }

    public void newSalaire(View v){
        Intent p=new Intent(this,PayrollActivity.class);
        startActivity(p);
    }
}
