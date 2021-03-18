package com.Heba.alquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
public class HomePage extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPageAdapter(getSupportFragmentManager(), HomePage.this));

        // Give the TabLayout the ViewPager
        tabLayout  =  findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
