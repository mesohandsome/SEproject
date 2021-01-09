package com.seproject.babysitter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class search extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private searchbabysitterAdapter babysitterAdapter;
    private View view;
    private static final String dataPath = "search_use";
    private boolean time_check = false, place_check = false;

    public search() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_search_to_personalFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void allClickListener(){
        try {
            ImageButton btnsearch = view.findViewById(R.id.search_search);
            ImageButton btnpersonal = view.findViewById(R.id.search_personal);
            ImageButton btnneeds = view.findViewById(R.id.search_needs);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sw = view.findViewById(R.id.search_switch);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch time_set = view.findViewById(R.id.babysitter_time);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch place_set = view.findViewById(R.id.babysitter_place);

            time_set.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        time_check = true;
                    }
                    else{
                        time_check = false;
                    }
                }
            });

            place_set.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        place_check = true;
                    }
                    else{
                        place_check = false;
                    }
                }
            });

            sw.setChecked(false);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_search_to_searchBabyFragment);
                    }
                }
            });


            btnsearch.setEnabled(false);
            btnsearch.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray3)));

            btnpersonal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_to_personalFragment);
                }
            });

            btnneeds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_to_search_whichFragment);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), "clcik listener failed!", Toast.LENGTH_SHORT).show();
        }
    }



    private void setRecyclerView(){
        try{
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSearch);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            Query query = db.collection(dataPath);
            FirestoreRecyclerOptions<babysitter> options = new FirestoreRecyclerOptions.Builder<babysitter>()
                    .setQuery(query, babysitter.class)
                    .build();

            babysitterAdapter = new searchbabysitterAdapter(options);
            recyclerView.setAdapter(babysitterAdapter);

            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sw_time = view.findViewById(R.id.babysitter_time);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sw_place = view.findViewById(R.id.babysitter_place);
            EditText editTextSalary = view.findViewById(R.id.editTextTextSalary);
            sw_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Query query = null;
                    if(buttonView.isChecked()){
                        time_check = true;
                    }
                    else if(!buttonView.isChecked()){
                        time_check = false;
                    }

                    if(time_check && place_check){
                        Log.d("2 checked", "sw_time");
                        query = db.collection(dataPath)
                                .whereEqualTo("time", "1").whereEqualTo("place", "1");
                    }
                    else if(time_check && !place_check){
                        Log.d("time checked", "sw_time");
                        query = db.collection(dataPath)
                                .whereEqualTo("time", "1");
                    }
                    else if(place_check && !time_check){
                        Log.d("place checked", "sw_time");
                        query = db.collection(dataPath)
                                .whereEqualTo("place", "1");
                    }
                    else if(!time_check && !place_check){
                        Log.d("not checked", "sw_time");
                        query = db.collection(dataPath);
                    }
                    FirestoreRecyclerOptions<babysitter> options = new FirestoreRecyclerOptions.Builder<babysitter>()
                            .setQuery(query, babysitter.class)
                            .build();
                    Log.d("sw_time", "before updateoptions");
                    babysitterAdapter.updateOptions(options);
                }
            });

            sw_place.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Query query = null;
                    if(buttonView.isChecked()){
                        place_check = true;
                    }
                    else if(!buttonView.isChecked()){
                        place_check = false;
                    }

                    if(time_check && place_check){
                        Log.d("2 checked", "sw_place");
                        query = db.collection(dataPath)
                                .whereEqualTo("time", "1").whereEqualTo("place", "1");
                    }
                    else if(time_check && !place_check){
                        Log.d("time check", "sw_place");
                        query = db.collection(dataPath)
                                .whereEqualTo("time", "1");
                    }
                    else if(place_check && !time_check){
                        Log.d("place check", "sw_place");
                        query = db.collection(dataPath)
                                .whereEqualTo("place", "1");
                    }
                    else if(!time_check && !place_check){
                        Log.d("not check", "sw_place");
                        query = db.collection(dataPath);
                    }


                    FirestoreRecyclerOptions<babysitter> options = new FirestoreRecyclerOptions.Builder<babysitter>()
                            .setQuery(query, babysitter.class)
                            .build();
                    Log.d("sw_place", "before updateoptions");
                    babysitterAdapter.updateOptions(options);
                }
            });

            editTextSalary.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Query query;
                    if(s.toString().isEmpty()){
                        query = db.collection(dataPath);
                    }
                    else{
                        if(!time_check && !place_check) {
                            query = db.collection(dataPath)
                                    .whereLessThanOrEqualTo("money", (long) Integer.parseInt(s.toString()));
                        }
                        else if(time_check && !place_check){
                            query = db.collection(dataPath)
                                    .whereEqualTo("time", "1").whereEqualTo("place", "0")
                                    .whereLessThanOrEqualTo("money", (long) Integer.parseInt(s.toString()));
                        }
                        else if(place_check && !time_check){
                            query = db.collection(dataPath)
                                    .whereEqualTo("time","0").whereEqualTo("place", "1")
                                    .whereLessThanOrEqualTo("money", (long) Integer.parseInt(s.toString()));
                        }
                        else{
                            query = db.collection(dataPath)
                                    .whereEqualTo("time", "1").whereEqualTo("place", "1" )
                                    .whereLessThanOrEqualTo("money", (long) Integer.parseInt(s.toString()));
                        }
                    }
                    FirestoreRecyclerOptions<babysitter> options = new FirestoreRecyclerOptions.Builder<babysitter>()
                            .setQuery(query, babysitter.class)
                            .build();
                    Log.d("edittext", "before updateoptions");
                    babysitterAdapter.updateOptions(options);
                }
            });


        }
        catch (Exception e){
            Toast.makeText(getContext(), "search recycler view set failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false);
        allClickListener();
        setRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        babysitterAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(babysitterAdapter != null) {
            babysitterAdapter.stopListening();
        }
    }
}