package com.zhangjie.bigdata.dowload;

import com.zhangjie.bigdata.util.HttpClientUtil;
import com.zhangjie.bigdata.vo.Page;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.dowload
 * @ description: 下载器
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class BDTBListDownLoad {
    public Page downLoad(Page page){
        String entityString = HttpClientUtil.getEntityStringByUrl(page.getUrl());
        page.setContent(entityString);
        return page;
    }
}
