package com.xuan.uitls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

/**
 * @Author: 轩轩
 * @Date: 2020/3/23 10:47
 * @description: Excel工具类
 */
public class ExcelUtils {

    /**
     * 读取Excel表
     * @param fileName 文件名称
     * @param inputStream 文件输入流
     * @return List  （map《k：标题 ， V ： 内容》）
     */
    public static List<Map<String,Object>> readExcel(String fileName, InputStream inputStream) throws Exception {
        boolean ret = isXlS(fileName);
        // 定义工作簿接口变量
        Workbook workbook = null;
        if(ret == true){
            workbook = new HSSFWorkbook(inputStream);
        }else{
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        //读取第一行（表头表体）
        Row titleRow = sheet.getRow(0);
        //获取单元格的个数
        short lastCellNum = titleRow.getLastCellNum();

        //存储标题内容
        ArrayList<String> titleList = new ArrayList<>();
        //遍历标题
        titleRow.forEach( cell -> {
            cell.setCellType(CellType.STRING);//设置单元格内容为字符串
            //获取单元格内容
            String stringCellValue = cell.getStringCellValue();
            //保存标题数据
            titleList.add(stringCellValue);
        });

        //获取工作表的总行数
        int lastRowNum = sheet.getLastRowNum();
        //从第二行开始遍历
        List<Map<String,Object>> list = new ArrayList<>();
        int j = 1;
        for (int i=1 ; i<=lastRowNum ; i++){
            //获取行
            Row row = sheet.getRow(i);

            HashMap<String, Object> map = new HashMap<>();
            //遍历行中的内容
            row.forEach(LambdaUtils.consumerWithIndex((item, index) -> {
                item.setCellType(CellType.STRING);//设置单元格为String
                String stringCellValue = item.getStringCellValue();//获取单元格内容
                //添加到Map中
                map.put(titleList.get(index),stringCellValue);
            }));
            //将每行数据放到List中
            list.add(map);
        }
        workbook.close();
        return list;
    }

    public static List<Map<String, Object>> readExcel02(String fileName, InputStream inputStream) throws Exception {
        boolean ret = isXlS(fileName);
        // 定义工作簿接口变量
        Workbook workbook = null;
        if(ret == true){
            workbook = new HSSFWorkbook(inputStream);
        }else{
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        // 读取标题行
        Row titleRow = sheet.getRow(0);
        List<String> titleList = new ArrayList<>();
        // 获取单元格的个数
        short lastCellNum = titleRow.getLastCellNum();
        for(int i = 0; i < lastCellNum; i++){
            Cell cell = titleRow.getCell(i);
            // 设置单元格类型
            cell.setCellType(CellType.STRING);
            // 保存标题数据
            titleList.add(cell.getStringCellValue());
        }
        List<Map<String, Object>> list = new ArrayList<>();
        // 获取总行数
        int lastRowNum = sheet.getLastRowNum();
        for(int i = 1; i <= lastRowNum; i++){
            Row row = sheet.getRow(i);
            Map<String, Object> map = new HashMap<>();
            for(int j = 0; j < lastCellNum; j++){
                Cell cell = row.getCell(j);
                if(cell == null){
                    continue;
                }
                cell.setCellType(CellType.STRING);
                // 将单元格中数据放到map对象中
                map.put(titleList.get(j), cell.getStringCellValue());
            }
            // 将每行数据放到list中
            list.add(map);
        }

        workbook.close();
        return list;
    }



    /**
     * 判断excle文件格式
     * @param fileName 文件名称
     * @return xls为true
     *          xlsx 为false
     */
    public static  boolean isXlS(String fileName){
        if(fileName.matches("^.+\\.(?i)(xls)$")){
            return true;
        }else if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            return false;
        }else{
            throw  new RuntimeException("格式错误");
        }
    }
}
