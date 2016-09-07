package library.artaris.cn.library.core;

import android.app.Application;
import android.content.Context;

import com.google.common.eventbus.EventBus;

import library.artaris.cn.library.constant.ArtarisConstant;
import library.artaris.cn.library.http.HttpRequestManager;

/**
 * Created by Rick on 16/8/24.
 */
public class BaseApplication extends Application {

    private EventBus mEventBus;
    private static Context mApplicationContext;
    private HttpRequestManager mNetworkManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();

        initFramework();
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
        HttpRequestManager.initVolleyQueue(getBaseApplicationContext(), mEventBus);
        mNetworkManager = HttpRequestManager.getInstance();
    }

    public EventBus getEventBus() {
        return mEventBus;
    }

//    public NetworkManager getNetworkManager() {
//        return mNetworkManager;
//    }

    public static Context getBaseApplicationContext() {
        return mApplicationContext;
    }

}