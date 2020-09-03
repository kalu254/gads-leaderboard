package com.kalu.leaderboard.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kalu.leaderboard.models.HourlyLeader;

import java.util.List;

public class HourlyViewModel extends ViewModel {

    private MutableLiveData<List<HourlyLeader>> list = new MutableLiveData<>();

    private LiveData<List<HourlyLeader>> mListLiveData = Transformations.map(list, new Function<List<HourlyLeader>, List<HourlyLeader>>() {
        @Override
        public List<HourlyLeader> apply(List<HourlyLeader> input) {
            return input;
        }
    });


    public void setList(List<HourlyLeader> hourlyLeaders) {
        list.setValue(hourlyLeaders);
    }


    public LiveData<List<HourlyLeader>> getListLiveData(){
        return list;
    }
}
