package com.example.quora_pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
  //  TextView emailView;
    FirebaseFirestore db;
    Button create;
    EditText emaidId;EditText password;
    EditText fullname;EditText lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        db=FirebaseFirestore.getInstance();

        firebaseAuth=FirebaseAuth.getInstance();
        fullname=findViewById(R.id.FirstNametag);
        lastname=findViewById(R.id.SecondNametag);
        create=findViewById(R.id.CreateTag);
        emaidId=findViewById(R.id.Emailtag);
        password=findViewById(R.id.Passwordtag);
        create.setOnClickListener(this);

    }
    public void createAccount(String Email,String Password,String firstname,String lastname)
    {

        firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(Email==null||Password.length()<6||firstname==null){
                    Toast.makeText(CreateAccount.this, "Incomplete field", Toast.LENGTH_SHORT).show();
                }
                if(task.isSuccessful()){
                    FirebaseUser user=firebaseAuth.getCurrentUser();
                    Add(Email,Password,firstname,lastname,user.getUid().toString());
                    updateUI(user);
                    Log.d("Ayush","created");
                }
                else{
                   // updateUI(null);
                    Toast.makeText(CreateAccount.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    Log.d("Ayush","created");
                }

            }
        });

    }
    void updateUI(FirebaseUser user){
        String UId=user.getUid();
        Intent i=new Intent(this,mainLayout.class);
        i.putExtra("TAG",UId);
        startActivity(i);



    }
    public void Add(String email,String password,String firstname,String lastname,String id){
        Map<String ,Object> user=new HashMap<>();
        user.put("email",email);user.put("password",password);user.put("firstname",firstname);user.put("lastname",lastname);
        db.collection("users").document(id).set(user).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("Ayush","added successfully");
                }
                else{
                    Log.d("Ayush","some Error occured");
                }
            }
        });

    }
    @Override
    public void onClick(View v) {
        int which=v.getId();
        switch (which){
            case R.id.CreateTag:
                String tex=emaidId.getText().toString();
                String passwrd=password.getText().toString();
                String fullnames=fullname.getText().toString();
                String lastnames=lastname.getText().toString();

                createAccount(tex,passwrd,fullnames,lastnames);


        }
    }
}