package 算法.简单;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 */
public class 二叉树的最小深度 {


    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        //如果左边有节点 则寻找左边的长度
        if (root.left != null){
            left = minDepth(root.left);
        }

        //如果右边有节点 这寻找右边的长度
        if (root.right != null) {
            right = minDepth(root.right);
        }

        return Math.min(left,right)+1;
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
