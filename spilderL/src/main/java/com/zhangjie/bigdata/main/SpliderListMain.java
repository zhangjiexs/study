package com.zhangjie.bigdata.main;

import com.zhangjie.bigdata.dowload.BDTBListDownLoad;
import com.zhangjie.bigdata.parser.BDTBListParser;
import com.zhangjie.bigdata.util.JedisUtil;
import com.zhangjie.bigdata.vo.Page;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.vo
 * @ description: 列表爬取
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class SpliderListMain {
    /**
     * 爬取百度贴吧美女吧的帖子列表页
     *①创建初始对象
     *②下载页面
     *③解析页面  创建解析器
     *④把解析后的结果储存到rides数据库
     * @param args
     */

//创建初始对象
    private  static Page page=new Page();
    // 创建解析器
    private static BDTBListParser bdtbListParser=new BDTBListParser();
    //存到rides数据库
    private static Jedis jedis= JedisUtil.getJedis();
    public static void main(String[] args) throws InterruptedException {
        BDTBListDownLoad downLoad=new BDTBListDownLoad();
        page.setUrl("https://tieba.baidu.com/f?kw=美女&pn=0");//连接url
        //下载页面
        page=downLoad.downLoad(page);
        page=bdtbListParser.parserList(page);
        saveUrlListToRedis(page.getUrlList());
        System.out.println("=========第一页储存到Redis成功==========");
        while (bdtbListParser.isNextPage(page)){
         page=bdtbListParser.getNextPage(SpliderListMain.page);
         downLoad.downLoad(page);
         page=bdtbListParser.parserList(page);
         saveUrlListToRedis(page.getUrlList());
            System.out.println("=下一页="+page.getUrl()+"=====储存到Redis成功=====");
            Thread.sleep(3000);//设置刷新时间
        }
    }
    //把解析后的结果储存到rides数据库
    private static void  saveUrlListToRedis(List<String> urllist){
        jedis.select(1);
        for (String url : urllist){
            jedis.lpush("urllist",url);
        }
    }
}
