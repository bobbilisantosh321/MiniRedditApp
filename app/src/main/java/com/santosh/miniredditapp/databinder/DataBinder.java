package com.santosh.miniredditapp.databinder;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.util.UserFriendlyTimeConverterUtil;


public class DataBinder {

    private DataBinder() {
        //NO-OP
    }

    @BindingAdapter("thumbnail")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .placeholder(context.getResources().getDrawable(R.drawable.palceholder_image))
                .into(imageView);
    }



}
