package com.kalu.leaderboard.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kalu.leaderboard.R;

public class SubmitActivity extends AppCompatActivity {

    private TextView firstName;
    private TextView lastName;
    private TextView emailAddress;
    private TextView projectLink;

    private Button mButton;
    private String mFName;
    private String mLName;
    private String mEAddress;
    private String mPLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        firstName = findViewById(R.id.edt_txt_first_name);
        lastName = findViewById(R.id.edt_txt_last_name);
        emailAddress = findViewById(R.id.edt_txt_email_address);
        projectLink = findViewById(R.id.edt_txt_project_url);



        mButton = findViewById(R.id.btn_submit_proj);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFName = firstName.getText().toString();
                mLName = lastName.getText().toString();
                mEAddress = emailAddress.getText().toString();
                mPLink = projectLink.getText().toString();


                if (!isEmpty(mFName) && !isEmpty(mLName) && !isEmpty(mEAddress) && !isEmpty(mPLink)){

                    Bundle bundle = new Bundle();
                    bundle.putString("fname",mFName);
                    bundle.putString("lname",mLName);
                    bundle.putString("eaddress",mEAddress);
                    bundle.putString("plink",mPLink);

                    AssuranceFragment assuranceFragment = new AssuranceFragment();
                    assuranceFragment.setArguments(bundle);

                    assuranceFragment.show(getSupportFragmentManager(),"AssuranceFragment");
                }
                else {
                    Toast.makeText(SubmitActivity.this,"All fields required",Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    private boolean isEmpty(String string){
        return string.equals("");
    }

}
