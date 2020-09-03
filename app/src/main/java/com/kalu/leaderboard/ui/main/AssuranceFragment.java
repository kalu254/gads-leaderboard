package com.kalu.leaderboard.ui.main;

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
import com.kalu.leaderboard.models.Submit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssuranceFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.assurance_fragment,container,false);

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
                Call<Submit> submitCall = submitApi.submitProject(fName,lName,eAddress,pLink);
                submitCall.enqueue(new Callback<Submit>() {
                    @Override
                    public void onResponse(Call<Submit> call, Response<Submit> response) {
                        if (!response.isSuccessful()){

                            FailedDialog failedDialog = new FailedDialog();
                            failedDialog.show(getParentFragmentManager(),"AssuranceFragment");
                            Log.d("never", String.valueOf(response.code()));
                            return;
                        }

                        Log.d("status okay", String.valueOf(response.code()));
                        SuccessfulDialog successfulDialog = new SuccessfulDialog();
                        successfulDialog.show(getParentFragmentManager(),"SuccessfulDialog");

                    }

                    @Override
                    public void onFailure(Call<Submit> call, Throwable t) {
                        Log.d("nn",t.getMessage());

                        FailedDialog failedDialog = new FailedDialog();
                        failedDialog.show(getParentFragmentManager(),"AssuranceFragment");

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
