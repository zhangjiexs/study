package com.zhangjie.bigdata.Get_meinv;
import  java.io.*;
import  java.net.*;
import  java.util.regex.*;

/**
 * @ Title: spilderM
 * @ Package:com.zhangjie.bigdata.Get_meinv
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class GetMeiNv {
    public static void main(String[] args) throws Exception {
        //1.创建连接
        URL url=new URL("https://tieba.baidu.com/p/2589161303?red_tag=1874186153");
        //2.打开网页
        URLConnection urlc=url.openConnection();
        //3.处理网络超时问题
        urlc.setConnectTimeout(1000*10);
        //4.获取网页所有输入
        BufferedReader buffered=new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        //5.正则来表示邮箱的格式
        String line=null;
        String reg="[a-zA-Z0-9_-]+@\\W+\\.[a-z]+(\\.[a-z]+)?";
        //6.编译
        Pattern p=Pattern.compile(reg);
        //7.匹配
        while ((line=buffered.readLine())!=null){
            Matcher m=p.matcher(line);
            //8.截取打印
            while (m.find()){
                System.out.println(m.group());
            }

        }
    }
}
