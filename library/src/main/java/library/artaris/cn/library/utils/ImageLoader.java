package library.artaris.cn.library.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import library.artaris.cn.library.activity.BaseActivity;
import library.artaris.cn.library.fragment.ArtarisFragment;

/**
 * Created by Rick on 16/7/15.
 */
public final class ImageLoader {
    public static void with(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).into(imageView);
    }
    public static void with(BaseActivity activity, String imageUrl, ImageView imageView) {
        Glide.with(activity).load(imageUrl).into(imageView);
    }
    public static void with(ArtarisFragment fragment, String imageUrl, ImageView imageView) {
        Glide.with(fragment).load(imageUrl).into(imageView);
    }
}
