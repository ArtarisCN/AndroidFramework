package library.artaris.cn.library.http;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class BaseHttpError extends VolleyError implements Serializable {
    private int code = -1;
    private String message;
    private String data;
    private JSONObject jObj;

    public BaseHttpError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public void setData(String data) {
        this.data = data;
    }

    public BaseHttpError(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        if (code == -1 && message == null)
            return "您的网络状况不佳！";
        else
            return message;
    }


    public String getBody(){
        if (data == null || "".equals(data))
            return null;

        String result = null;

        try {
            JSONObject jsonObj = new JSONObject(data);

            if (jsonObj.has("data"))
                result = jsonObj.getString("data");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void setJsonObj(JSONObject jobj){
        this.jObj = jobj;
    }

    public String getParamFromData(String name){
        if (jObj == null)
            return null;

        try {
            if (jObj.has("data")){
                JSONObject jData = jObj.getJSONObject("data");

                if (jData.has(name))
                    return jData.getString(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
