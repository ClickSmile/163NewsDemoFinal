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

    public static class ImgEntity {
        public String ref;
        public String pixel;
        public String alt;
        public String src;
    }
}
