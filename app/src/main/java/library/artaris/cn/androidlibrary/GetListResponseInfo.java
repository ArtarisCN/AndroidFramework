package library.artaris.cn.androidlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;

import library.artaris.cn.library.http.HttpResponseInfo;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class GetListResponseInfo extends HttpResponseInfo {
    public Info getDate() {
        return date;
    }

    public void setDate(Info date) {
        this.date = date;
    }
    private Info date;
}
