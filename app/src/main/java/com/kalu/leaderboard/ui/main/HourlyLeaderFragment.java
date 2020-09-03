package com.kalu.leaderboard.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalu.leaderboard.FetchData.LeadersApi;
import com.kalu.leaderboard.FetchData.ServiceBuilder;
import com.kalu.leaderboard.R;
import com.kalu.leaderboard.models.HourlyLeader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class HourlyLeaderFragment extends Fragment {

    private List<HourlyLeader> mHourlyLeaders = new ArrayList<>();
    private HourlyViewModel mHourlyViewModel;




    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hourly_leader, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHourlyViewModel = ViewModelProviders.of(this).get(HourlyViewModel.class);

        LeadersApi leadersApi = ServiceBuilder.leaderBuildService(LeadersApi.class);

        Call<List<HourlyLeader>> hourlyLeaders = leadersApi.getHourlyLeaders();

        hourlyLeaders.enqueue(new Callback<List<HourlyLeader>>() {
            @Override
            public void onResponse(Call<List<HourlyLeader>> call, Response<List<HourlyLeader>> response) {

                if (!response.isSuccessful()){
                    Log.d("not successful", String.valueOf(response.code()));
                    return;
                }

                mHourlyLeaders = response.body();

                mHourlyViewModel.setList(mHourlyLeaders);


                RecyclerView recyclerView = view.findViewById(R.id.recycler_hours);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                HourlyLeaderAdapter mHourlyLeaderAdapter = new HourlyLeaderAdapter(getContext());
                recyclerView.setAdapter(mHourlyLeaderAdapter);
                mHourlyViewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<List<HourlyLeader>>() {
                    @Override
                    public void onChanged(List<HourlyLeader> hourlyLeaders) {

                        mHourlyLeaderAdapter.setList(hourlyLeaders);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<HourlyLeader>> call, Throwable t) {

                Log.d("nothing", t.getMessage());
            }
        });


    }
}
