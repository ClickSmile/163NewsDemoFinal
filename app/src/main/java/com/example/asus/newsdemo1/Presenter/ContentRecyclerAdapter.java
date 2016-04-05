package com.example.asus.newsdemo1.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.newsdemo1.R;

import java.util.List;

/**
 * Created by ASUS on 2016/4/1.
 */
public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.ContentRecyclerViewHolder> {


    private List<String> data;
    private Context context;

    public ContentRecyclerAdapter(Context context1, List<String> data1){
        context=context1;
        data=data1;
    }

    @Override
    public ContentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itmeView=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_content,parent,false);
        return new ContentRecyclerViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(ContentRecyclerViewHolder holder, final int position) {
        holder.textView.setText(data.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ContentRecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ContentRecyclerViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textViewContentRecyclerItem);
        }
    }
}
