package com.seproject.babysitter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends Fragment {

    View objectRegisterFragment;
    private EditText userEmailET, userPwdET;
    private FirebaseAuth objectFirebaseAuth;
    private Button objectButton;
    private ProgressBar objectProgressBar;
    private EditText realName, connect, place, usrID;
    private DocumentReference myDocRef;

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
                String ID = usrID.getText().toString().trim();
                String passwd = userPwdET.getText().toString().trim();
                String email = userEmailET.getText().toString().trim();
                String name = realName.getText().toString().trim();
                String phone = connect.getText().toString().trim();
                String address = place.getText().toString().trim();
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("ID", ID);
                dataToSave.put("passwd", passwd);
                dataToSave.put("email", email);
                dataToSave.put("name", name);
                dataToSave.put("phone", phone);
                dataToSave.put("address", address);
                String path = "users/" + name;
                String TAG = "Saving Data";
                myDocRef = FirebaseFirestore.getInstance().document(path);
                myDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Saved!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Failed!");
                    }
                });
                objectFirebaseAuth.createUserWithEmailAndPassword(email, passwd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "帳戶建立成功!", Toast.LENGTH_SHORT).show();
                                objectProgressBar.setVisibility(View.INVISIBLE);
                                objectButton.setEnabled(true);
                                userEmailET.setText("");
                                userPwdET.setText("");
                                realName.setText("");
                                connect.setText("");
                                place.setText("");
                                usrID.setText("");
                                String uid = objectFirebaseAuth.getCurrentUser().getUid().toString();
                                String text = "this " + uid;
                                String TAG = "register";
                                Log.d(TAG, text);
                                if(objectFirebaseAuth.getCurrentUser() != null)
                                {
                                    objectFirebaseAuth.signOut();
                                }
                                NavController navController = Navigation.findNavController(requireView());
                                navController.navigate(R.id.action_register_to_loginFragment);
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
            realName = objectRegisterFragment.findViewById(R.id.et_real_name);
            connect = objectRegisterFragment.findViewById(R.id.et_connect);
            place = objectRegisterFragment.findViewById(R.id.et_place);
            usrID = objectRegisterFragment.findViewById(R.id.et_name);

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}