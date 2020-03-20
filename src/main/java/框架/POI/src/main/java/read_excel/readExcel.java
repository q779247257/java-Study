package read_excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: 轩轩
 * @Date: 2020/3/20 14:58
 * @description:
 */
public class readExcel {
    public static void main(String[] args) throws Exception {
        readExcel readExcel = new readExcel();

        Runnable r = () ->{
            try {
                readExcel.readExcelValue("D:\\Java\\IdeaProjects\\java-Study\\src\\main\\java\\框架\\POI\\src\\main\\resources\\xuanxuan_02.xlsx");
            } catch (IOException e) {
            }
        };
         new Thread(r,"xuanxuan").start();

        readExcel.readExcelValue("D:\\Java\\IdeaProjects\\java-Study\\src\\main\\java\\框架\\POI\\src\\main\\resources\\xuanxuan.xls");
    }

    /**
     * 读取2中后缀的excel文件
     * @param fileName
     */
    public void readExcelValue(String fileName) throws IOException {
        //拿到文件的后缀
        String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
        //fileName.substring(fileName.lastIndexOf(".") + 1)
        FileInputStream inputStream = new FileInputStream(fileName);
        boolean ret = isXlS(fileName);
        // 定义工作簿接口变量
        Workbook workbook = null;
        if(ret == true){
            System.out.println("xls文件开始读取");
            workbook = new HSSFWorkbook(inputStream);
        }else{
            System.out.println("xlsx文件开始读取");
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);//获取sheet工作表
        sheet.forEach(row ->{
            row.forEach(cell -> {
                System.out.println(substring+"单元格内容:"+cell.getStringCellValue());
            });
        });

        //关闭
        workbook.close();
        inputStream.close();
    }

    /**
     * 判断文件啊是否为XLS格式
     * @param fileName 文件名
     * @return xls为true  非xls为false
     */
    public boolean isXlS(String fileName){
        if(fileName.matches("^.+\\.(?i)(xls)$")){
            return true;
        }else if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            return false;
        }else{
            throw  new RuntimeException("格式错误");
        }
    }
}
