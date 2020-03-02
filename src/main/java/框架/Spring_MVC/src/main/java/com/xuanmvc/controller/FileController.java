package com.xuanmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
}
