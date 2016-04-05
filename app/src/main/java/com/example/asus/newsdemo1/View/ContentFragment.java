package com.example.asus.newsdemo1.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.newsdemo1.Presenter.ContentRecyclerAdapter;
import com.example.asus.newsdemo1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/4/1.
 */
public class ContentFragment extends Fragment{
    private RecyclerView recyclerView;
    private ContentRecyclerAdapter recyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerViewContent);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> strings=new ArrayList<>();
        for(int i=0;i<50;i++){
            strings.add("xiaohuoKK"+i);
        }
        recyclerAdapter=new ContentRecyclerAdapter(getContext(),strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
