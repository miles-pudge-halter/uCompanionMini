package com.uit.ucompanion.adapters;

/**
 * Created by aYoe on 7/11/16.
 */

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.uit.ucompanion.R;
import com.uit.ucompanion.objects.DataObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private int tabN = 0;
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        CardView card;
        FrameLayout frame;
        TextView subj_name;
        TextView subj_code;
        TextView subj_room;
        TextView subj_time;
        TextView subj_lecturer;

        public DataObjectHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card_view);
            frame = (FrameLayout) itemView.findViewById(R.id.frame);
            subj_name = (TextView) itemView.findViewById(R.id.subjName);
            subj_code = (TextView) itemView.findViewById(R.id.subjCode);
            subj_room = (TextView) itemView.findViewById(R.id.room);
            subj_time = (TextView) itemView.findViewById(R.id.time);
            subj_lecturer = (TextView) itemView.findViewById(R.id.subjLecturer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;

    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset, int i) {
        mDataset = myDataset;
        tabN = i;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.subj_code.getText().equals("Free")) {
                    if (holder.subj_lecturer.getVisibility() == View.GONE) {
                        holder.subj_lecturer.setVisibility(View.VISIBLE);
                        holder.subj_name.setMaxLines(2);
                    } else {
                        holder.subj_lecturer.setVisibility(View.GONE);
                        holder.subj_name.setMaxLines(1);
                    }
                }
            }
        });

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int day = c.get(Calendar.DAY_OF_WEEK);

        holder.subj_name.setText(mDataset.get(position).getName());
        holder.subj_code.setText(mDataset.get(position).getSubjID());
        holder.subj_lecturer.setText(mDataset.get(position).getLecturer());
        if (!mDataset.get(position).getSubjID().equals("Free"))
            holder.subj_room.setText("Room - " + mDataset.get(position).getRoom());
        else
            holder.subj_room.setText(mDataset.get(position).getRoom());
        switch (position) {
            case 0:
                holder.subj_time.setText("08:30-09:20");
                break;
            case 1:
                holder.subj_time.setText("09:30-10:20");
                break;
            case 2:
                holder.subj_time.setText("10:30-11:20");
                break;
            case 3:
                holder.subj_time.setText("12:00-12:50");
                break;
            case 4:
                holder.subj_time.setText("01:00-01:50");
                break;
            case 5:
                holder.subj_time.setText("02:00-02:50");
                break;
            case 6:
                holder.subj_time.setText("03:00-03:50");
                break;
        }
        if (!mDataset.get(position).getSubjID().equals("Free")) {

            ViewGroup.LayoutParams param = holder.frame.getLayoutParams();
            switch (tabN) {
                case 1:
                    if (day == 2)
                        switch (position) {
                            case 0:
                                if (hour == 8 && minute >= 30 || hour == 9 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 1:
                                if (hour == 9 && minute >= 30 || hour == 10 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 2:
                                if (hour == 10 && minute >= 30 || hour == 11 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 3:
                                if (hour == 0) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 4:
                                if (hour == 1) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 5:
                                if (hour == 2) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 6:
                                if (hour == 3) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                        }
                    break;
                case 2:
                    if (day == 3)
                        switch (position) {
                            case 0:
                                if (hour == 8 && minute >= 30 || hour == 9 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 1:
                                if (hour == 9 && minute >= 30 || hour == 10 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 2:
                                if (hour == 10 && minute >= 30 || hour == 11 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 3:
                                if (hour == 0) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 4:
                                if (hour == 1) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 5:
                                if (hour == 2) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 6:
                                if (hour == 3) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                        }
                    break;
                case 3:
                    if (day == 4)
                        switch (position) {
                            case 0:
                                if (hour == 8 && minute >= 30 || hour == 9 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 1:
                                if (hour == 9 && minute >= 30 || hour == 10 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 2:
                                if (hour == 10 && minute >= 30 || hour == 11 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 3:
                                if (hour == 0) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 4:
                                if (hour == 1) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 5:
                                if (hour == 2) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 6:
                                if (hour == 3) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                        }
                    break;
                case 4:
                    if (day == 5)
                        switch (position) {
                            case 0:
                                if (hour == 8 && minute >= 30 || hour == 9 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 1:
                                if (hour == 9 && minute >= 30 || hour == 10 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 2:
                                if (hour == 10 && minute >= 30 || hour == 11 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 3:
                                if (hour == 0) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 4:
                                if (hour == 1) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 5:
                                if (hour == 2) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 6:
                                if (hour == 3) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                        }
                    break;
                case 5:
                    if (day == 6)
                        switch (position) {
                            case 0:
                                if (hour == 8 && minute >= 30 || hour == 9 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 1:
                                if (hour == 9 && minute >= 30 || hour == 10 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 2:
                                if (hour == 10 && minute >= 30 || hour == 11 && minute <= 30) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 3:
                                if (hour == 0) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 4:
                                if (hour == 1) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 5:
                                if (hour == 2) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                            case 6:
                                if (hour == 3) {
                                    param.width = 40;
                                    holder.frame.setLayoutParams(param);
                                }
                                break;
                        }
                    break;
            }
            if (mDataset.get(position).getName().equals("Tutorial") || mDataset.get(position).getName().equals("Presentation")) {
                holder.frame.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.card.setCardBackgroundColor(Color.parseColor("#333333"));
                holder.subj_code.setTextColor(Color.parseColor("#ffffff"));
                holder.subj_room.setTextColor(Color.parseColor("#ffffff"));
                holder.subj_time.setTextColor(Color.parseColor("#ffffff"));
                holder.subj_name.setTextColor(Color.parseColor("#ffffff"));
            }
            if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("1"))
                holder.frame.setBackgroundColor(Color.parseColor("#2196f3"));//bluelight
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("2"))
                holder.frame.setBackgroundColor(Color.parseColor("#ef5350"));//redlight
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("3"))
                holder.frame.setBackgroundColor(Color.parseColor("#7e57c2"));//purple
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("4"))
                holder.frame.setBackgroundColor(Color.parseColor("#009688"));//teal
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("5"))
                holder.frame.setBackgroundColor(Color.parseColor("#43a047"));//greenlight
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("6"))
                holder.frame.setBackgroundColor(Color.parseColor("#607d8b"));//amber;
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("G"))
                holder.frame.setBackgroundColor(Color.parseColor("#4dd0e1"));//cyan
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("M"))
                holder.frame.setBackgroundColor(Color.parseColor("#ffc107"));//yellow
            else if (mDataset.get(position).getSubjID().substring(mDataset.get(position).getSubjID().length() - 1).equals("Y"))
                holder.frame.setBackgroundColor(Color.parseColor("#3f51b5"));//indigo
        }
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
