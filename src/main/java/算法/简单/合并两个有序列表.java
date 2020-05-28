package 算法.简单;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/23
 * @描述：
 */




public class 合并两个有序列表 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;

        //把后边的追加到前边
        if (l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }

    }

    public static void main(String[] args) {
        System.out.println("参数："+args);
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);


        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);
        System.out.println("初始化数据1:"+listNode);
        System.out.println("初始化数据2:"+listNode2);

        ListNode listNode1 = new 合并两个有序列表().mergeTwoLists(listNode, listNode2);
        System.out.println("结果:"+listNode1);


    }
}
