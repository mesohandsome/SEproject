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

public class noticeSendAdapter extends FirestoreRecyclerAdapter<notice, noticeSendAdapter.noticeSendViewHolder> {

    public noticeSendAdapter(@NonNull FirestoreRecyclerOptions<notice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull noticeSendViewHolder noticeViewHolder, int i, @NonNull notice notice) {
        noticeViewHolder.text1.setText("向您發送了");
        noticeViewHolder.text2.setText("聯絡資訊");
        noticeViewHolder.text3.setText("查看請求");
        noticeViewHolder.name.setText(notice.getName());
    }

    @NonNull
    @Override
    public noticeSendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice_send, parent, false);
        return new noticeSendViewHolder(view);
    }

    class noticeSendViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView photo;
        TextView text1, text2, text3;

        public noticeSendViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.send_tv_name);
            text1 = itemView.findViewById(R.id.stext_1);
            text2 = itemView.findViewById(R.id.stext2);
            text3 = itemView.findViewById(R.id.stext3);
            photo = itemView.findViewById(R.id.send_iv_image);
        }
    }

}
