package com.example.whatsappclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsappclone.R;
import com.example.whatsappclone.model.ChatList;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.Holder> {

    private List<ChatList> list;
    private Context context;

    public ChatListAdapter(List<ChatList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chat_list,parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        ChatList chatList= list.get(position);

        holder.tvName.setText(chatList.getUserName());
        holder.tvDesc.setText(chatList.getDescription());
        holder.tvDate.setText(chatList.getDate());


        Glide.with(context).load(chatList.getUrlProfileImage()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;

        private TextView tvName,tvDesc,tvDate;


        public Holder(@NonNull View itemView) {
            super(itemView);

            profileImage=itemView.findViewById(R.id.image_profile);
            tvName=itemView.findViewById(R.id.tv_name);
            tvDate=itemView.findViewById(R.id.tv_date);
            tvDesc=itemView.findViewById(R.id.tv_desc);
        }
    }
}
