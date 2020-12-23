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

public class BabysitterFragment extends Fragment {

    private View babysitterFragmentView;
    private Button babysitter_save_data, babysitter_cancel_data;

    public BabysitterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try {
            babysitter_save_data = babysitterFragmentView.findViewById(R.id.babysitter_save_data);
            babysitter_cancel_data = babysitterFragmentView.findViewById(R.id.babysitter_cancel_data);

            babysitter_save_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_babysitterFragment_to_personalFragment);
                }
            });

            babysitter_cancel_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_babysitterFragment_to_personalFragment);
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
        babysitterFragmentView = inflater.inflate(R.layout.fragment_babysitter, container, false);
        allClickListener();
        return babysitterFragmentView;
    }
}