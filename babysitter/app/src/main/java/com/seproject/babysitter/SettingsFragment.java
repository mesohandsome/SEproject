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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class SettingsFragment extends Fragment {

    private View SettingsFragmentView;
    private Button save_passwd, cancel_passwd, save_data, cancel_data;
    private ImageView person_pic;
    private StorageReference mStorageRef;
    private String uid;
    private Uri imageUrl;
    private Button save_pic;
    private FirebaseStorage storage;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void allClickListener(){
        try {
            save_passwd = SettingsFragmentView.findViewById(R.id.btn_save_password);
            cancel_passwd = SettingsFragmentView.findViewById(R.id.btn_cancel_password);
            save_data = SettingsFragmentView.findViewById(R.id.btn_save_data);
            cancel_data = SettingsFragmentView.findViewById(R.id.btn_cancel_data);

            save_passwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });

            cancel_passwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });

            save_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });

            cancel_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_settingsFragment_to_personalFragment);
                }
            });
            save_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadPicToDB();
                }
            });

        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void  uploadPicToDB() {

        StorageReference imgRef = mStorageRef.child("images/" + uid + "_person");
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
        person_pic.setOnClickListener(new View.OnClickListener() {
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
            person_pic.setImageURI(imageUrl);
        }

    }
    private void  downloadPic()
    {
        StorageReference imgRef=mStorageRef.child("images/"+uid+"_person");
        try {
            final File download_img=File.createTempFile("image","jpg");

            imgRef.getFile(download_img)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            Bitmap bitmap= BitmapFactory.decodeFile(download_img.getAbsolutePath());
                            person_pic.setImageBitmap(bitmap);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SettingsFragmentView =  inflater.inflate(R.layout.fragment_settings, container, false);
        save_pic= SettingsFragmentView.findViewById(R.id.btn_pic);
        person_pic=SettingsFragmentView.findViewById(R.id.iv_pic);
        uid = FirebaseAuth.getInstance().getUid();
        storage= FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        setUpload_pic();
        downloadPic();
        allClickListener();
        return SettingsFragmentView;
    }
}