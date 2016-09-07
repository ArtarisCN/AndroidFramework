package library.artaris.cn.library.http;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class OnErrorListener implements Response.ErrorListener{
    private OnRequestListener listener;

    public OnErrorListener(OnRequestListener listener){
        this.listener = listener;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        BaseHttpError error = new BaseHttpError(volleyError.getMessage(),volleyError);
        listener.onResponse(HttpRequestManager.HTTP_FAILD_CODE,null,error);
    }
}
