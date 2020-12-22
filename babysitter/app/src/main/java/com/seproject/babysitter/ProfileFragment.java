package com.seproject.babysitter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText username = requireActivity().findViewById(R.id.editTextTextPersonName);
        EditText mail = requireActivity().findViewById(R.id.editTextTextPersonName2);
        EditText password = requireActivity().findViewById(R.id.editTextTextPassword);
        Button enter = requireActivity().findViewById(R.id.buttonBack);
        Button search = requireActivity().findViewById(R.id.button);
        EditText find = requireActivity().findViewById(R.id.editTextTextPersonName3);
        TextView textView = requireActivity().findViewById(R.id.textView);
        Button friend = requireActivity().findViewById(R.id.friend);
        Button check = requireActivity().findViewById(R.id.buttoncheck);
        EditText request = requireActivity().findViewById(R.id.editTextTextPersonName4);

        // button test on accept friend request
        check.setOnClickListener(v -> {
            String now = find.getEditableText().toString();
            String from = request.getEditableText().toString();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference().child("users").child(now);
            reference.child("friend_request").setValue("accepted");
            reference = database.getReference().child("users").child(from);
            reference.child("friend_request").setValue("accepted");
        });

        // type in user's information
        enter.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            String name = username.getEditableText().toString().trim();
            String email = mail.getEditableText().toString().trim();
            String passwd = password.getEditableText().toString().trim();
            String freind_request = "none";
            information info = new information(name, email, passwd, freind_request);
            reference.child("users").child(name).setValue(info);
        });

        // using listener to get user's information
        ValueEventListener searchinfolisten = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                information info = snapshot.getValue(information.class);
                textView.setText(info.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message
                String TAG = "error";
                Log.w(TAG, "error");
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        // button test on send friend request
        friend.setOnClickListener(v -> {
            // mAuth = firebaseAuth.getInstance();
            // senderID = mAuth.getCurrentUser().getUid();
            String send = find.getEditableText().toString();
            String get = "test";

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference().child("users").child(send);
            reference.child("friend_request").setValue("wait");

            reference = database.getReference().child("users").child(get);
            reference.child("friend_request").setValue("wait");

        });

        // search button ( find user's information )
        search.setOnClickListener(v -> {
            String inputfind = find.getEditableText().toString();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(inputfind)){
                        DatabaseReference ref = reference.child(inputfind);
                        ref.addValueEventListener(searchinfolisten);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
}