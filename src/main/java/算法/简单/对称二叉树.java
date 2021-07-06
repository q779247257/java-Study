package 算法.简单;

/**
 * 检查二叉树是否是对称的
 * 对称
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 非对称
 *    1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/same-tree/solution/xiang-tong-de-shu-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。;
 */
public class 对称二叉树 {

    /**
     * 是否对称
     * @param root 跟节点
     * @return 是否对称
     */
    public boolean isSymmetric(TreeNode root) {

    }

    /**
     *   是否对称
     * @param p 左节点
     * @param q 又节点
     * @return 是否对称
     */
    public boolean isSameTree(TreeNode p,TreeNode q) {

        //两边都为null 直接返回true
       if (null == p && null == q){
           return true;

           //其中一个为null 返回false
       }else if (null == p || null == q){
            return false;

            //如果2个相同，继续遍历比较子节点
       }else if (p.val == q.val){
           return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
       }
        return false;
    }
}

class TreeNode {
      int val;
      TreeNode left;//左子数
      TreeNode right;//右子树
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
