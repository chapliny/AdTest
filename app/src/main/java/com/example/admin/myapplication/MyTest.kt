package com.example.admin.myapplication


import java.util.ArrayList
import java.util.Collections
import java.util.HashSet

/**
 * Created by admin on 2016/9/9.
 */
object MyTest {

    @JvmStatic fun main(arg: Array<String>) {
        //        try {
        //            String json = "{\\\"isApp\\\":\\\"1\\\",\\\"isWK\\\":\\\"0\\\",\\\"jumpType\\\":\\\"4\\\"}";
        //            String jsonMessage = "{\"语文\":\"88\",\"数学\":\"78\",\"计算机\":\"99\"}";
        //            JSONObject jsonObject = new JSONObject(jsonMessage);
        //            System.out.print("======"+jsonObject.toString());
        //            jsonObject.putOpt("user_id", "111");
        //            System.out.print("======"+jsonObject.toString());
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

        //        List<String> strs = new ArrayList<>();
        //        strs.add("1111111");
        //        strs.add("2222222");
        //        strs.add("3333333");
        //        strs.add("4444444");
        //        for(int i=0;i<strs.size();i++){
        //            System.out.println("====i="+i+":"+strs.get(i));
        //        }
        //        System.out.println("==========================");
        //        strs.set(2,"aaaaaaa");
        //        for(int i=0;i<strs.size();i++){
        //            System.out.println("====i="+i+":"+strs.get(i));
        //        }


        val list = ArrayList<String>()
        list.add("b")
        list.add("c")
        list.add("d")
        list.add("b")
        list.add("c")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("e")
        list.add("e")
        list.add("e")

        println("\n例子1 - 计算'a'出现的次数")
        println("a : " + Collections.frequency(list, "a"))

        for (i in list.indices) {
            println(list.get(i) + ":=== " + Collections.frequency(list, list.get(i)))
        }


        //为了去重复的
        println("\n例子2 - 计算所有对象出现的次数")
        val uniqueSet = HashSet(list)
        for (temp in uniqueSet) {
            println(temp + ": " + Collections.frequency(list, temp))

        }


    }


}
