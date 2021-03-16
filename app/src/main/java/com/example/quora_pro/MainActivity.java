package com.example.quora_pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private Button SignIn;
    private Button CreateAcc;
    private CheckBox checkBox;

    private EditText email;
    private EditText password;

    boolean flag=false;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            Log.d("Ayush","number 4");
           updateUI(user);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Ayush","number 1");
        firebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.EmailEdit);
        password=findViewById(R.id.PasswordEdit);
        SignIn=findViewById(R.id.SignInBox);
        CreateAcc=findViewById(R.id.CreateAccount);
        checkBox=findViewById(R.id.CheckBox);
        SignIn.setOnClickListener(this);
        CreateAcc.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        Log.d("Ayush","number 2");

    }


    public boolean valideForm(){
        return true;
    }
    public void updateUI(FirebaseUser user){
                Intent i=new Intent(MainActivity.this,mainLayout.class);
                String id=user.getUid();

                i.putExtra("TAG",id);

                startActivity(i);
        Log.d("Ayush","number 5");
    }

    @Override
    public void onClick(View v) {
        int which=v.getId();
        switch (which){
            case R.id.SignInBox:
                if(flag==true) {
                    String emailshow=email.getText().toString();
                    String passwordshow=password.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(emailshow,passwordshow).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Ayush","number 3");
                            if(emailshow==null||passwordshow==null){
                                Toast.makeText(MainActivity.this, "email or password is tpp short", Toast.LENGTH_SHORT).show();

                            }

                            else if(task.isSuccessful()){
                                FirebaseUser user=firebaseAuth.getCurrentUser();
                                updateUI(user);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(this, "Incomplete signIn", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ResetPassword:
                Intent i1=new Intent(this,CreateAccount.class);
                startActivity(i1);
                break;
            case R.id.ForgotPassword:
                Intent i2=new Intent(this,CreateAccount.class);
                startActivity(i2);
                break;
            case R.id.CreateAccount:
                Intent i3=new Intent(this,CreateAccount.class);
                startActivity(i3);
                break;
            case  R.id.CheckBox:
                if(((CheckBox)v).isChecked()){
                    flag=true;
                }
                break;
        }
    }
}