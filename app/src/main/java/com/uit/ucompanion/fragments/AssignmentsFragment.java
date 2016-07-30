package com.uit.ucompanion.fragments;


import android.annotation.SuppressLint;
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
import com.uit.ucompanion.adapters.CustomAdapter;
import com.uit.ucompanion.classes.DividerItemDecoration;
import com.uit.ucompanion.classes.TinyDB;
import com.uit.ucompanion.objects.Lectures;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentsFragment extends Fragment {

    TinyDB tinyDB;

    String subject;

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase database;
    DatabaseReference reference;

    Lectures[] lectures;

    public AssignmentsFragment() {

    }

    @SuppressLint("ValidFragment")
    public AssignmentsFragment(String subject) {
        this.subject = subject;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lecture_slides, container, false);

        tinyDB = new TinyDB(getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        database = FirebaseDatabase.getInstance();

        String year = tinyDB.getString("year");
        String major = tinyDB.getString("major");

        if (major.equals("")) {
            reference = database.getReference("Downloads/Assignments/" + year + "/" + subject);
        } else {
            reference = database.getReference("Downloads/Assignments/" + year + "/" + major + "/" + subject);
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lectures = new Lectures[(int) dataSnapshot.getChildrenCount()];

                int i = 0;

                for (DataSnapshot lectureSnapShot : dataSnapshot.getChildren()) {
                    lectures[(int) dataSnapshot.getChildrenCount() - i - 1] = lectureSnapShot.getValue(Lectures.class);
                    i++;
                }

                mAdapter = new CustomAdapter(lectures, getContext(), subject);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

}
