package com.example.admin.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/3/17.
 */
public class MyWebViewActivity extends Activity{

    private WebView web_myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        init();

        try {
            String json = "{\"isApp\":\"1\",\"isWK\":\"0\",\"jumpType\":\"4\"}";
            JSONObject jsonObject = new JSONObject(json);
            Log.i("e","======"+jsonObject.toString());
            jsonObject.putOpt("user_id", "111");
            Log.i("e", "======" + jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ProgressDialog dialog;
    private void initProgressbar(){
        dialog = new ProgressDialog(MyWebViewActivity.this);
        dialog.setMessage("加载中...");
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND
                        | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

    private void init() {
        initProgressbar();

        web_myview = (WebView) findViewById(R.id.web_test);
        WebSettings webset = web_myview.getSettings();
        webset.setJavaScriptEnabled(true);
        webset.setSupportZoom(true);
        web_myview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    if(null !=dialog && dialog.isShowing())
                        dialog.dismiss();
                }
            }
        });

        web_myview.setDownloadListener(new MyWebViewDownLoadListener());

        web_myview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受证书
            }
        });
        webset.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webset.setCacheMode(WebSettings.LOAD_DEFAULT); // 设置缓存模式
        // 开启 DOM storage API 功能
        webset.setDomStorageEnabled(true);
        // 开启 database storage API 功能
        webset.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath();
        // 设置数据库缓存路径
        webset.setDatabasePath(cacheDirPath);
        // 设置 Application Caches 缓存目录
        webset.setAppCachePath(cacheDirPath);
        // 开启 Application Caches 功能
//        webset.setAppCacheEnabled(true);
//        webset.setUseWideViewPort(true);
//        webset.setLoadWithOverviewMode(true);


        initData();
    }

    private void initData(){
//        String url = "http://zqmfcdn.huanhuba.com/app_static/mofang/share/index.html?match_id=2024865&partner=qmcai#/home/index";
//        String url = "https://partner.huanhuba.com/qmcai/index?id=201607214004";
//        String url = "http://172.16.1.111:60/";
        String url = "http://www.huanhuba.com/down/app/index.html";
        web_myview.loadUrl(url);
        dialog.setMessage("加载中...");
        dialog.show();
        web_myview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public class MyWebviewCient extends WebViewClient{
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            WebResourceResponse response = null;
            response = super.shouldInterceptRequest(view, url);
            return response;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            Log.d("info", "***on page finished");

//            web_myview.loadUrl("javascript:startPlay()");
//            web_myview.loadUrl("javascript:document.querySelector(\".mpptv_ads\").style.display = \"none\";");
//            javascript="javascript:setMeParam('"+userid+"','"+forumId+"')";

//            String urserid ="290270";
//            web_myview.loadUrl("javascript:setMeParam('"+urserid+"','"+urserid+"')");
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            web_myview.getClass().getMethod("onResume").invoke(web_myview,(Object[])null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            web_myview.getClass().getMethod("onPause").invoke(web_myview, (Object[]) null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

