package com.zhangjie.bigdata.vo;

import java.util.List;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.vo
 * @ description: 页面实体类
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class Page {
    private  String url;//网址
    private List<String> urlList;//页面列表

    private String content;//页面内容

    public List<String>getImages(){
        return images;
    }

    public void setImages(List<String> images){this.images=images;}
    public List<String> images;//图片路径

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
