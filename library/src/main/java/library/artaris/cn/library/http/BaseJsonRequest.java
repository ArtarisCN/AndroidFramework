package library.artaris.cn.library.http;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


/**
 * Created by Rick
 * on 16/9/7.
 */
public class BaseJsonRequest extends JsonObjectRequest{
    private Context mContext;
    private String url;
    private OnRequestListener mListener;

    public BaseJsonRequest(final Context context, BaseRequestInfo info,OnRequestListener listener){
        super(info.getMethod(), info.getUrl(),info.getRequestBody(), new OnSuccessListener(listener), new OnErrorListener(listener));
        this.mListener = listener;
        this.mContext = context;
    }
}
