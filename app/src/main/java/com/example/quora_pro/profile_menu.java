package com.example.quora_pro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class profile_menu extends Fragment {


    public profile_menu() {


    }
    FirebaseUser user;
    FirebaseAuth mauth;
    FirebaseFirestore db;
    ListView listView;
    ArrayList<String> menu;
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile_menu, container, false);
        mauth=FirebaseAuth.getInstance();
        user=mauth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        name=v.findViewById(R.id.profilename);

        DocumentReference doc=db.collection("users").document(user.getUid().toString());
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot result=task.getResult();
                    name.setText(result.get("firstname")+" "+result.get("lastname"));

                }
                else{
                    Log.d("Ayush","not fetched");
                }
            }
        });


        menu=new ArrayList<>();
       // View v= inflater.inflate(R.layout.fragment_profile_menu, container, false);
            menu.add("Questions");menu.add("Answers");menu.add("favourites");menu.add("freinds");
            menu.add("views");menu.add("Top questions");menu.add("Drafts");menu.add("QLiked");menu.add("Log Out");

            listView=v.findViewById(R.id.profile_list);
        ArrayAdapter<String> arr=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,menu);
        listView.setAdapter(arr);

        return  v;
    }
}