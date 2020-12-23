package com.seproject.babysitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private View SettingsFragmentView;
    private Button save_passwd, cancel_passwd, save_data, cancel_data;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try {
            save_passwd = SettingsFragmentView.findViewById(R.id.btn_save_password);
            cancel_passwd = SettingsFragmentView.findViewById(R.id.btn_cancel_password);
            save_data = SettingsFragmentView.findViewById(R.id.btn_save_data);
            cancel_data = SettingsFragmentView.findViewById(R.id.btn_cancel_data);

            save_passwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });

            cancel_passwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });

            save_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });

            cancel_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
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
        SettingsFragmentView =  inflater.inflate(R.layout.fragment_settings, container, false);
        allClickListener();
        return SettingsFragmentView;
    }
}