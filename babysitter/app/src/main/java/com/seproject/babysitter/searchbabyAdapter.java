package com.seproject.babysitter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class searchbabyAdapter extends FirestoreRecyclerAdapter<baby, searchbabyAdapter.searchbabyViewHolder> {

    public searchbabyAdapter(@NonNull FirestoreRecyclerOptions<baby> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull searchbabyViewHolder searchbabyViewHolder, int i, @NonNull baby baby) {
        searchbabyViewHolder.title_salary.setText("薪資");
        searchbabyViewHolder.title_place.setText("地點");
        searchbabyViewHolder.title_time.setText("時間");
        searchbabyViewHolder.title_else.setText("其他");
        searchbabyViewHolder.address.setText(baby.getAddress());
        searchbabyViewHolder.character.setText(baby.getCharacter());
        searchbabyViewHolder.time.setText(baby.getTime());
        searchbabyViewHolder.salary.setText(baby.getSalary());
    }

    @NonNull
    @Override
    public searchbabyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_result, parent, false);
        return new searchbabyViewHolder(view);
    }

    class searchbabyViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView address, character, diet, gender, loction, old, phone, pickup, ps, salary, time;
        TextView title_time, title_place, title_salary, title_else;
        Button info;

        public searchbabyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_time = itemView.findViewById(R.id.search_tv_title_time);
            title_place = itemView.findViewById(R.id.search_tv_title_place);
            title_salary = itemView.findViewById(R.id.search_tv_title_money);
            title_else = itemView.findViewById(R.id.search_tv_title_else);
            character = itemView.findViewById(R.id.search_tv_user_name);
            address = itemView.findViewById(R.id.search_tv_place);
            time = itemView.findViewById(R.id.search_tv_time);
            salary = itemView.findViewById(R.id.search_tv_money);
            photo = itemView.findViewById(R.id.search_iv_image);
            info = itemView.findViewById(R.id.searchbabysitter_btn_connect);

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_searchBabyFragment_to_other_OneFragment);
                }
            });

        }
    }
}
