package com.seproject.babysitter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class noticeAdapter extends FirestoreRecyclerAdapter<notice, noticeAdapter.noticeViewHolder> {

    public noticeAdapter(@NonNull FirestoreRecyclerOptions<notice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull noticeViewHolder noticeViewHolder, int i, @NonNull notice notice) {
        noticeViewHolder.text1.setText("向您傳送了");
        noticeViewHolder.text2.setText("聯絡資訊");
        noticeViewHolder.text3.setText("是否要查看資訊？");
        noticeViewHolder.name.setText(notice.getName());
    }

    @NonNull
    @Override
    public noticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice_get, parent, false);
        return new noticeViewHolder(view);
    }

    class noticeViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView photo;
        TextView text1, text2, text3;

        public noticeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.notice_tv_name);
            text1 = itemView.findViewById(R.id.notice_1);
            text2 = itemView.findViewById(R.id.notice_2);
            text3 = itemView.findViewById(R.id.notice_3);
            photo = itemView.findViewById(R.id.notice_iv_image);
        }
    }

}
