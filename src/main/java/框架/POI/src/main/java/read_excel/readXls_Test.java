package read_excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: 轩轩
 * @Date: 2020/3/20 14:45
 * @description:
 */
public class readXls_Test {
    FileInputStream inputStream;
    HSSFWorkbook workbook;
    @Before
    public void before() throws IOException {
        //创建输入流
        inputStream = new FileInputStream("D:\\Java\\IdeaProjects\\java-Study\\src\\main\\java\\框架\\POI\\src\\main\\resources\\xuanxuan.xls");
        //由输入流得到工作簿对象
        workbook = new HSSFWorkbook(inputStream);
    }

    //读取XLS 文件
    @Test
    public void readXls() throws Exception {

        //得到工作表
        //workbook.getSheet("sheet1");//根据名字获得工作表
        HSSFSheet sheet = workbook.getSheetAt(0);//根据索引获得工作表
        //获得第1行
        HSSFRow row = sheet.getRow(0);
        //获得第一行的第一列
        HSSFCell cell = row.getCell(0);
        //打印第一行第一列的内容
        System.out.println(cell.getStringCellValue());
    }

    //读取所有的
    @Test
    public void readALlValue() throws Exception {
        HSSFSheet sheet1 = workbook.getSheet("Sheet1");//获取工作表
        sheet1.iterator().forEachRemaining(row ->{
            row.forEach(cell -> {
                String stringCellValue = cell.getStringCellValue();
                System.out.println("info:"+stringCellValue);
            });
        });
    }

    @After
    public void after() throws Exception {
        inputStream.close();
        workbook.close();
    }
}
