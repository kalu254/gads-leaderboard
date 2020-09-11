package com.kalu.leaderboard.ui.main.adapters;

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
import com.kalu.leaderboard.models.SkillIqLeader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SkillLeaderAdapter extends  RecyclerView.Adapter<SkillLeaderAdapter.Leader> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    private List<SkillIqLeader> mSkillIqLeaders = new ArrayList<>();

    public SkillLeaderAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Leader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View leader = mLayoutInflater.inflate(R.layout.skill_leader, parent, false);
        return new Leader(leader);
    }

    @Override
    public void onBindViewHolder(@NonNull Leader holder, int position) {

        String imgUri = mSkillIqLeaders.get(position).getBadgeUrl();
        Uri url = Uri.parse(imgUri);

        Picasso.get().load(url).into(holder.img);

        holder.name.setText(mSkillIqLeaders.get(position).getName());
        holder.skills_country.setText(mSkillIqLeaders.get(position).getScore() + " skill iq score , " +
            mSkillIqLeaders.get(position).getCountry());

    }

    @Override
    public int getItemCount() {
        return mSkillIqLeaders.size();
    }

     public void setValuesToList(List<SkillIqLeader> skillIqLeaders) {
        mSkillIqLeaders = skillIqLeaders;
        notifyDataSetChanged();
    }

    public class Leader extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        TextView skills_country;

        public Leader(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_skills);
            name = itemView.findViewById(R.id.txt_name_skills);
            skills_country = itemView.findViewById(R.id.txt_skills_country);


        }
    }
}
