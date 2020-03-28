package com.xuan.controller;

import com.alibaba.fastjson.JSON;
import com.xuan.entity.Staff;
import com.xuan.service.StaffService;
import com.xuan.uitls.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: 轩轩
 * @Date: 2020/3/20 16:10
 * @description: 此类用于文件类操作
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private StaffService staffService;


    //上传文件导入数据库
    @RequestMapping("/import")
    @ResponseBody
    public ModelAndView impotExcel(@RequestParam("excelFile") MultipartFile excelFile) throws Exception {
        String fileName = excelFile.getOriginalFilename();//获取上传的文件名
        InputStream inputStream = excelFile.getInputStream();//获取文件的输入流
        //读取Excek表的内容
      List<Map<String, Object>> maps = ExcelUtils.readExcel(fileName, inputStream);

        String jsonString = JSON.toJSONString(maps);//转换字符串

        List<Staff> syaffs = JSON.parseArray(jsonString,Staff.class);//转换为批量插入所需的数据
//        syaffs.stream().forEach(LambdaUtils.consumerWithIndex((item , index) -> { System.out.println("info:"+item); }));
        //批量插入
        staffService.addEmps(syaffs);
        return new ModelAndView("list");
    }
}
