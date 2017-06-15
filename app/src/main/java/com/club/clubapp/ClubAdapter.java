package com.club.clubapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {
    
    private ArrayList<ClubBean> clubList = new ArrayList<ClubBean>();
    private OnClubClickListener onClubListClickListener;
    Context mContext;
    
    public ClubAdapter(ArrayList<ClubBean> clubList, Context mContext, OnClubClickListener onClubListClickListener){
        this.clubList = clubList;
        this.mContext = mContext;
        this.onClubListClickListener = onClubListClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.club_view_item, parent, false);
        MyViewHolder myHolder = new MyViewHolder(cv);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ClubBean club = clubList.get(position);
        CardView cardView = holder.cardView;
        TextView textClubName = (TextView) cardView.findViewById(R.id.club_name);
        TextView textClubLoc = (TextView) cardView.findViewById(R.id.club_loc);
        TextView textClubRating = (TextView) cardView.findViewById(R.id.club_rating);
        
        textClubName.setText(club.getClubName());
        textClubLoc.setText(club.getClubLoc());
        textClubRating.setText(club.getClubRating());
        
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                onClubListClickListener.onClicked(club);
            }
        });
    }
    
    public interface OnClubClickListener{
        void onClicked(ClubBean club);
    }
//    public void setOnClickListener(OnClubClickListener onClubListClickListener) {
//        this.onClubListClickListener = onClubListClickListener;
//    }
    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        
        public MyViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }
}
