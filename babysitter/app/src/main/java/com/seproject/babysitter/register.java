package com.seproject.babysitter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class register extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View objectRegisterFragment;
    private EditText userEmailET, userPwdET;
    private FirebaseAuth objectFirebaseAuth;
    private Button objectButton;
    private ProgressBar objectProgressBar;

    public register() {
        // Required empty public constructor
    }

    public void createUser()
    {
        try
        {
            if(!userEmailET.getText().toString().isEmpty() && !userPwdET.getText().toString().isEmpty())
            {
                objectProgressBar.setVisibility(View.VISIBLE);
                objectButton.setEnabled(false);
                objectFirebaseAuth.createUserWithEmailAndPassword(userEmailET.getText().toString(), userPwdET.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "帳戶建立成功!", Toast.LENGTH_SHORT).show();
                                objectProgressBar.setVisibility(View.INVISIBLE);
                                objectButton.setEnabled(true);
                                userEmailET.setText("");
                                userPwdET.setText("");
                                if(objectFirebaseAuth.getCurrentUser() != null)
                                {
                                    objectFirebaseAuth.signOut();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                objectProgressBar.setVisibility(View.INVISIBLE);
                                objectButton.setEnabled(true);
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
            else
            {
                Toast.makeText(getContext(), "請先填入此欄位!", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void checkUserExists()
    {
        try
        {
            if(objectFirebaseAuth != null && !userEmailET.getText().toString().isEmpty())
            {
                objectProgressBar.setVisibility(View.VISIBLE);
                objectButton.setEnabled(false);
                objectFirebaseAuth.fetchSignInMethodsForEmail(userEmailET.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean checkResult = !task.getResult().getSignInMethods().isEmpty();
                                if(!checkResult)
                                {
                                    createUser();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), "此信箱已被註冊過!", Toast.LENGTH_SHORT).show();
                                    objectProgressBar.setVisibility(View.INVISIBLE);
                                    objectButton.setEnabled(true);
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                objectProgressBar.setVisibility(View.INVISIBLE);
                                objectButton.setEnabled(true);
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void attachToXML()
    {
        try
        {
            userEmailET = objectRegisterFragment.findViewById(R.id.et_email);
            userPwdET = objectRegisterFragment.findViewById(R.id.et_password);

            objectFirebaseAuth = FirebaseAuth.getInstance();
            objectButton = objectRegisterFragment.findViewById(R.id.btn_register);

            objectProgressBar = objectRegisterFragment.findViewById(R.id.progressbar_register);
            objectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkUserExists();
                }
            });



        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public static register newInstance(String param1, String param2) {
        register fragment = new register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectRegisterFragment = inflater.inflate(R.layout.fragment_register, container, false);
        attachToXML();
        return objectRegisterFragment;
    }
}