package com.example.quora_pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class allInOneActivity extends AppCompatActivity {
//    FirebaseUser user;
//    FirebaseAuth mauth;
//    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_in_one);
//        mauth =FirebaseAuth.getInstance();
//        user=mauth.getCurrentUser();
        Log.d("Ayush","Error in 1");
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("TAG");

        profile_menu profile=new profile_menu();
        FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.freameIt,profile);
        trans.commit();
    }
}