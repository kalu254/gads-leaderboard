package com.kalu.leaderboard.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kalu.leaderboard.models.SkillIqLeader;

import java.util.List;

public class SkillViewModel extends ViewModel {

    private MutableLiveData<List<SkillIqLeader>> list = new MutableLiveData<>();

    private LiveData<List<SkillIqLeader>> mSkillIqLeaderLiveData = Transformations.map(list, new Function<List<SkillIqLeader>, List<SkillIqLeader>>() {
        @Override
        public List<SkillIqLeader> apply(List<SkillIqLeader> input) {
            return input;
        }
    });

    public MutableLiveData<List<SkillIqLeader>> getList() {
        return list;
    }

    public void setList(List<SkillIqLeader> list) {
        this.list.setValue(list);
    }
}
