package 算法.简单;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 力扣链接： https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class 平衡二叉树 {

    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }

        //比较左节点和右节点的高度差异
        if (Math.abs(height(root.left) - height(root.right)) <=1) {

            //判断左右子节点是否都平衡
            return isBalanced(root.left) && isBalanced(root.right);

        }else {
            //高度相差过大，直接返回false
            return false;
        }
    }

    /**
     * 获取数的最深高度
     * @param node 节点
     */
    public int height(TreeNode node){
        if (node == null){
            return 0;
        }else {
            int left = height(node.left);
            int right = height(node.right);
            return Math.max(left,right)+1;
        }
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
