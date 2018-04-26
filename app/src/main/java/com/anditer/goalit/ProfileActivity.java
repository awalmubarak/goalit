package com.anditer.goalit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anditer.goalit.utils.PrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        setProfileNameAndEmail(account);
        setUpToolBar();

        findViewById(R.id.logout).setOnClickListener(this);
    }



    private void setUpToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this,R.style.TolBarStyle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("My Account");

    }



    public void setProfileNameAndEmail(GoogleSignInAccount account){

        TextView profileName = findViewById(R.id.profileName);
        TextView profileEmail = findViewById(R.id.profileEmail);
        if (account!=null){
            profileName.setText(account.getDisplayName());
            profileEmail.setText(account.getEmail());
        }

    }


    @Override
    public void onClick(View view) {

        findViewById(R.id.loggingOutLayer).setVisibility(View.VISIBLE);

        buildSignInClient().signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                logoutUser();
            }
        });
    }


    public GoogleSignInClient buildSignInClient(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        return GoogleSignIn.getClient(getApplicationContext(),gso);
    }



    public void logoutUser(){
        //clear login preferences and cleanup the database
        PrefManager.updateSignInPref(this,false);
        startActivity(new Intent(this, LoginActivity.class));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
