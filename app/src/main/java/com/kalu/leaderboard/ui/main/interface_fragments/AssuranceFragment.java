package com.kalu.leaderboard.ui.main.interface_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kalu.leaderboard.FetchData.ServiceBuilder;
import com.kalu.leaderboard.FetchData.SubmitApi;
import com.kalu.leaderboard.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssuranceFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.assurance_fragment, container, false);

        Bundle bundle = getArguments();

        String fName = bundle.getString("fname");
        String lName = bundle.getString("lname");
        String eAddress = bundle.getString("eaddress");
        String pLink = bundle.getString("plink");

        Button button = view.findViewById(R.id.btn_confirm_submission);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitApi submitApi = ServiceBuilder.submitBuildService(SubmitApi.class);
                Call<Void> submitCall = submitApi.submitProject(fName, lName, eAddress, pLink);
                submitCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()){
                            Log.d("bad response", response.message());
                            FailedDialog failedDialog = new FailedDialog();
                            failedDialog.show(getParentFragmentManager(),"FailedDialog");
                        }

                        Log.d("workrd","yessss");
                        SuccessfulDialog successfulDialog = new SuccessfulDialog();
                        successfulDialog.show(getParentFragmentManager(),"SuccessfulDialog");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("failed", t.getMessage());
                        FailedDialog failedDialog = new FailedDialog();
                        failedDialog.show(getParentFragmentManager(),"FailedDialog");
                    }
                });
            }
        });
        ImageView cancel = view.findViewById(R.id.btn_cancel_assurance);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDialog().dismiss();
                    }
                });
        return view;

    }
}
