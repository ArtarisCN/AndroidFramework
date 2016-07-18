package cn.artaris.framework.artarisframework.core;

import android.content.Context;

import com.activeandroid.app.Application;
import com.google.common.eventbus.EventBus;


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
