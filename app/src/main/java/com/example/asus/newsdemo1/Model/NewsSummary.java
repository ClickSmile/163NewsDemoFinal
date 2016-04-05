package com.example.asus.newsdemo1.Model;

import java.util.List;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */
public class NewsSummary {
    public String postid;
    public boolean hasCover;
    public int hasHead;
    public int replyCount;
    public int hasImg;
    public String digest;    //内容摘要
    public boolean hasIcon;
    public String docid;
    public String title;    //新闻标题
    public int order;
    public int priority;
    public String lmodify;
    public String boardid;
    public String photosetID;
    public String template;
    public int votecount;
    public String skipID;
    public String alias;
    public String skipType;
    public String cid;
    public int hasAD;
    public String imgsrc;
    public String tname;
    public String ename;
    public String ptime;
    public List<AdsEntity> ads;
    public List<ImgextraEntity> imgextra;

    public static class AdsEntity {
        public String title;
        public String tag;
        public String imgsrc;
        public String subtitle;
        public String url;
    }


    public static class ImgextraEntity {
        public String imgsrc;
    }


}
