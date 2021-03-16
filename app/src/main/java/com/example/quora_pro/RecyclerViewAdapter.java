package com.example.quora_pro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<String> question;
    ArrayList<String> answer;

    Context context;


    public RecyclerViewAdapter(Context context,ArrayList<String> question,ArrayList<String> answer) {
        this.question=question;
        this.answer=answer;
        this.context=context;

    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.questshow.setText(question.get(position));
        holder.answershow.setText(answer.get(position));
    }

    @Override
    public int getItemCount() {
        return question.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView questshow;
        public TextView answershow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questshow =itemView.findViewById(R.id.question);
            answershow=itemView.findViewById(R.id.answer);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
