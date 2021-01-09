package com.seproject.babysitter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MybabyFragment extends Fragment {

    private View mybabyFragmentView;
    private Button save_data, cancel_data;
    private EditText baby_name,baby_sexual,baby_age,baby_hobbit;
    private EditText baby_time,baby_eat,baby_car,baby_personality,
            baby_precaution,baby_salary;
    private String uid;
    private FirebaseAuth FirebaseAuth;
    private Button upload_pic;
    private ImageView baby_pic;
    private FirebaseStorage storage;
    private StorageReference mStorageRef;
    private Uri imageUrl;
    private DocumentReference myDocRef;

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
                    saveInfo();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_mybabyFragment_to_personalFragment);
                }
            });
            upload_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadPicToDB();



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
    private void saveInfo(){
        //-----update babysitter profile-----//
        //-----Document name by : UID-----//
        String age = baby_age.getText().toString().trim();
        String name = baby_name.getText().toString().trim();
        String sexual = baby_sexual.getText().toString().trim();
        String salary = baby_salary.getText().toString().trim();
        String hobbit= baby_hobbit.getText().toString().trim();
        String precaution = baby_precaution.getText().toString().trim();
        String personality = baby_personality.getText().toString().trim();
        String car = baby_car.getText().toString().trim();
        String eat = baby_eat.getText().toString().trim();
        String time = baby_time.getText().toString().trim();
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("age", age);
        dataToSave.put("name", name);
        dataToSave.put("sexual", sexual);
        dataToSave.put("salary", salary);
        dataToSave.put("hobbit", hobbit);
        dataToSave.put("precaution", precaution);
        dataToSave.put("personality", personality);
        dataToSave.put("car",car);
        dataToSave.put("time", time);
        dataToSave.put("eat", eat);
        dataToSave.put("UID", uid);
        String path = "baby/" + uid;
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
    }
    private void  downloadPic()
    {
        StorageReference imgRef=mStorageRef.child("images/"+uid+"_baby");
        try {
            final File download_img=File.createTempFile("image","jpg");

            imgRef.getFile(download_img)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            Bitmap bitmap= BitmapFactory.decodeFile(download_img.getAbsolutePath());
                            baby_pic.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    private void  uploadPicToDB() {
        StorageReference imgRef = mStorageRef.child("images/" + uid + "_baby");
        try {
            imgRef.putFile(imageUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            //Snackbar.make(babysitterFragmentView.findViewById(android.R.id.content),"Image Upload",Snackbar.LENGTH_LONG).show();
                            Toast.makeText(getContext(), "Upload Succeed", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads

                        }
                    });
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void setUpload_pic()
    {
        baby_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }
    private  void choosePicture()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }
    private void updateInfo()
    {
        try {
            String path = "baby/" + uid;
            myDocRef = FirebaseFirestore.getInstance().document(path);
            myDocRef.get().addOnCompleteListener((task -> {
                if (task.isSuccessful()){
                    DocumentSnapshot d=task.getResult();
                    if(d.exists()) {
                        String name = d.getString("name");
                        baby_name.append(name);
                        String age = d.getString("age");
                        baby_age.append(age);
                        String personality = d.getString("personality");
                        baby_personality.append(personality);
                        String precaution = d.getString("precaution");
                        baby_precaution.append(precaution);
                        String time = d.getString("time");
                        baby_time.append(time);
                        String car = d.getString("car");
                        baby_car.append(car);
                        String salary = d.getString("salary");
                        baby_salary.append(salary);
                        String hobbit = d.getString("hobbit");
                        baby_hobbit.append(hobbit);
                        String sexual = d.getString("sexual");
                        baby_sexual.append(sexual);
                        String eat= d.getString("eat");
                        baby_eat.append(eat);

                    }

                }
            }));
            downloadPic();
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
        private  void attachToXML()
    {
        uid = FirebaseAuth.getInstance().getUid();
        baby_name = mybabyFragmentView.findViewById(R.id.et_name);
        baby_sexual = mybabyFragmentView.findViewById(R.id.et_sexual);
        baby_age = mybabyFragmentView.findViewById(R.id.et_age);
        baby_time = mybabyFragmentView.findViewById(R.id.edt_time);
        baby_car = mybabyFragmentView.findViewById(R.id.edt_car);
        baby_personality = mybabyFragmentView.findViewById(R.id.edt_personality);
        baby_precaution = mybabyFragmentView.findViewById(R.id.edt_precaution);
        baby_salary = mybabyFragmentView.findViewById(R.id.edt_salary);
        baby_hobbit = mybabyFragmentView.findViewById(R.id.et_hobbit);
        baby_eat= mybabyFragmentView.findViewById(R.id.edt_eat);
        FirebaseAuth = FirebaseAuth.getInstance();
        upload_pic= mybabyFragmentView.findViewById(R.id.btn_pic);
        baby_pic=mybabyFragmentView.findViewById(R.id.iv_pic);
        save_data = mybabyFragmentView.findViewById(R.id.btn_save_data);
        storage= FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==-1 && data!=null){

            imageUrl=data.getData();
            baby_pic.setImageURI(imageUrl);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mybabyFragmentView = inflater.inflate(R.layout.fragment_mybaby, container, false);
        attachToXML();
        setUpload_pic();
        updateInfo();
        allClicklistener();
        return mybabyFragmentView;
    }
}