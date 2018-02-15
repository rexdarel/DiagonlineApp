package com.fungeonstudio.diagonline.recycler;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fungeonstudio.diagonline.R;
import com.fungeonstudio.diagonline.utils.CircleGlide;

import java.util.List;

/**
 * Created by Admin on 2/15/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    private List<ItemReview> items;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView username, date, comment;
        public ImageView img1, img2, userphoto;
        public RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);

            username = (TextView) view.findViewById(R.id.tv_username);
            date = (TextView) view.findViewById(R.id.tv_date);
            comment = (TextView) view.findViewById(R.id.tv_text_comment);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            userphoto = (ImageView) view.findViewById(R.id.iv_user);
            img1 = (ImageView) view.findViewById(R.id.imageview1);
            img2 = (ImageView) view.findViewById(R.id.imageview2);
        }

    }

    public ReviewAdapter(List<ItemReview> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, int position) {
        ItemReview itemReview = items.get(position);
        holder.username.setText(itemReview.getAuthor().toString());
        holder.ratingBar.setRating(itemReview.getRate());
        //Log.w("Review: Submitted" ,itemReview.getMessage());
       // holder.date.setText(itemReview.getTimestamp().toString());
        holder.comment.setText(itemReview.getMessage().toString());

        /*Glide.with(context)
                .load(Uri.parse(itemComment.getUserphoto()))
                .transform(new CircleGlide(context))
                .into(holder.userphoto);
        Glide.with(context)
                .load(Uri.parse(itemComment.getImg1()))
                .centerCrop()
                .into(holder.img1);
        Glide.with(context)
                .load(Uri.parse(itemComment.getImg2()))
                .centerCrop()
                .into(holder.img2);*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
