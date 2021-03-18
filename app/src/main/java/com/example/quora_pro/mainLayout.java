package com.example.quora_pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class mainLayout extends AppCompatActivity implements View.OnClickListener{
    //TextView textView;
   // FrameLayout frameLayout;

    ArrayList<String> question;
    ArrayList<String> answer;
    ImageView homeImage,askImage,searchImage,notifyImage,profileImage;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        db=FirebaseFirestore.getInstance();

        homeImage=findViewById(R.id.homeIt);
        homeImage.setOnClickListener(this);
        searchImage=findViewById(R.id.searchIt);
        searchImage.setOnClickListener(this);
        askImage=findViewById(R.id.askIt);
        askImage.setOnClickListener(this);
        notifyImage=findViewById(R.id.notifyIt);
        notifyImage.setOnClickListener(this);
        profileImage=findViewById(R.id.profileIt);
        profileImage.setOnClickListener(this);


        Log.d("Ayush","number 6");
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("TAG");
        Log.d("Ayush","number 7");


        question =new ArrayList<>();
        answer=new ArrayList<>();

       // frameLayout=findViewById(R.id.frameLayout);
        questionlist list=new questionlist(question,answer);

        //FragmentManager fragmentManager=getSupportFragmentManager().beginTransaction();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,list);
        transaction.commit();
        Log.d("Ayush","number 5");
    }

    @Override
    public void onClick(View v) {
        int which=v.getId();
        Log.d("Ayush","number 10");
        switch (which){
            case R.id.homeIt:
                homeImage.setBackgroundResource(R.color.app_name);
                questionlist list=new questionlist(question,answer);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout,list);
                transaction.commit();
                break;
            case R.id.searchIt:
                searchImage.setBackgroundResource(R.color.app_name);
                break;


            case R.id.askIt :
//                Intent i=new Intent(mainLayout.this,CreateAccount.class);
//                startActivity(i);
                askImage.setBackgroundResource(R.color.app_name);
                AskQuestionFragment ask=new AskQuestionFragment();
                FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.frameLayout,ask);
                transaction1.commit();
                    break;

            case R.id.notifyIt:
                notifyImage.setBackgroundResource(R.color.app_name);
                break;

            case R.id.profileIt:
                profileImage.setBackgroundResource(R.color.app_name);
                Intent iprofile=new Intent(this,allInOneActivity.class);
                iprofile.putExtra("TAG","Ayush");

                startActivity(iprofile);

                break;


        }
    }
}