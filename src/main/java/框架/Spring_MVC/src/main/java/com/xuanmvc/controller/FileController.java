package com.xuanmvc.controller;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/3/2 10:57
 * @description:
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    public String upload(String name,@RequestParam("file") MultipartFile file) throws IOException {
        //接收表单提交的数据,包含文件
        System.out.println("name = " + name);
        System.out.println("filename is : "+ file.getName());

            System.out.println("文件开始上传------------------------");
            file.transferTo(new File("D:/Java/"+file.getOriginalFilename()));
            System.out.println("文件上传结束------------------------");

        return "hellow";
    }
    @RequestMapping("/picOSS")
    public String picOSS( @RequestParam("file")MultipartFile uploadFile) throws Exception {
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com/";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
        // https://ram.console.aliyun.com 创建
        String accessKeyId = "LTAI4FkcLtwaSxLUcy7bfY9B";
        String accessKeySecret = "KoNNamQoL5rFdKHLQhYSrg2z5w18Vb";
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //获取文件后缀
        String suffix = "."+uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
//      如果文件名一样，阿里云会直接覆盖，这里我们使用 UUID 等一些分布式id生成器 来作为文件名
        String filename = "123"+suffix;
        // 上传
        ossClient.putObject("xuanandjava", filename, new ByteArrayInputStream(uploadFile.getBytes()));
        // 关闭client
        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl("xuanandjava", filename, expiration).toString();
        System.out.println("url:"+url);
        return url;
    }



}
