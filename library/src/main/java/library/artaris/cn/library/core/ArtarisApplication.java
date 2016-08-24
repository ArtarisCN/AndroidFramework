package library.artaris.cn.library.core;

import android.app.Application;
import android.content.Context;

import com.google.common.eventbus.EventBus;

import library.artaris.cn.library.constant.ArtarisConstant;

/**
 * Created by Rick on 16/8/24.
 */
public class ArtarisApplication extends Application {

    private EventBus mEventBus;
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initFramework();
        mApplicationContext = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private void initFramework(){
        mEventBus = new EventBus(ArtarisConstant.ARTARIS_EVENTBUS);
    }

    public EventBus getEventBus() {
        return mEventBus;
    }

    public static Context getArtarisApplicationContext() {
        return mApplicationContext;
    }

}