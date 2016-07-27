package cn.artaris.framework.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.artaris.framework.core.ArtarisActivity;
import cn.artaris.framework.core.ArtarisFragment;

/**
 * Created by Rick on 16/7/15.
 */
public class ImageLoader {
    public static void with(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).into(imageView);
    }
    public static void with(ArtarisActivity activity, String imageUrl, ImageView imageView) {
        Glide.with(activity).load(imageUrl).into(imageView);
    }
    public static void with(ArtarisFragment fragment, String imageUrl, ImageView imageView) {
        Glide.with(fragment).load(imageUrl).into(imageView);
    }
}
