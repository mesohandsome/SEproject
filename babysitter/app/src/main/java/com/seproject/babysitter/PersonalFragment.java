package com.seproject.babysitter;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.Distribution;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

import org.w3c.dom.Text;

import java.util.Collection;

public class PersonalFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private personalAdapter adapter;
    private View view;

    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(getContext(), "Are you sure to Logout?", Toast.LENGTH_SHORT).show();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    private void allClickListener(){
        try{
            String TAG = "testing";
            Log.d(TAG, "allClickListener: ");

            Button btnbaby = view.findViewById(R.id.btn_baby);
            Button btnbabysitter = view.findViewById(R.id.btn_babysitter);
            Button btnnotice = view.findViewById(R.id.btn_notice);
            Button btnstore = view.findViewById(R.id.btn_store);
            Button btnsetting = view.findViewById(R.id.btn_setting);
            ImageButton btnsearch = view.findViewById(R.id.personal_search);
            ImageButton btnpersonal = view.findViewById(R.id.personal_personal);
            ImageButton btnneeds = view.findViewById(R.id.personal_needs);
            TextView nickname = view.findViewById(R.id.personal_tv_real_name);
            TextView realname = view.findViewById(R.id.personal_tv_name);
            TextView mailaddr = view.findViewById(R.id.personal_tv_email);

            // getuid().getname()
            String path = "users/123/";


            btnsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_search);
                }
            });

            btnpersonal.setEnabled(false);
            btnpersonal.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray3)));

            btnneeds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_search_whichFragment);
                }
            });


            btnsetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_settingsFragment);
                }
            });

            btnbaby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_mybabyFragment);
                }
            });

            btnbabysitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_babysitterFragment);
                }
            });

            btnnotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_noticeFragment);
                }
            });

            btnstore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_personalFragment_to_storeFragment);
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private  void setRecyclerView() {
        try {
            Query query = db.collection("recycler_testing");

            FirestoreRecyclerOptions<information> options = new FirestoreRecyclerOptions.Builder<information>()
                    .setQuery(query, information.class)
                    .build();

            adapter = new personalAdapter(options);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPersonal);
            //recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Personal setRecyclerView() Failed!!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        allClickListener();
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