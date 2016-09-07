package library.artaris.cn.library.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.common.eventbus.EventBus;

import library.artaris.cn.library.constant.UrlConstant;
//import library.artaris.cn.library.net.NetworkRequest;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class HttpRequestManager {
    public static int HTTP_SUCCESS_CODE = 200;
    public static int HTTP_FAILD_CODE = -1;

    protected static HttpRequestManager instance;
    protected RequestQueue mQueue;
    protected Context mContext;
    protected EventBus mEventBus;

    public HttpRequestManager(Context context, EventBus eventBus){
        this.mContext = context;
        this.mEventBus = eventBus;
        mQueue = Volley.newRequestQueue(context);
    }

    public static void initVolleyQueue(Context context, EventBus eventBus){
        if (instance == null)
            instance = new HttpRequestManager(context, eventBus);
    }

    public static HttpRequestManager getInstance(){
        return instance;
    }

    public void addRequest(BaseJsonRequest request){
        mQueue.add(request);
    }
}
