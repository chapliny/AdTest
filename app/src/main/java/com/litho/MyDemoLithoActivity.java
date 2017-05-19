package com.litho;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.myapplication.LottieTestActivity;
import com.example.admin.myapplication.MainActivity;
import com.example.admin.myapplication.R;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
public class MyDemoLithoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ComponentContext c = new ComponentContext(this);

        final LithoView lithoView = LithoView.create(this ,
                Text.create(c)
                        .text("Hello, World!-LALALALLALAALLALLA")
                        .textSizeDip(50)
                        .build());



//        HeaderComponentSpec headerComponent = new HeaderComponentSpec();
//        headerComponent.create
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492678572784&di=9ed0678ac6363de01e811e3fbc1cee0a&imgtype=0&src=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201302%2F1-130201105204.jpg";
        Component component = HeaderComponent.create(c).imageUri(Uri.parse(url))
                .titleRes(R.string.action_settings)
                .subtitle("this is my subtitle.")
                .build();

        lithoView.setComponent(component);
        setContentView(lithoView);

    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}

