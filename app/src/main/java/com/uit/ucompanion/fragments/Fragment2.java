package com.uit.ucompanion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uit.ucompanion.R;
import com.uit.ucompanion.classes.TinyDB;
import com.uit.ucompanion.views.DownloadActivity;
import com.uit.ucompanion.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aYoe on 3/18/16.
 */
public class Fragment2 extends Fragment {
    private Spinner ySpinner, mSpinner, sSpinner;
    private Button btnSubmit;
    private String year, major, section, keyYear, keyMajor, keySection;
    private List<String> major1, major2;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.page2, container, false);

        database = FirebaseDatabase.getInstance();

        ref = database.getReference();

        keyYear = "";
        keySection = "";
        keyMajor = "";

        ySpinner = (Spinner) v.findViewById(R.id.yearSpinner);
        sSpinner = (Spinner) v.findViewById(R.id.sectionSpinner);
        mSpinner = (Spinner) v.findViewById(R.id.majorSpinner);
        btnSubmit = (Button) v.findViewById(R.id.button);

        major1 = new ArrayList<String>();
        major1.add("Select your major");
        major1.add("Computer Science");
        major1.add("Computer Technology");

        major2 = new ArrayList<String>();
        major2.add("Select your major");
        major2.add("Software Engineering");
        major2.add("Knowledge Engineering");
        major2.add("Business Information System");
        major2.add("High Performance Computing");
        major2.add("Communication & Networking");
        major2.add("Embedded Systems");

        //btnSubmit.setEnabled(false);

        ySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> mRelative, View selectedItemView, final int position, long id) {
                year = mRelative.getItemAtPosition(position).toString();
//                // your code here
                if (year.equals("First Year") || year.equals("Second Year")) {
                    if (year.equals("First Year")) {
                        keyYear = "First";
                        keySection = "";
                        keyMajor = "";
                    } else {
                        keyYear = "Second";
                        keySection = "";
                        keyMajor = "";
                    }

                    mSpinner.setVisibility(View.INVISIBLE);
                    btnSubmit.setVisibility(View.INVISIBLE);
                    sSpinner.setVisibility(View.VISIBLE);
                    sSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            section = adapterView.getItemAtPosition(i).toString();
                            if (section.equals("A")) keySection = "A";
                            if (!section.equals("Select your section")) {
                                switch (section) {
                                    case "None":
                                        keySection = "";
                                        break;
                                    case "A":
                                        keySection = "A";
                                        break;
                                    case "B":
                                        keySection = "B";
                                        break;
                                    case "C":
                                        keySection = "C";
                                        break;
                                    case "D":
                                        keySection = "D";
                                        break;
                                    case "E":
                                        keySection = "E";
                                        break;
                                }
                                btnSubmit.setVisibility(View.VISIBLE);
                            } else
                                btnSubmit.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (year.equals("Third Year")) {
                    keyYear = "Third";
                    keyMajor = "";
                    keySection = "";

                    btnSubmit.setVisibility(View.INVISIBLE);
                    sSpinner.setVisibility(View.INVISIBLE);
                    mSpinner.setVisibility(View.VISIBLE);

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, major1);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(dataAdapter);
                    mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            major = adapterView.getItemAtPosition(i).toString();
                            if (!major.equals("Select your major")) {
                                if (major.equals("Computer Science")) {
                                    keyMajor = "CS";
                                    keySection = "";
                                    sSpinner.setVisibility(View.VISIBLE);
                                    sSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            section = adapterView.getItemAtPosition(i).toString();
                                            if (!section.equals("Select your section")) {
                                                switch (section) {
                                                    case "None":
                                                        keySection = "";
                                                        break;
                                                    case "A":
                                                        keySection = "A";
                                                        break;
                                                    case "B":
                                                        keySection = "B";
                                                        break;
                                                    case "C":
                                                        keySection = "C";
                                                        break;
                                                    case "D":
                                                        keySection = "D";
                                                        break;
                                                    case "E":
                                                        keySection = "E";
                                                        break;
                                                }
                                                btnSubmit.setVisibility(View.VISIBLE);
                                            } else
                                                btnSubmit.setVisibility(View.INVISIBLE);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                } else if (major.equals("Computer Technology")) {
                                    keyMajor = "CT";
                                    keySection = "";
                                    sSpinner.setVisibility(View.INVISIBLE);
                                    btnSubmit.setVisibility(View.VISIBLE);
                                }

                            } else
                                sSpinner.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (year.equals("Fourth Year") || year.equals("Fifth Year")) {
                    btnSubmit.setVisibility(View.INVISIBLE);
                    sSpinner.setVisibility(View.INVISIBLE);
                    mSpinner.setVisibility(View.VISIBLE);
                    switch (year) {
                        case "Fourth Year":
                            keyYear = "Fourth";
                            keySection = "";
                            break;
                        case "Fifth Year":
                            keyYear = "Fifth";
                            keySection = "";
                            break;
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, major2);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(dataAdapter);
                    mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            major = adapterView.getItemAtPosition(i).toString();
                            if (!major.equals("Select your major")) {
                                switch (major) {
                                    case "Software Engineering":
                                        keyMajor = "CS/SE";
                                        break;
                                    case "Knowledge Engineering":
                                        keyMajor = "CS/KE";
                                        break;
                                    case "Business Information System":
                                        keyMajor = "CS/BIS";
                                        break;
                                    case "High Performance Computing":
                                        keyMajor = "CS/HPC";
                                        break;
                                    case "Communication & Networking":
                                        keyMajor = "CT/CN";
                                        break;
                                    case "Embedded Systems":
                                        keyMajor = "CT/ES";
                                        break;
                                }
                                btnSubmit.setVisibility(View.VISIBLE);
                            } else {
                                btnSubmit.setVisibility(View.INVISIBLE);
                                sSpinner.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                } else {
                    mSpinner.setVisibility(View.INVISIBLE);
                    sSpinner.setVisibility(View.INVISIBLE);
                    btnSubmit.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TinyDB tinyDb = new TinyDB(getContext());

                tinyDb.putString("year", keyYear);
                tinyDb.putString("major", keyMajor);
                tinyDb.putString("section", keySection);

                getActivity().finish();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

                ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("year").setValue(keyYear);
                ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("major").setValue(keyMajor);
                ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("section").setValue(keySection);

//                ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("year").setValue(keyYear, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                        ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("major").setValue(keyMajor, new DatabaseReference.CompletionListener() {
//                            @Override
//                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("section").setValue(keySection, new DatabaseReference.CompletionListener() {
//                                    @Override
//                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                    }
//                                });
//                            }
//                        });
//
//                        if (databaseError == null) {
//
//                        }
//                    }
//                });

            }
        });

        return v;
    }

}

