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

public class NoticeFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private noticeAdapter adapter;
    private noticeSendAdapter sendAdapter;
    private View view;

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setGetRecyclerView(){
        try{
            Query query = db.collection("notice");
            FirestoreRecyclerOptions<notice> options = new FirestoreRecyclerOptions.Builder<notice>()
                    .setQuery(query, notice.class)
                    .build();
            adapter = new noticeAdapter(options);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_get);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "notice setRecyclerView Failed!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSendRecyclerView(){
        try{
            Query query = db.collection("notice");
            FirestoreRecyclerOptions<notice> options = new FirestoreRecyclerOptions.Builder<notice>()
                    .setQuery(query, notice.class)
                    .build();
            sendAdapter = new noticeSendAdapter(options);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_send);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(sendAdapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "notice setRecyclerView Failed!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notice, container, false);
        setGetRecyclerView();
        setSendRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        sendAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        sendAdapter.stopListening();
    }
}