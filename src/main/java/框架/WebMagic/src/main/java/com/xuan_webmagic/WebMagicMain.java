package com.xuan_webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/12/10 0:21
 * @description: webmagic爬虫示例
 */
public class WebMagicMain implements PageProcessor {

    /** 爬虫的地址 */
    private Site site = Site.me().setDomain("my.oschina.net");

    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
        page.addTargetRequests(links);
        //通过putField 来保存爬取的结果


       // page.getHtml().xpath()则是按照某个规则对结果进行抽取，这里抽取支持链式调用。调用结束后，toString()表示转化为单个String，all()则转化为一个String列表。
        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
//        String url = "https://www.baidu.com/";
        String url = "http://my.oschina.net/flashsword/blog";
        Spider.create(new WebMagicMain ()).addUrl(url)
                .addPipeline(new ConsolePipeline()).run();
    }


}
