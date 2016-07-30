package com.uit.ucompanion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uit.ucompanion.R;
import com.uit.ucompanion.adapters.MyRecyclerViewAdapter;
import com.uit.ucompanion.classes.TinyDB;
import com.uit.ucompanion.objects.DataObject;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab2 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private DataObject[] dataArr = new DataObject[7];

    String path = "";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);

        TinyDB tinyDB = new TinyDB(getContext());
        String year = tinyDB.getString("year");
        String major = tinyDB.getString("major");
        String section = tinyDB.getString("section");

        if (major.equals("")) {
            path = year + "/" + section;
        } else {
            path = year + "/" + major + "/" + section;
        }

        for (int j = 0; j < 7; j++)
            dataArr[j] = new DataObject("", "", "", "Free");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Timetable/" + path + "/Tuesday");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //data=new DataObject[(int)dataSnapshot.getChildrenCount()];
                Log.d("total count-----", Long.toString(dataSnapshot.getChildrenCount()));
                for (DataSnapshot classShot : dataSnapshot.getChildren()) {
                    dataArr[Integer.parseInt(classShot.getKey()) - 1] = classShot.getValue(DataObject.class);
                    //Log.d("HERE IT IS" + data.getTitle(), ">>>>>" + data.getLecturer());
                    //fbValue = dataSnapshot.getValue(String.class);
                }
                mAdapter = new MyRecyclerViewAdapter(getDataSet(), 2);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("HEYYY", "SHIT FAILED");
            }
        });

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet(), 1);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 7; index++) {
            DataObject obj = new DataObject(dataArr[index].getLecturer(), dataArr[index].getName(), dataArr[index].getRoom(), dataArr[index].getSubjID());
            results.add(index, obj);
        }
        return results;
    }
}
