package 算法.简单;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 力扣：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 示例
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 给出深度3
 */
public class 二叉树的最大深度 {

    public int maxDepth(TreeNode root) {
        //如果当前节点等于null 或根节点等于null  那么返回0
        if (root == null){
            return 0;
        }else {
            //获取左边的深度
            int left = maxDepth(root.left);

            //获取右边的深度
            int right = maxDepth(root.right);

            //+1 累计层数
            return Math.max(left, right) +1;
        }
    }



    class TreeNode {
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
