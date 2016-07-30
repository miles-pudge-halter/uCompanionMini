package com.uit.ucompanion.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 7/29/2016.
 */
public class MySpinnerAdapter extends ArrayAdapter<String> {
    Context context;

    public MySpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTextColor(Color.parseColor("#ffffff"));
        view.setTextSize(18);
        return view;
    }

    // Affects opened state of the spinner
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
//            view.setTypeface(font);
//            view.setBackgroundResource(R.drawable.spinner_ripple);
        view.setTextSize(18);
        view.setTextColor(Color.parseColor("#222222"));
        return view;
    }
}
