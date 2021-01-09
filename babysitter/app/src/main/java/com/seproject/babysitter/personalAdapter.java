package com.seproject.babysitter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class personalAdapter extends FirestoreRecyclerAdapter<personal, personalAdapter.personalViewHolder> {

    public personalAdapter(@NonNull FirestoreRecyclerOptions<personal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull personalViewHolder personalViewHolder, int i, @NonNull personal personal) {
        personalViewHolder.username.setText(personal.getName());
        personalViewHolder.comment.setText(personal.getComment());

        // FirebaseAuth.getInstance().getCurrentUser().getUid();
        // use uid to find name

    }

    @NonNull
    @Override
    public personalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_comment, parent, false);
        return new personalViewHolder(view);
    }

    class personalViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView username, date, comment;

        public personalViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_user_name);
            date = itemView.findViewById(R.id.tv_date);
            comment = itemView.findViewById(R.id.tv_else);
            photo = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
