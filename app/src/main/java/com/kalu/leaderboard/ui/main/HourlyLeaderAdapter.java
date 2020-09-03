package com.kalu.leaderboard.ui.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalu.leaderboard.R;
import com.kalu.leaderboard.models.HourlyLeader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HourlyLeaderAdapter extends RecyclerView.Adapter<HourlyLeaderAdapter.Leader> {

   private final Context mContext;
   private final LayoutInflater mLayoutInflater;
   private List<HourlyLeader> mHourlyLeaders;

    public HourlyLeaderAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Leader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View leader = mLayoutInflater.inflate(R.layout.hourly_leader,parent,false);

        return new Leader(leader);
    }

    @Override
    public void onBindViewHolder(@NonNull Leader holder, int position) {

        String badgeUrl = mHourlyLeaders.get(position).getBadgeUrl();
        Uri imageUri = Uri.parse(badgeUrl);

        Picasso.get().load(imageUri).into(holder.img);

        holder.name.setText(mHourlyLeaders.get(position).getName());
        holder.hours_country.setText(mHourlyLeaders.get(position).getHours().toString() + " learning hours, "+
            mHourlyLeaders.get(position).getCountry());

    }

    @Override
    public int getItemCount() {
        return mHourlyLeaders.size();
    }

     void setList(List<HourlyLeader> hourlyLeaders) {
        mHourlyLeaders = hourlyLeaders;
        notifyDataSetChanged();
    }


    public class Leader extends RecyclerView.ViewHolder{

         ImageView img;
         TextView name;
         TextView hours_country;

        public Leader(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_hours);
            name = itemView.findViewById(R.id.txt_name_hours);
            hours_country = itemView.findViewById(R.id.txt_hours_country);
        }
    }
}
