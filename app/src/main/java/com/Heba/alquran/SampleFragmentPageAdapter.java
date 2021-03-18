package com.Heba.alquran;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SampleFragmentPageAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "تلاوة","حفظ واستماع"};
    private Context context;


    public SampleFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TelawaaFragment();
        } else {
            return new HefzFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
