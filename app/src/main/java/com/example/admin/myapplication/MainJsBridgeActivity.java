package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

public class MainJsBridgeActivity extends Activity implements View.OnClickListener {

    private final String TAG = "MYJS_INFO";

    private Button button;
    private BridgeWebView webView;

    int RESULT_CODE = 0;
    ValueCallback<Uri> mUploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsbridge_webview);

        webView = (BridgeWebView) findViewById(R.id.webView);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
        webView.setWebViewClient(new MyWebViewClient(webView));
        webView.setDefaultHandler(new myHadlerCallBack());
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.i(TAG, "=========onJsAlert=========message=" + message
                        + "\n url =" + url);
                return super.onJsAlert(view, url, message, result);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                pickFile();
            }
        });



//        String url = "http://172.16.1.111:60/";
        String url = "http://172.16.1.137:8077/test/index.html";
//        webView.loadUrl("file:///android_asset/demo.html");
        webView.loadUrl(url);

        webView.registerHandler("toPayView", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "11111111111111111111handler = submitFromWeb, data from web = " + data);

                function.onCallBack("{\"status\":1}");


            }
        });




//        webView.callHandler("functionInJs", "{\"userid\":\"2071\"}", new CallBackFunction() {
//            @Override
//            public void onCallBack(String data) {
//
//            }
//        });

        webView.callHandler("toPayView", "{\"userId\":1}", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });


        /**
         * 事件名称：getUserInfo
         事件描述：获取用户信息
         */
        webView.registerHandler("getUserInfo", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i("info", "===getUserInfo 111==" + data);

                String userId = "";
                String mf_token = "";
                String jToken = "{\"token\":\""+mf_token+"\",\"user_id\":\""+userId+"\"}";
                Log.i("info","=======loginApp==jToken="+jToken);
                function.onCallBack(jToken);

            }
        });

//        webView.send("hello");
    }

    /**
     * 自定义的WebViewClient
     */
    class MyWebViewClient extends BridgeWebViewClient {

        public MyWebViewClient(BridgeWebView webView) {
            super(webView);
        }
    }


    /**
     * 自定义回调
     */
    class myHadlerCallBack extends DefaultHandler {

        @Override
        public void handler(String data, CallBackFunction function) {
            if(function != null){

                Toast.makeText(MainJsBridgeActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private CallBackFunction myfunction;
    private Handler myTestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i(TAG, "11111111111111111111handleMessage 执行了 啊啊啊啊= ");
            myfunction.onCallBack("{\"status\":1}");

        }
    };

    public void pickFile() {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        startActivityForResult(chooserIntent, RESULT_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_CODE) {
            if (null == mUploadMessage){
                return;
            }
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (button.equals(v)) {
            webView.callHandler("functionInJs", "data from Java", new CallBackFunction() {

                @Override
                public void onCallBack(String data) {
                    // TODO Auto-generated method stub
                    Log.i(TAG, "reponse data from js " + data);
                }

            });
        }

    }



}
