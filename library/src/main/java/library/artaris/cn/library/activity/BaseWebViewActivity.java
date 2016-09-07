package library.artaris.cn.library.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Rick on 16/7/27.
 */
public class BaseWebViewActivity extends BaseActivity {
    public static final String WEB_URL = "url";
    public static final String WEB_Title = "title";

    private WebView mWebView;
    private int mWebViewContentID;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.getSettings().setJavaScriptEnabled(true);

        if(mWebViewContentID != 0)
            mWebView = (WebView)findViewById(mWebViewContentID);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mWebView.canGoBack()){
                mWebView.goBack();
            }
            else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void startWebViewActivty(Activity context, String url, String title){
        Intent webIntent = new Intent(context, BaseWebViewActivity.class);
        webIntent.putExtra(BaseWebViewActivity.WEB_URL, url);
        webIntent.putExtra(BaseWebViewActivity.WEB_Title, title);
        context.startActivity(webIntent);
    }

    protected int getWebViewContentID(){
        return mWebViewContentID;
    }

    protected void setWebViewContentID(int webViewContentID){
        if(webViewContentID != 0)
            mWebViewContentID = webViewContentID;

    }


    //=======================WebView Client Start=========================================
    private WebChromeClient mWebChromeClient = new WebChromeClient(){
        public void onProgressChanged(WebView view, int progress)
        {

        }
    };

    private WebViewClient mWebViewClient = new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    };

    //=======================WebView Client Finish=========================================

    //=======================WebView Function Start=========================================

    public String getThisUrl(){
        return mWebView.getUrl();
    }

    public void reloadThisWenView(){
        mWebView.reload();
    }

    public void clearWebViewCache(boolean includeDiskFiles){
        mWebView.clearCache(includeDiskFiles);
    }


    //=======================WebView Function Finish=========================================


}
