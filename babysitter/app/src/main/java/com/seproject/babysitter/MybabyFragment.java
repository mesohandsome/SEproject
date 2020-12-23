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


public class MybabyFragment extends Fragment {

    private View mybabyFragmentView;
    private Button save_data, cancel_data;

    public MybabyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClicklistener(){
        try {
            save_data = mybabyFragmentView.findViewById(R.id.btn_save_data);
            cancel_data = mybabyFragmentView.findViewById(R.id.btn_cancel_data);

            save_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_mybabyFragment_to_personalFragment);
                }
            });

            cancel_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_mybabyFragment_to_personalFragment);
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
        mybabyFragmentView = inflater.inflate(R.layout.fragment_mybaby, container, false);
        allClicklistener();
        return mybabyFragmentView;
    }
}