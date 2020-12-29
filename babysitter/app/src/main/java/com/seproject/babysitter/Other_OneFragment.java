package com.seproject.babysitter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

public class Other_OneFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private otheroneAdapter adapter;
    private View view;
    private otheronename ooo = new otheronename();

    public Other_OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setRecyclerView(){
        try{
            TextView name = view.findViewById(R.id.oth_tv_name);
            // getuid.getname
            String user = "123";
            Log.d("before", "otherone");
            String p = "recycler_testing/" + user;
            db.collection("recycler_testing").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists()){
                        name.set
                        name.setText(documentSnapshot.getString("other"));
                        ooo.setName(documentSnapshot.getString("other"));
                    }
                }
            });
            String aaaa = name.getText().toString();
            String path = "users/" + aaaa + "/comment";
            Query query = db.collection(path);
            Log.d("path",path);
            FirestoreRecyclerOptions<otherone> options = new FirestoreRecyclerOptions.Builder<otherone>()
                    .setQuery(query, otherone.class).build();
            adapter = new otheroneAdapter(options);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewOtherOne);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "other one recycle error", Toast.LENGTH_SHORT).show();
            Log.d("otherone", "recycler error");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_other_one, container, false);
        //setFragment();
        setRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}