package com.uit.ucompanion.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uit.ucompanion.R;
import com.uit.ucompanion.adapters.ViewPagerAdapter;
import com.uit.ucompanion.classes.SlidingTabLayout;

public class Timetable extends Fragment {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    int NumOfTabs = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_timetable, container, false);
        getActivity().setTitle("Timetable");

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), Titles, NumOfTabs);

        if (adapter == null) {
            System.out.println("adapter is null");
        }

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) v.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(android.R.color.white);
            }
        });

        tabs.setViewPager(pager);
        return v;
    }

}
