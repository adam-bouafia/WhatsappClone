package com.example.whatsappclone.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.ChatListAdapter;
import com.example.whatsappclone.model.ChatList;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    public ChatsFragment() {
        // Required empty public constructor
    }

    private List<ChatList> list = new ArrayList<>();
    private RecyclerView recyclerView;

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getChatList();
        }

    private void getChatList() {

       list.add(new ChatList("1","Adam","yolo","9/3/2021","https://www.rocketmortgage.com/resources-cmsassets/RocketMortgage.com/Article_Images/Large_Images/TypesOfHomes/types-of-homes-hero.jpg"));
       list.add(new ChatList("2","Mada","Bolo","9/3/2031","https://www.rocketmortgage.com/resources-cmsassets/RocketMortgage.com/Article_Images/Large_Images/TypesOfHomes/types-of-homes-apartment-1.jpg"));
       list.add(new ChatList("3","Amda","Solo","9/3/2041","https://www.rocketmortgage.com/resources-cmsassets/RocketMortgage.com/Article_Images/Large_Images/TypesOfHomes/types-of-homes-condo-2.jpg"));
    }


    @Override

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new ChatListAdapter(list,getContext()));

        return view;

    }
}