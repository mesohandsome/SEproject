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

public class favoriteAdapter extends FirestoreRecyclerAdapter<favorite, favoriteAdapter.favoriteViewHolder> {

    public favoriteAdapter(@NonNull FirestoreRecyclerOptions<favorite> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull favoriteViewHolder favoriteViewHolder, int i, @NonNull favorite favorite) {
        favoriteViewHolder.title_salary.setText("薪資");
        favoriteViewHolder.title_place.setText("地點");
        favoriteViewHolder.title_time.setText("時間");
        favoriteViewHolder.title_else.setText("其他");
        //favoriteViewHolder.address.setText(favorite.getAddress());
        favoriteViewHolder.character.setText(favorite.getName());
        //favoriteViewHolder.time.setText(favorite.getTime());
        //favoriteViewHolder.salary.setText(favorite.getSalary());
    }

    @NonNull
    @Override
    public favoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_favorite, parent, false);
        return new favoriteViewHolder(view);
    }

    class favoriteViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView address, character, diet, gender, loction, old, phone, pickup, ps, salary, time;
        TextView title_time, title_place, title_salary, title_else;

        public favoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            title_time = itemView.findViewById(R.id.favor_tv_title_time);
            title_place = itemView.findViewById(R.id.favor_tv_title_place);
            title_salary = itemView.findViewById(R.id.favor_tv_title_money);
            title_else = itemView.findViewById(R.id.favor_tv_title_else);
            character = itemView.findViewById(R.id.favor_tv_user_name);
            address = itemView.findViewById(R.id.favor_tv_place);
            time = itemView.findViewById(R.id.favor_tv_time);
            salary = itemView.findViewById(R.id.favor_tv_money);
            photo = itemView.findViewById(R.id.favor_iv_image);
        }
    }
}
