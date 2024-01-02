package com.example.networkmeup.view.HomeEmployer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.networkmeup.R;
import com.example.networkmeup.view.EditAccountEmployer.EditAccountEmployerActivity;
import com.example.networkmeup.view.HomeEmployee.HomeEmployeeActivity;
import com.example.networkmeup.view.Login.LoginEmployee.LoginEmployeeActivity;
import com.example.networkmeup.view.Login.LoginEmployer.LoginEmployerActivity;
import com.example.networkmeup.view.ManageJobPositions.ManageJobPositionsActivity;
import com.example.networkmeup.view.UpdateJobApplications.UpdateJobApplicationsActivity;
import com.example.networkmeup.view.EditAccountEmployer.EditAccountEmployerActivity;

public class HomeEmployerActivity extends AppCompatActivity implements HomeEmployerView {
    private String userToken; // Token (email) of the employer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employer);

        //homepage employer activity requires a login token from the user that logged in (token = email)
        Bundle extras = getIntent().getExtras();

        String userEmail = null;
        if(extras!=null){
            //obtain user token
            userEmail = extras.getString("token");
        }

        HomeEmployerPresenter presenter = new HomeEmployerPresenter(this, userEmail);

        findViewById(R.id.btnHomeEmployerManageJobPositions).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.onManageJobPositions();
                    }
                }
        );

        findViewById(R.id.btnHomeEmployerUpdateJobApplications).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.onUpdateJobApplications();
                    }
                }
        );

        findViewById(R.id.btnHomeEmployerEditAccount).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.onEditAccount();
                    }
                }
        );
    }

    @Override
    public void manageJobPositions(String userToken) {
        Intent intent = new Intent(this, ManageJobPositionsActivity.class);
        intent.putExtra("token", userToken);
        startActivity(intent);
    }

    @Override
    public void updateJobApplications(String userToken) {
        Intent intent = new Intent(this, UpdateJobApplicationsActivity.class);
        intent.putExtra("token", userToken);
        startActivity(intent);
    }

    @Override
    public void editAccount(String userToken) {
        Intent intent = new Intent(this, EditAccountEmployerActivity.class);
        intent.putExtra("token", userToken);
        startActivity(intent);
    }

    @Override
    public void showTokenErrorMessage(String errorMessage) {
        new AlertDialog.Builder(HomeEmployerActivity.this)
                .setCancelable(false)
                .setTitle("Login Token Error")
                .setMessage(errorMessage)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener(){
                            public void onClick (DialogInterface dialog,int id) {

                                Intent intent = new Intent(HomeEmployerActivity.this, LoginEmployerActivity.class);
                                startActivity(intent);
                            }}).create().show();
    }
}

