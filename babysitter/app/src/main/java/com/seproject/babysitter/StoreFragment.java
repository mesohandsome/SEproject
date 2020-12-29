package com.seproject.babysitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class StoreFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private favoriteAdapter adapter;
    private View view;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setRecyclerView(){
        try{
            Query query = db.collection("favorite");
            FirestoreRecyclerOptions<favorite> options = new FirestoreRecyclerOptions.Builder<favorite>()
                    .setQuery(query, favorite.class).build();
            adapter = new favoriteAdapter(options);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavorite);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Favorite setRecyclerView() Failed!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_store, container, false);
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