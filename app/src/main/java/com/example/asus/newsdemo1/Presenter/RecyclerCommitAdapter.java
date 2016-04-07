package com.example.asus.newsdemo1.Presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.newsdemo1.Activities.ImageClickedAty;
import com.example.asus.newsdemo1.Model.Commits;
import com.example.asus.newsdemo1.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by codekk on 2016/4/6.
 * Email:  645326280@qq.com
 */
public class RecyclerCommitAdapter extends RecyclerView.Adapter<RecyclerCommitAdapter.CommitViewholer> {

    private final List<Commits.HotPost> hotPosts;
    private final Context context;
    private final String default_icon = "http://img4.imgtn.bdimg.com/it/u=3769150338,1023604346&fm=21&gp=0.jpg";

    public RecyclerCommitAdapter(Context contex1, List<Commits.HotPost> hotPosts1) {
        context = contex1;
        hotPosts = hotPosts1;
    }

    @Override
    public CommitViewholer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_commits, parent, false);
        return new CommitViewholer(view);
    }

    @Override
    public void onBindViewHolder(final CommitViewholer holder, final int position) {
        final String name = hotPosts.get(position).commitItem.n;
        if (name == null || name.equals(" ") || name.equals("")) {
            holder.textViewUsername.setText("火星网友");
        } else {
            holder.textViewUsername.setText(name);
        }
        holder.textViewContent.setText(hotPosts.get(position).commitItem.b);
        holder.textViewAddress.setText(Html.fromHtml(hotPosts.get(position).commitItem.f));
        holder.textViewDianzanCount.setText(hotPosts.get(position).commitItem.v);
        final String uri = hotPosts.get(position).commitItem.timg;
        if (uri == null) {
            holder.simpleDraweeViewIcon.setImageURI(Uri.parse(default_icon));
        } else {
            holder.simpleDraweeViewIcon.setImageURI(Uri.parse(uri));
        }
        holder.simpleDraweeViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageClickedAty.class);
                if (uri != null) {
                    intent.putExtra("imgUri", uri);
                }else{
                    intent.putExtra("imgUri", default_icon);
                }
                intent.putExtra("HeadTitle", name);
                context.startActivity(intent);
            }
        });

        holder.textViewDianzanCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(hotPosts.get(position).commitItem.v);
                i++;
                holder.textViewDianzanCount.setText(i + "");
                holder.textViewDianzanCount.setTextColor(Color.BLUE);
                holder.imageViewDianzan.setImageResource(R.drawable.dianzan2);
                holder.imageViewDianzan.setClickable(false);
                holder.textViewDianzanCount.setClickable(false);
            }
        });

        holder.imageViewDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(hotPosts.get(position).commitItem.v);
                i++;
                holder.textViewDianzanCount.setText(i + "");
                holder.textViewDianzanCount.setTextColor(Color.BLUE);
                holder.imageViewDianzan.setImageResource(R.drawable.dianzan2);
                holder.imageViewDianzan.setClickable(false);
                holder.textViewDianzanCount.setClickable(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotPosts.size();
    }

    public class CommitViewholer extends RecyclerView.ViewHolder {

        private TextView textViewUsername;
        private TextView textViewContent;
        private TextView textViewAddress;
        private TextView textViewDianzanCount;
        private ImageView imageViewDianzan;
        private SimpleDraweeView simpleDraweeViewIcon;

        public CommitViewholer(View itemView) {
            super(itemView);
            textViewUsername = (TextView) itemView.findViewById(R.id.textViewUsernameCommitsShow);
            textViewContent = (TextView) itemView.findViewById(R.id.textViewContentCommitsShow);
            textViewAddress = (TextView) itemView.findViewById(R.id.textViewAddressCommitsShow);
            textViewDianzanCount = (TextView) itemView.findViewById(R.id.textViewDianZanCountCommitsShow);
            imageViewDianzan = (ImageView) itemView.findViewById(R.id.imageViewDianzanCommitsShow);
            simpleDraweeViewIcon = (SimpleDraweeView) itemView.findViewById(R.id.imageViewIconCommitsShow);
        }
    }
}
