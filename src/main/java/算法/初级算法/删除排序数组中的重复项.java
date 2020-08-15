package 算法.初级算法;

import java.util.Arrays;

public class 删除排序数组中的重复项 {
    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3};
        System.out.println(removeDuplicates(arr));

        System.out.println(Arrays.toString(arr));
    }



    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        //前指针
        int p = 0;

        //后指针
        int q = p+1;
        while (q < nums.length){
            if (nums[p] == nums[q]){
                //后指针往后一位
                q++;
            }else {
                //不相等 前指针往前走 后指针继续积累
                nums[ p++ + 1 ] = nums[q++];
            }
        }

        return p+1;
    }

}
