package 面试机试题.成都中科大旗;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;

/**
 * @Author: 轩轩
 * @Date: 2021/2/23 22:27
 * @description: 本地json 读取
 */
public class 本地json读取 {
    public static void main(String[] args) {
        String s = readJsonFile("D:\\Java\\IdeaProjects\\java-Study\\src\\main\\java\\面试机试题\\成都中科大旗\\xuan_json_text.json");
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);

    }

    //读取本地json utf-8格式
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
