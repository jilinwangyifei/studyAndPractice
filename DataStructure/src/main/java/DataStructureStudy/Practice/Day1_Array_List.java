package DataStructureStudy.Practice;

public class Day1_Array_List {

    /**
     * 数组和链表
     *
     * 实现一个支持动态扩容的数组
     *
     * 实现一个大小固定的有序数组，支持动态增删改操作  todo
     *
     * 实现两个有序数组合并为一个有序数组
     *
     *
     * 实现单链表、循环链表、双向链表，支持增删操作
     *
     * 实现单链表反转
     *
     * 实现两个有序的链表合并为一个有序链表
     g
     * 实现求链表的中间结点
     *
     * 给定单向链表的头指针和一个结点指针，定义一个函数在0(1)时间删除该结点
     *
     **/

    // 实现一个大小固定的有序数组，支持动态增删改操作


    //动态扩容
    void dynamicExpansion() {
        int[] dynamicArray = new int[20];

        if (dynamicArray.length == 20) {
            int [] newArray = new int[dynamicArray.length*2];
            System.arraycopy(dynamicArray,0,newArray,0,dynamicArray.length);
            dynamicArray = newArray;
        }
    }

    //有序数组合并
    void mergeArray(int[] array1, int [] array2) {
       int i = array1.length - 1;
       int j = array2.length - 1;
       int tar = i+j-1;
       int array[] = new int[tar-1];
       while (j >= 0) {
           array[tar--] = i >= 0 && array1[i] > array2[j] ? array1[i--] : array2[j--];
       }
    }

    //实现单链表、循环链表、双向链表，支持增删操作 此处只提供数据接口

    //循环链表
    class CircleLisNode<E> {
        E element;
        CircleLisNode<E> next;
        CircleLisNode<E> pre;

        CircleLisNode(CircleLisNode<E> prev, E element, CircleLisNode<E> next){
            this.element = element;
            this.pre = prev;
            this.next = next;
        }
    }

    //双向链表
    class TwoWayListNode<E> {
        E value;
        TwoWayListNode<E> next;
        TwoWayListNode<E> prev;
    }

    //单链表
    class ListNode {
        ListNode next;
        int value;
    }

    //链表反转
    void reverseNode(ListNode root) {
        ListNode reverseNode = new ListNode(); //反转后的链表
        while (root.next != null) {
            ListNode next = root.next;
            root.next = reverseNode;
            reverseNode = root;
            root = next;
        }
    }

    //合并链表
    ListNode mergeListNode(ListNode root1, ListNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        if (root1.value < root2.value) {
            root1.next = mergeListNode(root1.next, root2);
            return root1;
        }
        root2.next = mergeListNode(root1, root2.next);
        return root2;
    }


    //实现求链表的中间结点
    ListNode middleListNode (ListNode root) {
        ListNode slow = root ,fast = root;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //给定单向链表的头指针和一个结点指针，定义一个函数在0(1)时间删除该结点
    ListNode deleteListNode(ListNode head, ListNode deListNode){

        if(head == null || deListNode == null){
            return head;
        }

        //头结点
        if(head == deListNode){
            return head.next;
        }

        //尾节点
        if(deListNode.next == null){
            ListNode tmp = head;
            while (tmp.next != deListNode){
                tmp = tmp.next;
            }
            tmp.next = null;
        }
        //其他节点
        else{
            //将待删除节点的值替换成下个节点的值
            deListNode.value = deListNode.next.value;
            //待删除节点的下个值 指向下下个节点
            deListNode.next = deListNode.next.next;
        }

        return head;
    }

}

class ListNode{
    int value;
    ListNode next;
}
