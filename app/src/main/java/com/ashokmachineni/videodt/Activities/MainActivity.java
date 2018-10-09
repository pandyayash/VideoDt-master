package com.ashokmachineni.videodt.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokmachineni.videodt.Fragments.Fragment_Downloads;
import com.ashokmachineni.videodt.Fragments.Fragment_Home;
import com.ashokmachineni.videodt.R;

public class MainActivity extends AppCompatActivity {
    public int currentPosition = 0;
    private TextView mTextMessage;
    android.support.v4.app.Fragment fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(0);
                    return true;
                case R.id.navigation_download:
                    setFragment(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setFragment(0);
    }

    public void setFragment(int i) {
        //actionBar = getSupportActionBar();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        android.support.v4.app.Fragment fragment = null;

        currentPosition = i;
        switch (i) {
            case 0:
                fragment = new Fragment_Home();
                ft.replace(R.id.frame_container, fragment);
                ft.commit();
                break;
            case 1:
                fragment = new Fragment_Downloads();
                ft.replace(R.id.frame_container, fragment);
                ft.commit();
                break;

        }
    }


}
