package com.example.asus.newsdemo1.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codekk on 2016/4/6.
 * Email:  645326280@qq.com
 */
public class Commits {
    public String againstLock;
    public String audioLock;
    public String code;
    public String docUrl;
    @SerializedName("hotPosts")
    public List<HotPost> hotPosts = new ArrayList<HotPost>();
    public String isTagOff;


    public class HotPost {
        @SerializedName("1")   //表示对应json数据中的 "1"标签对应的值
        @Expose
        public CommitItem commitItem;
    }

    public class CommitItem {
        @SerializedName("a")
        @Expose
        public String a;
        @SerializedName("b")
        @Expose
        public String b;  //应该是评论内容
        @SerializedName("bi")
        @Expose
        public String bi;
        @SerializedName("d")
        @Expose
        public String d;    //postId吧
        @SerializedName("f")
        @Expose
        public String f;  //应该是发帖的地址和用户名 eg:网易辽宁省沈阳市手机网友&nbsp;156167481
        @SerializedName("fi")
        @Expose
        public String fi;
        @SerializedName("ip")  //ip地址
        @Expose
        public String ip;
        @SerializedName("l")
        @Expose
        public String l;
        @SerializedName("n")   //用户名
        @Expose
        public String n;
        @SerializedName("p")
        @Expose
        private String p;
        @SerializedName("pi")
        @Expose
        private String pi;
        @SerializedName("rp")
        @Expose
        private String rp;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("t")  //时间
        @Expose
        public String t;
        @SerializedName("u")
        @Expose
        private String u;
        @SerializedName("v")
        @Expose
        public String v;  //应该是点赞数

        @SerializedName("timg")
        @Expose
        public String timg;  //头像吧
    }
}
