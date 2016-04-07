package com.example.asus.newsdemo1.Model;

import java.util.List;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */
public class NewsDetail {
    public String body;
    public int replyCount;
    public String digest;
    public String dkeys;
    public String ec;
    public String docid;
    public List<ImgEntity> img;

    public SourceinfoEntity sourceinfo;
    public boolean picnews;
    public String title;
    public String tid;
    public String template;
    public int threadVote;
    public int threadAgainst;
    public String replyBoard;
    public String source;   //新闻来自哪里，比如 新华社
    public String voicecomment;
    public boolean hasNext;
    public String ptime;
    public List<?> users;
    public List<?> ydbaike;
    public List<?> link;
    public List<?> votes;

    public static class ImgEntity {
        public String ref;
        public String pixel;
        public String alt;
        public String src;
    }

    public static class SourceinfoEntity {
        public String alias;
        public String tname;
        public String ename;
        public String tid;
    }
}
