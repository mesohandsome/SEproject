package com.seproject.babysitter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class Search_whichFragment extends Fragment {

    private View view;

    public Search_whichFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try{
            ImageButton btnbaby = view.findViewById(R.id.search_baby);
            ImageButton btnbabysitter = view.findViewById(R.id.search_babysitter);
            ImageButton btnsearch = view.findViewById(R.id.searchwhich_search);
            ImageButton btnpersonal = view.findViewById(R.id.searchwhich_personal);
            ImageButton btnneeds = view.findViewById(R.id.searchwhich_needs);

            btnneeds.setEnabled(false);
            btnneeds.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray3)));

            btnsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_whichFragment_to_search);
                }
            });

            btnpersonal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_whichFragment_to_personalFragment);
                }
            });

            btnbaby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_whichFragment_to_needs);
                }
            });

            btnbabysitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_search_whichFragment_to_needs);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), "clcik listener failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_which, container, false);
        allClickListener();
        return view;
    }
}