package com.xuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: 轩轩
 * @Date: 2020/3/20 16:10
 * @description:
 */

@Controller
@RequestMapping("/file")
public class FileController {


    @RequestMapping("/import")
    public void impotExcel(@RequestParam("/file") MultipartFile excelFile){
    }
}
