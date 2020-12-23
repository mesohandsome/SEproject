package com.seproject.babysitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class PersonalFragment extends Fragment {

    private View personalView;
    private Button btnbaby, btnbabysitter, btnnotice, btnstore, btnsetting;

    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try{
            btnbaby = personalView.findViewById(R.id.btn_baby);
            btnbabysitter = personalView.findViewById(R.id.btn_babysitter);
            btnnotice = personalView.findViewById(R.id.btn_notice);
            btnstore = personalView.findViewById(R.id.btn_store);
            btnsetting = personalView.findViewById(R.id.btn_setting);

            btnsetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_settingsFragment);
                }
            });

            btnbaby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_mybabyFragment);
                }
            });

            btnbabysitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_babysitterFragment);
                }
            });

            btnnotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_noticeFragment);
                }
            });

            btnstore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_storeFragment);
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        personalView =  inflater.inflate(R.layout.fragment_personal, container, false);
        allClickListener();
        return personalView;
    }

}