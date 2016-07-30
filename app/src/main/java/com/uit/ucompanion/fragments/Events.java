package com.uit.ucompanion.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uit.ucompanion.R;
import com.uit.ucompanion.adapters.EventsRecyclerViewAdapter;
import com.uit.ucompanion.objects.EventObject;

import java.util.ArrayList;

/**
 * Created by aYoe on 7/18/16.
 */
public class Events extends Fragment {
    EventObject[] c;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_events, container, false);
        getActivity().setTitle("Events");
        //super.onCreate(savedInstanceState);
//        Utils.onActivityCreateSetTheme(this);
//        setContentView(R.layout.fragment_events);
//        TinyDB tinyDB=new TinyDB(getActivity());
//        int themeInt=tinyDB.getInt("theme");
//        if(themeInt==Utils.THEME_BLUE){
//            if(Build.VERSION.SDK_INT>=21)
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlueDark));
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBlue)));
//        }
//        else if(themeInt==Utils.THEME_DARK){
//            if(Build.VERSION.SDK_INT>=21)
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreyDark));
//            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGrey)));
//        }
//        else if(themeInt==Utils.THEME_TEAL){
//            if(Build.VERSION.SDK_INT>=21)
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorTealDark));
//            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorTeal)));
//        }
//        else{
//            if(Build.VERSION.SDK_INT>=21)
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
//        }
//
//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Events</font>"));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);

//        FloatingActionButton go_timetable=(FloatingActionButton) findViewById(R.id.go_timetable2);
//        go_timetable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getApplicationContext(),Timetable.class);
//                startActivity(intent);
//            }
//        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Events");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                c = new EventObject[(int) dataSnapshot.getChildrenCount()];

                int i = (int) dataSnapshot.getChildrenCount();
                for (DataSnapshot classShot : dataSnapshot.getChildren()) {
                    c[(int) dataSnapshot.getChildrenCount() - i] = classShot.getValue(EventObject.class);
                    i--;

                }
                mAdapter = new EventsRecyclerViewAdapter(getDataSet());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return v;
    }

    private ArrayList<EventObject> getDataSet() {
        ArrayList results = new ArrayList<EventObject>();

        int i = 0;
        for (EventObject cn : c) {
            EventObject obj = new EventObject(cn.getBody(), cn.getTitle(), cn.getEventDate());
            results.add(i, obj);
            i++;
        }

        return results;
    }
}
