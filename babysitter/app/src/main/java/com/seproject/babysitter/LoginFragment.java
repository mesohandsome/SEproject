package com.seproject.babysitter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private View objectLoginFragment;
    private FirebaseAuth objectFirebaseAuth;

    private EditText userEmailET, userPwdEt;
    private Button logInBtn;
    private ProgressBar objectProgressBar;


    public LoginFragment() {
        // Required empty public constructor
    }

    private void initializeVariables()
    {
        try
        {
            objectFirebaseAuth = FirebaseAuth.getInstance();
            userEmailET = objectLoginFragment.findViewById(R.id.et_email);
            userPwdEt = objectLoginFragment.findViewById(R.id.et_password);

            logInBtn = objectLoginFragment.findViewById(R.id.btn_login);
            objectProgressBar = objectLoginFragment.findViewById(R.id.progressbar_login);

            logInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogInUser();
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void LogInUser()
    {
        try
        {
            if(!userEmailET.getText().toString().isEmpty() && !userPwdEt.getText().toString().isEmpty())
            {
                if(objectFirebaseAuth != null)
                {
                    objectProgressBar.setVisibility(View.VISIBLE);
                    logInBtn.setEnabled(false);

                    objectFirebaseAuth.signInWithEmailAndPassword(userEmailET.getText().toString(), userPwdEt.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Intent intent = new Intent(requireActivity().getApplicationContext(), testActivity.class);
                                    startActivity(intent);
                                    //startActivity(new Intent(getActivity().getApplicationContext(), PersonalFragment.class));
                                    objectProgressBar.setVisibility(View.INVISIBLE);
                                    logInBtn.setEnabled(true);

                                    getActivity().finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    objectProgressBar.setVisibility(View.INVISIBLE);
                                    logInBtn.setEnabled(true);
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
            else
            {
                Toast.makeText(getContext(), "請填寫欄位!", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectLoginFragment = inflater.inflate(R.layout.fragment_login, container, false);
        initializeVariables();
        return objectLoginFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Button buttonlogin = requireActivity().findViewById(R.id.btn_login);
        Button buttonreg = requireActivity().findViewById(R.id.btn_register);
/*
        buttonlogin.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_loginFragment_to_profileFragment);
        });
*/
        buttonreg.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_loginFragment_to_termsFragment);
        });
    }
}