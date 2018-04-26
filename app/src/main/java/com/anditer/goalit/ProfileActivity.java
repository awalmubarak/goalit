package com.anditer.goalit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setToolbarTitleAndBackButton();

        findViewById(R.id.logout).setOnClickListener(this);
    }



    private void setToolbarTitleAndBackButton(){
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
    }

}
