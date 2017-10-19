package com.santosh.miniredditapp.databinder;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.util.UserFriendlyTimeConverterUtil;

/**
 * Custom Setter class
 * This is a simple utility class which contains a single method named setImageUrl()
 * which takes an ImageView and a String URL as arguments.
 * When invoked it performs the Glide operation.
 */
public class GlideImageLoaderHandler {


    private GlideImageLoaderHandler() {
    }

    /**
     *
     * @BindingAdapter("thumbnail") tells data binding library that this is a custom setter names "thumbnail"
     * We don’t need to declare this anywhere in the code
     * The annotation itself is enough to ensure that this is recognised at build time
     * @param imageView
     * @param url
     */
    @BindingAdapter("thumbnail")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .placeholder(context.getResources().getDrawable(R.drawable.palceholder_image))
                .into(imageView);
    }



}
