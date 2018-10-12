package com.zhangjie.bigdata.parser;

import com.zhangjie.bigdata.vo.Page;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;
import java.util.List;


/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.parser
 * @ description: 列表页解析
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class BDTBListParser {
    private  static HtmlCleaner htmlCleaner=new HtmlCleaner();
    public Page parserList(Page page) {
        try {
        String content = page.getContent();
        TagNode root = htmlCleaner.clean(content);
        Object[] objects = root.evaluateXPath("//a[@class='j_th_tit']");
        List<String> urlList=new ArrayList<String>();
        for (Object obj : objects){
            TagNode tagNode=(TagNode) obj;
            urlList.add(tagNode.getAttributeByName("href"));
        }
        page.setUrlList(urlList);
            return page;

        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isNextPage(Page page) {
        try {
        String content = page.getContent();
        TagNode root = htmlCleaner.clean(content);
        Object[] objects = root.evaluateXPath("//a[@class='next pagination-item']");
            if (objects != null && objects.length >0){
                System.out.println("====有下一页===");
                return true;
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取下一页  返回一个page
     * 
     * @param page  对象
     *              
     * @return
     */

    public Page getNextPage(Page page) {
        try {

            String url = page.getUrl();
            int lastIndexOf = url.lastIndexOf("=");
            String pnValue = String.valueOf(Integer.valueOf(url.substring(lastIndexOf + 1)) + 50);
            String afterUrl = url.substring(0, lastIndexOf + 1);
            page.setUrl(afterUrl + pnValue);
            return page;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

public Page parseItemages(Page page) {
    try {
        String content = page.getContent();
        TagNode tagNode = htmlCleaner.clean(content);
        Object[] objects = tagNode.evaluateXPath("//img[@class='BDE_Image']");
        List<String> imgs = new ArrayList<String>();
        for (Object obj : objects) {
            TagNode tagNodeImg = (TagNode) obj;
            String imPath = tagNodeImg.getAttributeByName("src");
            imgs.add(imPath);
        }
        page.setImages(imgs);
        return page;
    } catch (XPatherException e) {
        e.printStackTrace();
    }
     return null;
}
}