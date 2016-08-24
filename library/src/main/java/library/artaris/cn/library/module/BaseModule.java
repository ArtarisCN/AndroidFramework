package library.artaris.cn.library.module;

import android.content.Context;

/**
 * Created by Rick on 16/7/15.
 */
public abstract class BaseModule {
    public abstract void onCreate(Context context);
    public abstract void onDestroy(Context context);
}
