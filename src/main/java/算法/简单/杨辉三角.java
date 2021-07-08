package 算法.简单;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入: 5
 * 输出:
 * [
 *      [1], i=0 j=0
 *      [1,1], i =1 j= 0 1
 *      [1,2,1],i2 j= 012
 *      [1,3,3,1],
 *      [1,4,6,4,1]
 * ]
 *
 * 力扣：https://leetcode-cn.com/problems/pascals-triangle/
 */
public class 杨辉三角 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        if (numRows <= 0){
            return results;
        }

        for (int i = 0; i < numRows; i++) {

            ArrayList<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 ){
                    row.add(1);

                    //尾部直接添加为1
                }else if (j == i){
                    row.add(1);
                }else {
                    //获取上一个集合的 当前坐标 + 当前坐标-1
                    row.add(results.get(i-1).get(j-1)+results.get(i-1).get(j));
                }
            }

            //添加一行
            results.add(row);
        }
        return results;
    }
}
