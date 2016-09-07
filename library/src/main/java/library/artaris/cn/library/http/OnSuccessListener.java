package library.artaris.cn.library.http;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class OnSuccessListener implements Response.Listener<JSONObject> {
    private OnRequestListener listener;

    public OnSuccessListener(OnRequestListener listener){
        this.listener = listener;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        HttpResponseInfo responseInfo = new HttpResponseInfo();
        responseInfo.setData(jsonObject);
        listener.onResponse(HttpRequestManager.HTTP_SUCCESS_CODE,responseInfo,null);
    }
}
