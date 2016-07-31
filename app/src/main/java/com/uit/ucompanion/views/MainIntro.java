package com.uit.ucompanion.views;

import android.animation.ArgbEvaluator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.uit.ucompanion.R;
import com.uit.ucompanion.fragments.Fragment1;
import com.uit.ucompanion.fragments.Fragment2;

public class MainIntro extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_startup);

        System.out.println("Called MainIntro here!");

        if (Build.VERSION.SDK_INT < 20) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new CustomOnPageChangeListener());

        setUpColors();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void setUpColors() {

        Integer color1 = getResources().getColor(R.color.color1);
        Integer color2 = getResources().getColor(R.color.color2);
        Integer[] colors_temp = {color1, color2};
        colors = colors_temp;

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                default:
                    return new Fragment1();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "A";
        }
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflaterr, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflaterr.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }

    private class CustomOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (position < (mSectionsPagerAdapter.getCount() - 1) && position < (colors.length - 1)) {

                mViewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setNavigationBarColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                    getWindow().setStatusBarColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                }

            } else {
                // the last page color
                mViewPager.setBackgroundColor(colors[colors.length - 1]);
            }
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }


    }

}

