package com.kalu.leaderboard.FetchData;

import com.kalu.leaderboard.models.HourlyLeader;
import com.kalu.leaderboard.models.SkillIqLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadersApi {

    @GET("hours")
    Call<List<HourlyLeader>> getHourlyLeaders();

    @GET("skilliq")
    Call<List<SkillIqLeader>> getSkillIqLeader();
}
