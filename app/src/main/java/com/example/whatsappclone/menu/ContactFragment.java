package com.example.whatsappclone.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappclone.R;
import com.example.whatsappclone.adapter.CallListAdapter;
import com.example.whatsappclone.adapter.ChatListAdapter;
import com.example.whatsappclone.model.CallList;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view= inflater.inflate(R.layout.fragment_contact, container, false);

    List<CallList> list = new ArrayList<>();
    RecyclerView recyclerView;
    recyclerView=view.findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    list.add(new CallList("1","Neo","20/11/2021, 9:30AM","https://www.rocketmortgage.com/resources-cmsassets/RocketMortgage.com/Article_Images/Large_Images/TypesOfHomes/types-of-homes-hero.jpg","missed"));
    list.add(new CallList("2","Boo","20/11/2031, 9:30AM","https://www.rocketmortgage.com/resources-cmsassets/RocketMortgage.com/Article_Images/Large_Images/TypesOfHomes/types-of-homes-hero.jpg","income"));
    list.add(new CallList("3","BizBiz","20/11/2041, 9:30AM","https://www.rocketmortgage.com/resources-cmsassets/RocketMortgage.com/Article_Images/Large_Images/TypesOfHomes/types-of-homes-hero.jpg","outcome"));


    recyclerView.setAdapter(new CallListAdapter(list,getContext()));

    return view;
    }
}