package library.artaris.cn.library.http;

import com.android.volley.Request;

import org.json.JSONObject;

import java.util.Map;

import library.artaris.cn.library.constant.UrlConstant;

/**
 * Created by Rick
 * on 16/9/7.
 */
public abstract class BaseRequestInfo {
    public static final int GET = Request.Method.GET;
    public static final int POST = Request.Method.POST;

    private int method;
    private static String HOST = UrlConstant.HttpHost;

    public abstract String getPath();
    public abstract Map<String, Object> getPostParamsMap();


    public BaseRequestInfo(int method) {
        this.method = method;
    }

    public int getMethod() {
        return method;
    }

    public JSONObject getRequestBody() {
        Map<String, Object> map = getPostParamsMap();
        if (map != null) {
            return new JSONObject(map);
        }
        return null;
    }

    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(HOST).append(getPath());
        return sb.toString();
    }

}
