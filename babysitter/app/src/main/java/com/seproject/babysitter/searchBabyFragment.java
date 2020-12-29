package com.seproject.babysitter;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class searchBabyFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private searchbabyAdapter babyAdapter;
    private View view;

    public searchBabyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_searchBabyFragment_to_personalFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void allClickListener(){
        try {
            ImageButton btnsearch = view.findViewById(R.id.searchbaby_search);
            ImageButton btnpersonal = view.findViewById(R.id.searchbaby_personal);
            ImageButton btnneeds = view.findViewById(R.id.searchbaby_needs);
            Switch sw = view.findViewById(R.id.search_baby_switch);

            sw.setChecked(true);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!buttonView.isChecked()){
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_searchBabyFragment_to_search);
                    }
                }
            });


            btnsearch.setEnabled(false);
            btnsearch.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray3)));

            btnpersonal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_searchBabyFragment_to_personalFragment);
                }
            });

            btnneeds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_searchBabyFragment_to_search_whichFragment);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), "clcik listener failed!", Toast.LENGTH_SHORT).show();
        }
    }


    private  void setRecyclerView(){
        try{
            Query query = db.collection("baby");

            FirestoreRecyclerOptions<baby> options = new FirestoreRecyclerOptions.Builder<baby>()
                    .setQuery(query, baby.class)
                    .build();

            babyAdapter = new searchbabyAdapter(options);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSearchbaby);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(babyAdapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "search recycler view set failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search_baby, container, false);
        allClickListener();
        setRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        babyAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        babyAdapter.stopListening();
    }
}