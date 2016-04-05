package com.example.asus.newsdemo1.Presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.newsdemo1.Activities.NewsDetailAty;
import com.example.asus.newsdemo1.Model.NewsSummary;
import com.example.asus.newsdemo1.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */
public class RecyclerHeadlineAdapter extends RecyclerView.Adapter<RecyclerHeadlineAdapter.MyViewHolder> {

    private Context context;
    private List<NewsSummary> news;

    public RecyclerHeadlineAdapter(Context context1, List<NewsSummary> news1) {
        context = context1;
        news = news1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_headline, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.imageViewIcon.setImageURI(Uri.parse(news.get(position).imgsrc));
        holder.textViewTitle.setText(news.get(position).title);
        holder.textViewSubtitle.setText(news.get(position).digest);
        holder.itemView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailAty.class);
                intent.putExtra("postId", news.get(position).postid);
                intent.putExtra("title", news.get(position).title);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imageViewIcon;
        private TextView textViewTitle;
        private TextView textViewSubtitle;
        private View itemView1;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView1 = itemView;
            imageViewIcon = (SimpleDraweeView) itemView.findViewById(R.id.imageViewContentItemIcon);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewContentTitle);
            textViewSubtitle = (TextView) itemView.findViewById(R.id.textViewContentSubtitle);
        }
    }
}
