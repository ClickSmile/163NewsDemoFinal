package com.example.asus.newsdemo1.Presenter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by codekk on 2016/4/4.
 * Email:  645326280@qq.com
 */
public class MyBannerViewholder implements Holder<String> {
    private SimpleDraweeView simpleDraweeView;
    @Override
    public View createView(Context context) {
        simpleDraweeView=new SimpleDraweeView(context);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        return simpleDraweeView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        simpleDraweeView.setImageURI(Uri.parse(data));
    }
}
