官方网站 http://webmagic.io/

webmagic是一个开源的Java垂直爬虫框架，目标简化爬虫的开发流程，让开发者专注于逻辑功能的开发，webmagic的核心非常简单，但是覆盖爬虫的整个流程，也是很好的学习爬虫开发的资料。

**webmagic的主要特色**

- 完全模块化的设计，强大的可扩展性
- 核心简单但是涵盖爬虫的全部流程，灵活而强大，也是学习爬虫入门的好材料
- 提供丰富的抽取页面API。
- 无配置，但是可通过POJO+注解的形式实现一个爬虫
- 支持多线程
- 支持分布式
- 支持爬取JS动态渲染的页面
- 无框架依赖，可以灵活的浅入到项目中去；



webmagic的架构和设计参考了以下两个项目，感谢以下两个项目的作者：

python爬虫 **scrapy** https://github.com/scrapy/scrapy

Java爬虫 **Spiderman** http://git.oschina.net/l-weiwei/spiderman

webmagic的github地址：https://github.com/code4craft/webmagic。

​	

### 快速开始

#### 使用MAVEN

```xml
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-core</artifactId>
    <version>0.7.3</version>
</dependency>
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-extension</artifactId>
    <version>0.7.3</version>
</dependency>
```

WebMagic 使用slf4j-log4j12作为slf4j的实现.如果你自己定制了slf4j的实现，请在项目中去掉此依赖。

```xml
<exclusions>
    <exclusion>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
    </exclusion>
</exclusions>
```

### 项目架构

webmagic主要包括2个包 

- **webmagic-core**

  webmagic核心部分，只包含爬虫基本模块和基本抽取器。webmagic-core的目标是成为网页爬虫的一个教科书般的实现。

- **webmagic-extension**

  webmagic的扩展模块，提供一些更方便的编写爬虫的工具。包括注解格式定义爬虫、JSON、分布式等支持。

  webmagic还包含两个可用的扩展包，因为这两个包都依赖了比较重量级的工具，所以从主要包中抽离出来，这些包需要下载源码后自己编译：：

  - **webmagic-saxon**

    webmagic与Saxon结合的模块。Saxon是一个XPath、XSLT的解析工具，webmagic依赖Saxon来进行XPath2.0语法解析支持。

  - **webmagic-selenium**

    webmagic与Selenium结合的模块。Selenium是一个模拟浏览器进行页面渲染的工具，webmagic依赖Selenium进行动态页面的抓取。



在项目中，你可以根据需要依赖不同的包。

###  第一个爬虫

PageProcessor是webmagic-core的一部分，定制一个PageProcessor即可实现自己的爬虫逻辑。以下是抓取osc博客的一段代码：