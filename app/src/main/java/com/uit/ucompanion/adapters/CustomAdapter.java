package com.uit.ucompanion.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.uit.ucompanion.R;
import com.uit.ucompanion.classes.TinyDB;
import com.uit.ucompanion.objects.Lectures;

/**
 * Created by dell on 7/28/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;

    private Lectures[] items;
    private int lastPosition = -1;
    private boolean[] markToggle;
    private String subject;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvFileSize;
        FrameLayout container;
        LinearLayout hidPart, clickView;
        Button btnDownload;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (FrameLayout) itemView.findViewById(R.id.item_layout_container);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            hidPart = (LinearLayout) itemView.findViewById(R.id.hidPart);
            clickView = (LinearLayout) itemView.findViewById(R.id.clickView);
            tvFileSize = (TextView) itemView.findViewById(R.id.tvFileSize);
            btnDownload = (Button) itemView.findViewById(R.id.btnDownload);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }

    }

    public CustomAdapter(Lectures[] items, Context context, String subject) {
        this.items = items;
        this.context = context;
        this.subject = subject;
        markToggle = new boolean[this.items.length];

        for (int i = 0; i < markToggle.length; i++) {
            markToggle[i] = false;
        }
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(items[position].getTitle());
        holder.tvFileSize.setText(items[position].getSize());

        if (markToggle[position] == false) {
            holder.hidPart.setVisibility(View.GONE);
            holder.tvTitle.setMaxLines(1);
        } else {
            holder.hidPart.setVisibility(View.VISIBLE);
            holder.tvTitle.setMaxLines(5);
        }

        holder.clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (markToggle[position] == true) {
                    holder.hidPart.setVisibility(View.GONE);
                    holder.tvTitle.setMaxLines(1);
                } else {
                    holder.hidPart.setVisibility(View.VISIBLE);
                    holder.tvTitle.setMaxLines(5);
                }

                markToggle[position] = !markToggle[position];

            }
        });

        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(items[position].getFile_url().toString()));
                context.startActivity(i);
            }
        });

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }
}
