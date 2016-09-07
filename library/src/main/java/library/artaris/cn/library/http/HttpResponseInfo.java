package library.artaris.cn.library.http;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class HttpResponseInfo implements Serializable {
    private int code;
    private String msg;
    private JSONObject data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
