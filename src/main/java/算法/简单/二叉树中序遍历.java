package 算法.简单;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 二叉树中序遍历 {


    /**
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        return inorderTraversal(root,new ArrayList<>());
    }

    /**
     * @param item 节点
     * @param stores  存储的节点
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode item, List<Integer> stores) {
        //节点等于null  直接返回list
        if (item == null){
            return stores;
        }

        //左边不等于null 添加左边
        if (item.left != null){
            inorderTraversal(item.left,stores);
        }

        //然后添加自身
        stores.add(item.val);

        //右边不等于null
        if (item.right != null){
            inorderTraversal(item.right,stores);
        }

        return stores;
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




