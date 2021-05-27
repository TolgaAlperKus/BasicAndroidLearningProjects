package com.tolgaalperkus.swipeview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DemoFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    public DemoFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DemoFragment demoFragment = new DemoFragment();
        Bundle bundle = new Bundle();
        position = position +1;
        bundle.putString("message", "Page :" +position);
        demoFragment.setArguments(bundle);

        return demoFragment;
    }

    @Override
    public int getCount() {
        return 100;
    }
}
