package com.seproject.babysitter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class otheroneAdapter extends FirestoreRecyclerAdapter<otherone, otheroneAdapter.otheroneViewHolder> {

    public otheroneAdapter(@NonNull FirestoreRecyclerOptions<otherone> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull otheroneViewHolder otheroneViewHolder, int i, @NonNull otherone otherone) {
        otheroneViewHolder.username.setText(otherone.getName());
        otheroneViewHolder.comment.setText(otherone.getComment());
    }

    @NonNull
    @Override
    public otheroneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_comment, parent, false);
        return new otheroneViewHolder(view);
    }

    class otheroneViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView username, date, comment;

        public otheroneViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_user_name);
            date = itemView.findViewById(R.id.tv_date);
            comment = itemView.findViewById(R.id.tv_else);
            photo = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
