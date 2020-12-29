package com.seproject.babysitter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class needs extends Fragment {

    private View needsView;
    private Button save_need, upload, cancel;

    public needs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try {
            save_need = needsView.findViewById(R.id.btn_save_need);
            upload = needsView.findViewById(R.id.btn_upload);
            cancel = needsView.findViewById(R.id.btn_cancel);
            ImageButton btnsearch = needsView.findViewById(R.id.needs_search);
            ImageButton btnpersonal = needsView.findViewById(R.id.needs_personal);
            ImageButton btnneeds = needsView.findViewById(R.id.needs_needs);

            btnneeds.setEnabled(false);
            btnneeds.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray3)));

            btnsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_search);
                }
            });

            btnpersonal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_personalFragment);
                }
            });

            // TODO spinnners

            save_need.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_personalFragment);
                }
            });

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO upload
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_personalFragment);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        needsView = inflater.inflate(R.layout.fragment_needs, container, false);
        allClickListener();
        return needsView;
    }
}