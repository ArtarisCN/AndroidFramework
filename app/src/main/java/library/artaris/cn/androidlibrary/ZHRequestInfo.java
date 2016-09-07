package library.artaris.cn.androidlibrary;

import java.util.Map;

import library.artaris.cn.library.constant.UrlConstant;
import library.artaris.cn.library.http.BaseRequestInfo;
import library.artaris.cn.library.http.HttpResponseInfo;

/**
 * Created by Rick
 * on 16/9/7.
 */
public class ZHRequestInfo extends BaseRequestInfo {
    private static String HOST = Http.HttpHost;

    public ZHRequestInfo(int methon){
        super(methon);
    }

    @Override
    public Map<String, Object> getPostParamsMap() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }
}

class GetLastInfo extends ZHRequestInfo{

    public GetLastInfo(){
        super(GET);
    }

    @Override
    public String getPath() {
        return "sections";
    }

    @Override
    public int getMethod() {
        return GET;
    }
}