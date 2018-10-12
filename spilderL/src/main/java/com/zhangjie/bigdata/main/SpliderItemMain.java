package com.zhangjie.bigdata.main;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zhangjie.bigdata.dowload.BDTBListDownLoad;
import com.zhangjie.bigdata.parser.BDTBListParser;
import com.zhangjie.bigdata.schedule.UrlSchedule;
import com.zhangjie.bigdata.util.DataSourceUtil;
import com.zhangjie.bigdata.vo.Page;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.vo
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class SpliderItemMain {
    private static Page page=new Page();
    private static ComboPooledDataSource dataSource= DataSourceUtil.getDataSource();
    private static QueryRunner queryRunner=new QueryRunner(dataSource);

    public static void main(String[] args) throws Exception {
        while (true){
            getItemages(page);
            Thread.sleep(200);
        }
    }

    private static void getItemages(Page page) throws Exception {
        //执行调度
        page= UrlSchedule.getListUrlFromRedis(page);
        if (page == null){
            System.out.println("====列表页已经爬完=====");
            return;
        }
        BDTBListDownLoad downLoad=new BDTBListDownLoad();
        BDTBListParser bdtbListParser=new BDTBListParser();
        //下载页面
        page=downLoad.downLoad(page);
        //解析页面
        page= bdtbListParser.parseItemages(page);
         String sql="insert into images(path) values (?)";
         for (String path : page.getImages()){
             System.out.println(path);
             queryRunner.execute(sql, path);
             //储存到文件
             String name=path.substring(path.lastIndexOf("/"));
             FileUtils.copyURLToFile(new URL(path),new File("C:/private-love" + name));

         }

    }
}
