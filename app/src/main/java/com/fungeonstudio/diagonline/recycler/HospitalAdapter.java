package com.fungeonstudio.diagonline.recycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
//import com.fungeonstudio.diagonline.Detail;
import com.fungeonstudio.diagonline.DetailActivity;
import com.fungeonstudio.diagonline.R;
import com.fungeonstudio.diagonline.utils.CircleGlide;

import java.util.List;

/**
 * Created by Dytstudio.
 */

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> {

    private List<Hospitals> items;
    private Context context;
    private boolean active;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipe, time;
        public RatingBar ratingBar;
        public ImageView imageView;
        ItemClickListener itemClickListener;

        public MyViewHolder(View view) {
            super(view);

            recipe = (TextView) view.findViewById(R.id.tv_recipe_name);
            time = (TextView) view.findViewById(R.id.tv_time);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            imageView = (ImageView) view.findViewById(R.id.iv_recipe);

            view.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }


    public HospitalAdapter(List<Hospitals> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Hospitals itemHospital = items.get(position);
        holder.recipe.setText(itemHospital.getName());
        holder.time.setText(itemHospital.getLocation());
        holder.ratingBar.setRating(3);
        Glide.with(context)
                .load(itemHospital.getPhoto())
                .transform(new CircleGlide(context))
                .into(holder.imageView);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //OPEN DETAI ACTIVITY
                openDetailActivity(itemHospital.getName(),itemHospital.getLocation(),itemHospital.getContact(), itemHospital.getDescription(), itemHospital.getPhoto());
            }
        });
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(context, DetailActivity.class);

        i.putExtra("NAME",details[0]);
        i.putExtra("LOCATION",details[1]);
        i.putExtra("CONTACT",details[2]);
        i.putExtra("DESC",details[3]);
        i.putExtra("PHOTO", details[4]);

        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}