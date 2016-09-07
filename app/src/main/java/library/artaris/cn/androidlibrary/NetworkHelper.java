package library.artaris.cn.androidlibrary;


import android.content.Context;

import library.artaris.cn.library.http.BaseHttpError;
import library.artaris.cn.library.http.BaseJsonRequest;
import library.artaris.cn.library.http.HttpRequestManager;
import library.artaris.cn.library.http.HttpResponseInfo;
import library.artaris.cn.library.http.OnRequestListener;
import library.artaris.cn.library.json.JSONParser;
import library.artaris.cn.library.utils.logger.Logger;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class NetworkHelper {
    private static NetworkHelper instance;
    private HttpRequestManager mManager;
    private Context context;

    private NetworkHelper(){
        mManager = HttpRequestManager.getInstance();
    }

    public static NetworkHelper getInstance(){
        if (instance == null)
            instance = new NetworkHelper();

        return instance;
    }

    public void GetList(final OnRequestListener listener){
        BaseJsonRequest request =  new BaseJsonRequest(context, new GetLastInfo(), new OnRequestListener() {
            @Override
            public void onResponse(int requestCode, HttpResponseInfo requestInfo, BaseHttpError error) {
                Info infodata = JSONParser.getInstance().parserJSONObject(requestInfo.getData(),Info.class);
                Logger.d(infodata.getDate().get(0).getName());
                GetListResponseInfo info = new GetListResponseInfo();
                info.setDate(infodata);
                listener.onResponse(requestCode,info,error);
            }
        });
        mManager.addRequest(request);
    }
}
