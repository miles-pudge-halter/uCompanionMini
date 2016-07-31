package com.uit.ucompanion.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uit.ucompanion.R;
import com.uit.ucompanion.adapters.MySpinnerAdapter;
import com.uit.ucompanion.adapters.Utils;
import com.uit.ucompanion.classes.TinyDB;
import com.uit.ucompanion.fragments.AssignmentsFragment;
import com.uit.ucompanion.fragments.Events;
import com.uit.ucompanion.fragments.LectureSlidesFragment;
import com.uit.ucompanion.fragments.Timetable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spinner;
    DrawerLayout drawer;
    NavigationView navigationView;
    AppBarLayout appBarLayout;

    static String type = "";
    String[] subjects;

    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    int themeInt;

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {

            database.getInstance().setPersistenceEnabled(true);

        } catch (DatabaseException e) {

        }

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        super.onCreate(savedInstanceState);

        Utils.onActivityCreateSetTheme(this);
        tinyDB = new TinyDB(getApplicationContext());
        themeInt = tinyDB.getInt("theme");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //tinyDB = new TinyDB(getApplicationContext());

//        putDataToDB();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            if (tinyDB.getString("year").equals(null) || tinyDB.getString("year").equals("")) {
                finish();
                Intent intent = new Intent(MainActivity.this, MainIntro.class);
                startActivity(intent);
            }
        } else {
            finish();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        }

        declare();

        setSubjectsInSpinner();

        writeUser();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sub = spinner.getSelectedItem().toString();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_content);
                if (fragment != null && fragment instanceof LectureSlidesFragment) {
                    LectureSlidesFragment lsf = new LectureSlidesFragment(sub);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, lsf).commit();
                } else if (fragment != null && fragment instanceof AssignmentsFragment) {
                    AssignmentsFragment af = new AssignmentsFragment(sub);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, af).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_content, new Timetable()).commit();
        setTitle("Time Table");
        spinner.setVisibility(View.GONE);
        setAppBarElevation(0);
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        spinner.setVisibility(View.INVISIBLE);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new Fragment();

        if (id == R.id.time_table) {
            fragment = new Timetable();
            setTitle("Timetable");
            setAppBarElevation(0);
            fm.beginTransaction().replace(R.id.frame_content, fragment).commit();
        } else if (id == R.id.events) {
            fragment = new Events();
            setTitle("Events");
            setAppBarElevation(8);
            fm.beginTransaction().replace(R.id.frame_content, fragment).commit();
        } else if (id == R.id.slides) {
            fragment = new LectureSlidesFragment(spinner.getSelectedItem().toString());
            spinner.setVisibility(View.VISIBLE);
            setTitle("");
            setAppBarElevation(8);
            fm.beginTransaction().replace(R.id.frame_content, fragment).commit();
        } else if (id == R.id.assignments) {
            fragment = new AssignmentsFragment(spinner.getSelectedItem().toString());
            spinner.setVisibility(View.VISIBLE);
            setTitle("");
            setAppBarElevation(8);
            fm.beginTransaction().replace(R.id.frame_content, fragment).commit();
        } else if (id == R.id.log_out) {

            setAppBarElevation(0);

            if (getTitle().equals("")) {
                spinner.setVisibility(View.VISIBLE);
            } else {
                spinner.setVisibility(View.GONE);
            }

            new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Are you sure to log out?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseAuth.getInstance().signOut();

                            tinyDB.remove("year");
                            tinyDB.remove("major");
                            tinyDB.remove("section");
                            tinyDB.clear();

                            finish();
                            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", null).show();
        } else if (id == R.id.theme_change) {
            setAppBarElevation(0);
            showThemeDialog();

            if (getTitle().equals("")) {
                spinner.setVisibility(View.VISIBLE);
            } else {
                spinner.setVisibility(View.GONE);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void declare() {
        spinner = (Spinner) findViewById(R.id.spinner);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

    }

    private void setSubjectsInSpinner() {

        String year = tinyDB.getString("year");
        switch (year) {
            case "First":
                subjects = getResources().getStringArray(R.array.first);
                break;
            case "Second":
                subjects = getResources().getStringArray(R.array.second);
                break;
            case "Third":
                subjects = getResources().getStringArray(R.array.third);
                break;
            case "Fourth":
                subjects = getResources().getStringArray(R.array.fourth);
                break;
            default:
                subjects = getResources().getStringArray(R.array.first);
        }

        List<String> subjs = new ArrayList<>();

        for (int i = 0; i < subjects.length; i++) {
            subjs.add(subjects[i]);
        }

        MySpinnerAdapter dataAdapter = new MySpinnerAdapter(MainActivity.this, android.R.layout.simple_spinner_item, subjs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void setAppBarElevation(float elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(elevation);
        }
    }

    private void writeUser() {

        View headerView = navigationView.getHeaderView(0);
        TextView tvUsername = (TextView) headerView.findViewById(R.id.tvUsername);
        TextView tvEmail = (TextView) headerView.findViewById(R.id.tvEmail);
        if (themeInt == Utils.THEME_PINK)
            headerView.setBackgroundColor(getResources().getColor(R.color.colorPink));
        else if (themeInt == Utils.THEME_BLUE)
            headerView.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        else
            headerView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        String username = tinyDB.getString("username");
        String email = tinyDB.getString("email");
        String year = tinyDB.getString("year") + " Year";
        String major = tinyDB.getString("major");
        String section = tinyDB.getString("section");

        String classYear = year;

        if (!major.equals("")) {
            classYear += "-" + major;
        }

        if (!section.equals("")) {
            classYear += "-" + section;
        }

        tvUsername.setText(username + " (" + classYear + ")");
        tvEmail.setText(email);
    }

    public void showThemeDialog() {
        DialogFragment newDialog = new ThemeDialogFragment();
        newDialog.show(getSupportFragmentManager(), "Themes");
    }

    public static class ThemeDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Theme")
                    .setItems(R.array.theme_arrays, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TinyDB tinyDB = new TinyDB(getActivity());
                            if (which == 0) {
                                Utils.changeToTheme(getActivity(), Utils.THEME_PINK);
                                tinyDB.putInt("theme", Utils.THEME_PINK);
                            } else if (which == 2) {
                                Utils.changeToTheme(getActivity(), Utils.THEME_DEFAULT);
                                tinyDB.putInt("theme", Utils.THEME_DEFAULT);
                            } else {
                                Utils.changeToTheme(getActivity(), Utils.THEME_BLUE);
                                tinyDB.putInt("theme", Utils.THEME_BLUE);
                            }
                        }
                    });
            return builder.create();
        }
    }

//    private void putDataToDB() {
//        Intent i = getIntent();
//        if (i != null) {
//            boolean putData = i.getBooleanExtra("putData", false);
//            System.out.println("putData : " + putData);
//            if (putData) {
//                String keyYear = tinyDB.getString("year");
//                String keyMajor = tinyDB.getString("major");
//                String keySection = tinyDB.getString("section");
//
//                databaseReference.child("users").child(user.getUid()).child("year").setValue(keyYear);
//                databaseReference.child("users").child(user.getUid()).child("major").setValue(keyMajor);
//                databaseReference.child("users").child(user.getUid()).child("section").setValue(keySection);
//            }
//        }
//    }
}
