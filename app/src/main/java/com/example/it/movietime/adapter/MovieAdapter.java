package com.example.it.movietime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.it.movietime.R;
import com.example.it.movietime.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mangpali on 12/9/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Movie>list=new ArrayList<>();
    private View.OnClickListener onClickListener;

    public MovieAdapter(List<Movie>list){
        this.list=list;
    }

    public void setOnClickListener (View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int type=getItemViewType(position);
        ((MovieViewHolder)holder).bind(list.get(position),position,onClickListener);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
