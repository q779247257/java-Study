package read_excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: 轩轩
 * @Date: 2020/3/20 15:55
 * @description:
 */
public class WriteExcel {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建Sheet对象
        XSSFSheet sheet = workbook.createSheet();

        // 创建Row对象
        XSSFRow row = sheet.createRow(0);
        // 创建cell对象
        XSSFCell cell = row.createCell(0);
        // 设置单元格的内容
        cell.setCellValue("haha");
        // 保存excel文件 文件若存在则直接覆盖写入,如果不存在则创建文件 写入
        FileOutputStream outputStream = new FileOutputStream("D:\\Java\\IdeaProjects\\java-Study\\src\\main\\java\\框架\\POI\\src\\main\\resources\\xuanxuan.xls");
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }
}
