package com.seproject.babysitter;

import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class searchbabysitterAdapter extends FirestoreRecyclerAdapter<babysitter, searchbabysitterAdapter.searchViewHolder> {

    public searchbabysitterAdapter(@NonNull FirestoreRecyclerOptions<babysitter> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull searchViewHolder searchViewHolder, int i, @NonNull babysitter babysitter) {
        searchViewHolder.username.setText(babysitter.getName().toString());
        searchViewHolder.place.setText(babysitter.getEnvironment().toString());
        searchViewHolder.time.setText(babysitter.getTime().toString());
        searchViewHolder.salary.setText(babysitter.getSalary().toString());
        searchViewHolder.elseneed.setText(babysitter.getExperience().toString());
        searchViewHolder.title_salary.setText("薪資");
        searchViewHolder.title_place.setText("地點");
        searchViewHolder.title_time.setText("時間");
        searchViewHolder.title_else.setText("其他");

        searchViewHolder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foundname = babysitter.getName(); // found name
                String path = "recycler_testing/123";
                DocumentReference myRef = FirebaseFirestore.getInstance().document(path);
                Map<String, Object> map = new HashMap<>();
                map.put("other",foundname);
                myRef.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("update other direction", "success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("update other direction", "failed");
                    }
                });

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_search_to_other_OneFragment);
            }
        });

    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_result, parent, false);
        return new searchViewHolder(view);
    }

    class searchViewHolder extends RecyclerView.ViewHolder{
        TextView username, date, time, place, salary, elseneed;
        ImageView photo;
        TextView title_time, title_place, title_salary, title_else;
        Button info;

        public searchViewHolder(@NonNull View itemView){
            super(itemView);
            title_time = itemView.findViewById(R.id.search_tv_title_time);
            title_place = itemView.findViewById(R.id.search_tv_title_place);
            title_salary = itemView.findViewById(R.id.search_tv_title_money);
            title_else = itemView.findViewById(R.id.search_tv_title_else);
            username = itemView.findViewById(R.id.search_tv_user_name);
            date = itemView.findViewById(R.id.search_tv_date);
            time = itemView.findViewById(R.id.search_tv_time);
            place = itemView.findViewById(R.id.search_tv_place);
            salary = itemView.findViewById(R.id.search_tv_money);
            elseneed = itemView.findViewById(R.id.search_tv_else);
            photo = itemView.findViewById(R.id.search_iv_image);
            info = itemView.findViewById(R.id.searchbabysitter_btn_connect);
/*
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Need to change navigation destination

                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_to_other_OneFragment);
                }
            });

 */
        }
    }
}
