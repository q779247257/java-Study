package read_excel;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @Author: 轩轩
 * @Date: 2020/3/20 14:15
 * @description:
 */
public class readXlsx_Test {
    FileInputStream inputStream;
    XSSFWorkbook workbook;
    @Before
    public void before() throws IOException {
        //创建输入流
        inputStream = new FileInputStream("D:\\Java\\IdeaProjects\\java-Study\\src\\main\\java\\框架\\POI\\src\\main\\resources\\xuanxuan_02.xlsx");
        //由输入流得到工作簿对象
        workbook = new XSSFWorkbook(inputStream);
    }

    //读取XLSX 文件
    @Test
    public void readXls() throws Exception {

        //得到工作表
        //workbook.getSheet("sheet1");//根据名字获得工作表
        XSSFSheet sheetAt = workbook.getSheetAt(0);//根据索引获得工作表
//获得第1行
        XSSFRow row = sheetAt.getRow(0);
        //获得第一行的第一列
        XSSFCell cell = row.getCell(0);
        //打印第一行第一列的内容
        System.out.println(cell.getStringCellValue());
    }

    //读取所有的
    @Test
    public void readALlValue() throws Exception {
        XSSFSheet sheet = workbook.getSheet("Sheet1");//获取工作表
        sheet.forEach(row -> {//循环获取行
            row.forEach(cell -> {//循环获取列
                //获取指定单元格内容
                String stringCellValue = cell.getStringCellValue();
                System.out.println("info:"+stringCellValue);
            });
        });
//        sheet.iterator().forEachRemaining(row -> {
//            row.forEach(cell -> {
//                System.out.println("info:"+cell.getStringCellValue());
//            });
//        });

    }

    @After
    public void after() throws Exception {
        inputStream.close();//关闭输入流
        workbook.close();//关闭工作簿对象
    }
}
