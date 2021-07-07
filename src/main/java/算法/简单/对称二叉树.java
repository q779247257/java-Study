package 算法.简单;

/**
 * 如果一个树的左子树与右子树镜像对称，那么这个树是对称的。
 * 力扣对称二叉树：https://leetcode-cn.com/problems/symmetric-tree/
 */
public class 对称二叉树 {

    public boolean isSymmetric(TreeNode root) {

        return isSymmetric(root.left,root.right);

    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        //如果2个节点都为null 直接返回true
        if (left == null && right == null){
            return true;

            //其中一方等于null 返回false
        }else if (left == null || right == null){
            return false;

            //左右值不相等 返回false
        }else if (left.val != right.val){
            return false;
        }else {
            //继续递归比较数之间对称的节点
            return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
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
