package com.kalu.leaderboard.ui.main.interface_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalu.leaderboard.FetchData.LeadersApi;
import com.kalu.leaderboard.FetchData.ServiceBuilder;
import com.kalu.leaderboard.R;
import com.kalu.leaderboard.models.SkillIqLeader;
import com.kalu.leaderboard.ui.main.view_models.SkillViewModel;
import com.kalu.leaderboard.ui.main.adapters.SkillLeaderAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqLeaderFragment extends Fragment {

    private SkillViewModel mSkillViewModel;
    private List<SkillIqLeader> mSkillIqLeaders;

    public SkillIqLeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skill_iq_leader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSkillViewModel = ViewModelProviders.of(this).get(SkillViewModel.class);

        LeadersApi leadersApi = ServiceBuilder.leaderBuildService(LeadersApi.class);
        Call<List<SkillIqLeader>> iqLeaders =  leadersApi.getSkillIqLeader();
        iqLeaders.enqueue(new Callback<List<SkillIqLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIqLeader>> call, Response<List<SkillIqLeader>> response) {
                if (!response.isSuccessful()){
                    Log.d("Not successfull", String.valueOf(response.code()));
                    return;
                }

                mSkillIqLeaders = response.body();

                mSkillViewModel.setList(mSkillIqLeaders);

                RecyclerView recyclerView = view.findViewById(R.id.recycler_skillIq);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);

                SkillLeaderAdapter skillLeaderAdapter = new SkillLeaderAdapter(getContext());

                recyclerView.setAdapter(skillLeaderAdapter);

                mSkillViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<SkillIqLeader>>() {
                    @Override
                    public void onChanged(List<SkillIqLeader> skillIqLeaders) {

                        skillLeaderAdapter.setValuesToList(mSkillIqLeaders);

                    }
                });


            }

            @Override
            public void onFailure(Call<List<SkillIqLeader>> call, Throwable t) {

                Log.d("no anything" , t.getMessage());
            }
        });

    }
}
