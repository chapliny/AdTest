package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kotlin.Main2Activity;
import com.litho.MyDemoLithoActivity;
import com.pairscrollview.MyWebViewActivity2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/17.
 */
public class MyDemoListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo_list);
        init();

    }

    RecyclerView rc_demo;
    MyDemoAdatper myDemoAdatper;

    private void init() {
        rc_demo = (RecyclerView) findViewById(R.id.rc_demo);
        initData();
        myDemoAdatper = new MyDemoAdatper();
        rc_demo.setAdapter(myDemoAdatper);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.set
        rc_demo.setLayoutManager(linearLayoutManager);
        String info = "div class=\\\"m-edit\\\"> <p class=\\\"m-title\\\">5-10日test帖子<\\/p><div class=\\\"table\\\">\\n                        <div class=\\\"row\\\">\\n                            <div class=\\\"cell\\\">\\n                                <img src=\\\"http:\\/\\/zqmfcdn.huanhuba.com\\/ay_static\\/images\\/avatars\\/c796145c1497fc182dc051b6556f47ee.jpg?x-oss-process=image\\/resize,m_lfit,w_128,h_128\\\" class=\\\"peopleImg\\\" data-uid=\\\"50827\\\" \\/>\\n                            <\\/div>\\n                            <div class=\\\"cell\\\">\\n                                <p class=\\\"name\\\">B罗 <\\/p>\\n                            <\\/div>\\n                            <div class=\\\"cell\\\">\\n                                <p class=\\\"kuang\\\">B罗<\\/p>\\n                            <\\/div>\\n                            <div class=\\\"cell\\\">\\n                                <span class=\\\"time\\\">6天前<\\/span>\\n                            <\\/div>\\n                        <\\/div>\\n                <\\/div>\\n               \\n                \\n                    <\\/p><p style=\\\"font-size:12px;color:#333333;line-height: 2em;padding-bottom: 10px;\\\">*您现在可以点击左上角【本站名称】进入本站，选择【立即加入】，接收本站最新资讯。<\\/p><div class=\\\"content_images\\\">\\n                <img  src=\\\"http:\\/\\/sapp.huanhuba.com\\/images\\/default3.gif\\\" data-src=\\\"http:\\/\\/sapp.huanhuba.com\\/\\/images\\/championship\\/upload\\/4e102399f4b81ef18575e8af5e83841a85998ea5.png\\\" class=\\\"dataBig\\\"class=\\\"dataBig\\\" ><p>\\r\\n\\t5-10日test帖子5-10日test帖子\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t5-10日test帖子\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t5-10日test帖子\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t5-10日test帖子\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t5-10日test帖子5-10日test帖子\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t<br \\/>\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t<br \\/>\\r\\n<\\/p>\\r\\n<div class=\\\"ke-video\\\">\\r\\n\\t<span class=\\\"playControl\\\"><\\/span><video src=\\\"http:\\/\\/zqmfcdn.huanhuba.com\\/video\\/zqmf\\/20170510\\/2ebce9e9aff7e756d053789ff3bcf06ebe2542e7.mp4\\\" width=\\\"100%\\\" height=\\\"auto\\\" preload=\\\"auto\\\" poster=\\\"http:\\/\\/img03.tooopen.com\\/images\\/20131111\\/sy_46708898917.jpg\\\" dataurl=\\\"http:\\/\\/zqmfcdn.huanhuba.com\\/video\\/zqmf\\/20170510\\/2ebce9e9aff7e756d053789ff3bcf06ebe2542e7.mp4\\\"><\\/video>\\r\\n\\t<p class=\\\"times\\\">\\r\\n\\t\\t-分-秒\\r\\n\\t<\\/p>\\r\\n\\t<p class=\\\"playTit f-toe\\\">\\r\\n\\t\\t郭德纲相声\\r\\n\\t<\\/p>\\r\\n<\\/div>\\r\\n<p>\\r\\n\\t<br \\/>\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t<br \\/>\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t<br \\/>\\r\\n<\\/p>\\r\\n<div class=\\\"ke-audio\\\">\\r\\n\\t<p class=\\\"playTitle f-toe\\\">\\r\\n\\t\\t特效音频\\r\\n\\t<\\/p>\\r\\n<audio src=\\\"http:\\/\\/zqmfcdn.huanhuba.com\\/video\\/zqmf\\/20170508\\/1c1be333454b17e08b0ed978e6b2596994579644.mp3\\\" width=\\\"100%\\\" height=\\\"auto\\\" preload=\\\"auto\\\" dataurl=\\\"http:\\/\\/zqmfcdn.huanhuba.com\\/video\\/zqmf\\/20170508\\/1c1be333454b17e08b0ed978e6b2596994579644.mp3\\\"><\\/audio>\\r\\n\\t<div class=\\\"audioControl\\\">\\r\\n\\t\\t<span class=\\\"playTime\\\">0:00<\\/span><span class=\\\"playBtn\\\"><\\/span><span class=\\\"progress\\\"><span class=\\\"line\\\"><\\/span><span class=\\\"hasPlay\\\"><\\/span><span class=\\\"handle\\\"><\\/span><\\/span> \\r\\n\\t<\\/div>\\r\\n<\\/div>\\r\\n<br \\/>\\r\\n<p style=\\\"font-size:14px;color:#9099B4;line-height:14px;margin:0;text-align:center;\\\">\\r\\n\\t内容来源：足球魔方cubegoal\\r\\n<\\/p>\\r\\n<p>\\r\\n\\t<br \\/>\\r\\n<\\/p>\\n            <\\/div>\\n            <p class=\\\"m-list\\\" style=\\\"line-height: 2.4em;overflow: hidden;\\\"><p class=\\\"boinfo\\\">＊所有言论仅代表发布者个人意见，与本平台无关，请看官自行核查内容<\\/p>\\n        <\\/div>";

        String info2 ="<video src=\"http://zqmfcdn.huanhuba.com/video/zqmf/20170510/2ebce9e9aff7e756d053789ff3bcf06ebe2542e7.mp4\" width=\"100%\" height=\"auto\" preload=\"auto\" poster=\"http://img03.tooopen.com/images/20131111/sy_46708898917.jpg\" dataurl=\"http://zqmfcdn.huanhuba.com/video/zqmf/20170510/2ebce9e9aff7e756d053789ff3bcf06ebe2542e7.mp4\"></video>";
        String src_msg ="src=\"";
        int i_src_s= info2.indexOf(src_msg);
        int i_src_e = info2.indexOf("\"",i_src_s+src_msg.length());

        String src_url = info2.substring(i_src_s+src_msg.length(),i_src_e);

        String postter_msg = "poster=\"";
        int i_poster_s = info2.indexOf(postter_msg);
        int i_poster_e = info2.indexOf("\"",i_poster_s+postter_msg.length());
        String poster_msg = info2.substring(i_poster_s+postter_msg.length(),i_poster_e);
        Log.i("info","=====i_src_s="+i_src_s+"==i_src_e="+i_src_e+"==src_url=:::"+src_url
        +"=poster_msg="+poster_msg);

        Document document = Jsoup.parse(info2);
//        document.select("video");
//        Node node = document.parentNode();
//        String src = node.attr("src");
//        String poster = node.attr("poster");
//        Log.i("info","====src="+src+"=poster="+poster);

        //匹配固定 html tag
//        String regEx = "<(?<HtmlTag>(video|audio))[^>]*?>((?<Nested><\\k<HtmlTag>[^>]*>)|</\\k<HtmlTag>>(?<-Nested>)|.*?)*</\\k<HtmlTag>>";
        String regEx = "<video.*video>";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(info);
        String test = "";
        while(matcher.find()) {
            test =  matcher.group(0);
        }
        Log.i("info","===test="+test);

        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    /**
     * 给一个正则表达式，和数据，将正则匹配到的数据全数取出来
     *
     * @param regex
     * @param data
     * @return List<String>
     */
    public static List<String> getRegexData(String regex,String data){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(data);
        List<String> resultList=new ArrayList<String>();
        int index=0;//搜索的位置
        String temp="";
        /* 从指定位置查找，如果找到了，就继续执行下面的代码 */
        while(matcher.find(index)){
            temp=matcher.group();//将匹配到的数据取出来放到集合中去
            resultList.add(temp);
            index+=temp.length();//将查找位置放到此时找到的数据后面
            System.out.println(index);
        }
        return resultList;
    }


    private List<String> mDatas;
    protected void initData(){
        mDatas = new ArrayList<String>();


        mDatas.add("6.0+ 圖片选择 返回问题");
        mDatas.add("Kotlin Demo");
        mDatas.add("Lottie测试");
        mDatas.add("Test22222222");
        mDatas.add("Test33333");

    }

    private class MyDemoAdatper extends RecyclerView.Adapter<MyDemoAdatper.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MyDemoListActivity.this).inflate(R.layout.demo_list_item, parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,final int position) {
            holder.tv.setText(mDatas.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 0:
                            MyDemoListActivity.this.startActivity(new Intent(MyDemoListActivity.this,MainActivity.class));
                            break;
                        case 1:
                            MyDemoListActivity.this.startActivity(new Intent(MyDemoListActivity.this,Main2Activity.class));
                            break;
                        case 2:
                            MyDemoListActivity.this.startActivity(new Intent(MyDemoListActivity.this,LottieTestActivity.class));
                            break;
                        case 3:
                            MyDemoListActivity.this.startActivity(new Intent(MyDemoListActivity.this,MyDemoLithoActivity.class));
                            break;
                        case 4:
                            MyDemoListActivity.this.startActivity(new Intent(MyDemoListActivity.this,MyWebViewActivity2.class));
                            break;
                    }
                }
            });






        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv_msg);
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}

