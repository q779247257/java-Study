package 算法.简单;

public class 将有序数组转换为二叉搜索树 {

    public static void main(String[] args) {
        int[] ints = {-10, -3, 0, 5, 9};
        System.out.println(1/2);

    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length-1);
    }


    /**
     *
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     */
    public TreeNode sortedArrayToBST(int[] nums,int left , int right) {
        if (left > right){
            return null;
        }
        //获取中间索引
        int mid = (left+right)/2;

        //将中间元素设置为节点
        TreeNode treeNode = new TreeNode(nums[mid]);

        //设置左边节点
        treeNode.left = sortedArrayToBST(nums,left,mid-1);

        //设置右边节点
        treeNode.right = sortedArrayToBST(nums,mid+1,right);
        return treeNode;
    }

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }



}
