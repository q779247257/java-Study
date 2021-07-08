package 算法.简单;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 路径总和 {

    public static void main(String[] args) {
        System.out.println("1");
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {

        //节点为null 直接返回false
        if (root == null){
            return false;
        }

        //判断当前是否是子节点
        if (root.left == null && root.right == null){
            return root.val == targetSum;
        }
        boolean left = hasPathSum(root.left, targetSum - root.val);

        //先判断左边是不是true 如果不是 再尝试邮编
        return left || hasPathSum(root.right, targetSum - root.val);
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
