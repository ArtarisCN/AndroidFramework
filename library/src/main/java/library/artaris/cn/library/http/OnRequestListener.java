package library.artaris.cn.library.http;

/**
 * Created by Rick
 * on 16/9/7.
 */
public interface OnRequestListener<T extends HttpResponseInfo>{
    public void onResponse(int requestCode, T requestInfo, BaseHttpError error);
}
