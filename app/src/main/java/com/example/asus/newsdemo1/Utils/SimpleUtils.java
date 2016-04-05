package com.example.asus.newsdemo1.Utils;


import com.example.asus.newsdemo1.Model.NewsSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codekk on 2016/4/4.
 * Email:  645326280@qq.com
 */
public class SimpleUtils {
    public static List<NewsSummary> makeNewsList(List<NewsSummary> news1) {
        List<NewsSummary> news = new ArrayList<>();
        for (NewsSummary n : news1) {
            if (n.hasImg == 0 && n.hasHead == 0) {
                if(n.digest!=null && !("".equals(n.digest)) && !(" ".equals(n.digest)) )
                news.add(n);
            }
        }
        return news;
    }

    public static List<String> makeDetailBodyToList(String body) {
        String[] split = body.split("IMG#");
        List<String> strings = new ArrayList<>();
        for (String s : split) {
            int i = s.indexOf("-->");
            if (i != -1) {
                s = s.substring(i + 3);
            }
            strings.add(s);
        }
        return strings;
    }
}
