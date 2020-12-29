package com.seproject.babysitter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class BabysitterFragment extends Fragment {

    private View babysitterFragmentView;
    private FirebaseAuth FirebaseAuth;
    private Button babysitter_save_data, babysitter_cancel_data;
    private Button upload_pic;
    private EditText babysitter_name,babysitter_sexual,babysitter_age,babysitter_environment;
    private EditText babysitter_time,babysitter_number,babysitter_money,babysitter_experience,
            babysitter_notice;
    private DocumentReference myDocRef;
    private ImageView babysitter_pic;
    public  Uri imageUrl;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private String uid;
    public BabysitterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void saveInfo(){
        //-----update babysitter profile-----//
        //-----Document name by : UID-----//
        String age = babysitter_age.getText().toString().trim();
        String name = babysitter_name.getText().toString().trim();
        String sexual = babysitter_sexual.getText().toString().trim();
        String salary = babysitter_money.getText().toString().trim();
        String environment = babysitter_environment.getText().toString().trim();
        String experience = babysitter_experience.getText().toString().trim();
        String number = babysitter_number.getText().toString().trim();
        String notice = babysitter_notice.getText().toString().trim();
        String time = babysitter_time.getText().toString().trim();
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("age", age);
        dataToSave.put("name", name);
        dataToSave.put("sexual", sexual);
        dataToSave.put("salary", salary);
        dataToSave.put("environment", environment);
        dataToSave.put("experience", experience);
        dataToSave.put("number", number);
        dataToSave.put("notice", notice);
        dataToSave.put("time", time);
        dataToSave.put("UID", uid);
        String path = "babysitter/" + uid;
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
    private void allClickListener(){
        try {
            babysitter_save_data = babysitterFragmentView.findViewById(R.id.babysitter_save_data);
            babysitter_cancel_data = babysitterFragmentView.findViewById(R.id.babysitter_cancel_data);
            babysitter_save_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveInfo();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_babysitterFragment_to_personalFragment);
                }
            });
            upload_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadPicToDB();



                }
            });
            babysitter_cancel_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_babysitterFragment_to_personalFragment);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void  downloadPic()
    {
        StorageReference imgRef=mStorageRef.child("images/"+uid+"_babysitter");
        try {
            final File download_img=File.createTempFile("image","jpg");

            imgRef.getFile(download_img)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            Bitmap bitmap= BitmapFactory.decodeFile(download_img.getAbsolutePath());
                            babysitter_pic.setImageBitmap(bitmap);
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
    private void  uploadPicToDB()
    {
        StorageReference imgRef=mStorageRef.child("images/"+uid+"_babysitter");
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
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private void updateInfo()
    {
        try {
            String path = "babysitter/" + uid;
            myDocRef = FirebaseFirestore.getInstance().document(path);
            myDocRef.get().addOnCompleteListener((task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot d = task.getResult();
                    String name = d.getString("name");
                    babysitter_name.append(name);
                    String age = d.getString("age");
                    babysitter_age.append(age);
                    String environment = d.getString("environment");
                    babysitter_environment.append(environment);
                    String experience = d.getString("experience");
                    babysitter_experience.append(experience);
                    String notice = d.getString("notice");
                    babysitter_notice.append(notice);
                    String number = d.getString("number");
                    babysitter_number.append(number);
                    String sexual = d.getString("sexual");
                    babysitter_sexual.append(sexual);
                    String salary = d.getString("salary");
                    babysitter_money.append(salary);
                    String time = d.getString("time");
                    babysitter_time.append(time);

                }
            }));
            downloadPic();
        }
        catch (Exception e){
            Toast.makeText(getContext(), "updata info error", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpload_pic()
    {
        babysitter_pic.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==-1 && data!=null){

            imageUrl=data.getData();
            babysitter_pic.setImageURI(imageUrl);
        }

    }

    private  void attachToXML()
    {
        uid = FirebaseAuth.getInstance().getUid();
        babysitter_name = babysitterFragmentView.findViewById(R.id.et_name);
        babysitter_sexual = babysitterFragmentView.findViewById(R.id.et_sexual);
        babysitter_age = babysitterFragmentView.findViewById(R.id.et_age);
        babysitter_time = babysitterFragmentView.findViewById(R.id.edt_time);
        babysitter_environment = babysitterFragmentView.findViewById(R.id.edt_environment);
        babysitter_experience = babysitterFragmentView.findViewById(R.id.edt_experience);
        babysitter_notice = babysitterFragmentView.findViewById(R.id.edt_else);
        babysitter_money = babysitterFragmentView.findViewById(R.id.edt_money);
        babysitter_number = babysitterFragmentView.findViewById(R.id.edt_number);
        FirebaseAuth = FirebaseAuth.getInstance();
        upload_pic=babysitterFragmentView.findViewById(R.id.btn_pic);
        babysitter_pic=babysitterFragmentView.findViewById(R.id.iv_pic);
        babysitter_save_data = babysitterFragmentView.findViewById(R.id.babysitter_save_data);
        storage=FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        babysitterFragmentView = inflater.inflate(R.layout.fragment_babysitter, container, false);
        attachToXML();
        setUpload_pic();
        updateInfo();
        allClickListener();

        return babysitterFragmentView;
    }
}