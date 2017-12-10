package com.example.it.movietime.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.it.movietime.R;
import com.example.it.movietime.constant.APIConfig;
import com.example.it.movietime.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Mangpali on 12/9/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView img;

    public MovieViewHolder(View itemView) {
        super(itemView);
        img=(ImageView)itemView.findViewById(R.id.iv_poster);
    }
    public void bind(final Movie data, final int position, final OnClickListener onClickListener){
        Picasso.with(itemView.getContext()).
                load(APIConfig.getMoviePosterURL("w342",
                        data.getPoster_path())).
                into(img);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(data,position);
            }
        });
    }
}
