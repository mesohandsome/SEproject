package com.seproject.babysitter;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavType;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class needs extends Fragment implements AdapterView.OnItemSelectedListener {

    private View needsView;
    private Button save_need, upload, cancel;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference myDocRef;
    private EditText note,salary;
    private Spinner who,  money,time,where;

    public needs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try {
            save_need = needsView.findViewById(R.id.btn_save_need);
            upload = needsView.findViewById(R.id.btn_upload);
            cancel = needsView.findViewById(R.id.btn_cancel);
            ImageButton btnsearch = needsView.findViewById(R.id.needs_search);
            ImageButton btnpersonal = needsView.findViewById(R.id.needs_personal);
            ImageButton btnneeds = needsView.findViewById(R.id.needs_needs);

            btnneeds.setEnabled(false);
            btnneeds.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray3)));

            btnsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_search);
                }
            });

            btnpersonal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_personalFragment);
                }
            });

            // TODO spinnners

            save_need.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateInfo();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_personalFragment);
                }
            });

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO upload
                    updateInfo();
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_needs_to_personalFragment);
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
        needsView = inflater.inflate(R.layout.fragment_needs, container, false);
        Activity act = getActivity();

         who=needsView.findViewById(R.id.sp_need_want);
        ArrayAdapter<CharSequence> adapter_find= ArrayAdapter.createFromResource(act,
                R.array.FIND,
                android.R.layout.simple_spinner_dropdown_item);
        adapter_find.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        who.setAdapter(adapter_find);
        who.setOnItemSelectedListener(this);

         time=needsView.findViewById(R.id.sp_need_time1);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(act,
                R.array.TIME,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);
        time.setOnItemSelectedListener(this);

         where=needsView.findViewById(R.id.sp_need_place);
        ArrayAdapter<CharSequence> adapter_palace= ArrayAdapter.createFromResource(act,
                R.array.PLACE,
                android.R.layout.simple_spinner_dropdown_item);
        adapter_palace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        where.setAdapter(adapter_palace);
        where.setOnItemSelectedListener(this);

         money=needsView.findViewById(R.id.sp_need_money_dh);
        ArrayAdapter<CharSequence> adapter_money= ArrayAdapter.createFromResource(act,
                R.array.MONEY,
                android.R.layout.simple_spinner_dropdown_item);
        adapter_money.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        money.setAdapter(adapter_money);
        money.setOnItemSelectedListener(this);
        allClickListener();

        return needsView;
    }
    private void updateInfo()
    {
        salary=needsView.findViewById(R.id.edt_need_money);
        note=needsView.findViewById(R.id.edt_salary);
        String up_salary=salary.getText().toString().trim();
        String up_note=note.getText().toString().trim();
        int who_save,where_save,time_save;
        if(who.getSelectedItem().toString().equals("寶寶"))
            who_save=1;

        else
            who_save=0;
        if(where.getSelectedItem().toString().equals("寶寶家"))
            where_save=1;

        else
            where_save=0;
        if(time.getSelectedItem().toString().equals("彈性"))
            time_save=1;

        else
            time_save=0;



        try {
            String uid = FirebaseAuth.getInstance().getUid();
            String path = "needs/" + uid;
            Map<String, Object> dataToSave = new HashMap<String, Object>();
            dataToSave.put("name", who_save);
            dataToSave.put("place", where_save);
            dataToSave.put("salary", up_salary);
            dataToSave.put("time", time_save);
            dataToSave.put("note", up_note);
            dataToSave.put("UID", uid);
            myDocRef = FirebaseFirestore.getInstance().document(path);
            myDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("", "Saved!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("", "Failed!");
                }
            });


        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(getContext(), "HI", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}