package DataStructureStudy.Practice;

import DataStructure.tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class Day5_BinaryTree_Heap {
    /**
     * 二叉树和堆
     *
     * 实现二叉查找树 支持插入 删除 查找功能
     *
     * 找到二叉查找树的后继 前驱节点  todo
     *
     * 实现二叉树 前 中 后 序遍历 及 按层遍历
     *
     * 小顶堆 大顶堆 优先级队列
     *
     * 实现堆排序
     *
     * 利用优先级队列合并k个有序数组 todo
     *
     * 动态数据集合的动态top k  todo
     *
     *       复杂度分析
     * 二叉树的时间复杂度是O(n)
     * 堆的 查找删除时间复杂度为O(logn)
     *
     * 堆排序的时间复杂度是O(nlogn)
     *
     * */

    class BianrySearchTree {


        Node add(Integer x, Node root) {
            if (root == null)
                return new Node(x, null, null);

            int compareResult = x.compareTo(root.value);

            if (compareResult < 0) {
                root.left = add(x, root.left);
            } else if (compareResult > 0) {
                root.right = add(x, root.right);
            } else ;
            return root;
        }

        Node delete(Integer x, Node root) {
            if (root == null)
                return root;

            int compareResult = x.compareTo(root.value);

            if (compareResult < 0) {
                root.left = delete(x, root.left);
            } else if (compareResult > 0) {
                root.right = delete(x, root.right);
            } else if (root.left != null && root.right != null) {
                root.value = findMin(root.left).value;
                root.right = delete(root.value, root.right);
            } else {
                root = root.left != null? root.left:root.right;
            }
            return root;
        }

        Node findMin(Node node) {
            if(node == null) return null;
            if (node.left == null ) {
                return node;
            }
            return findMin(node.left);
        }

        boolean get(Integer x, Node root) {
            if (root == null) return false;

            int compareResult = x.compareTo(root.value);

            if (compareResult < 0) {
                return get(x, root.left);
            } else if (compareResult > 0) {
                return get(x, root.right);
            } else return true;
        }

        class Node {
            Integer value;
            Node left;
            Node right;

            Node (Integer value, Node left, Node right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }
        }

        //前序遍历
        void preOrder(Node root) {
            if (root == null) return;
            System.out.println(root.value);
            preOrder(root.left);
            preOrder(root.right);
        }

        //中序遍历
        void inOrder(Node root) {
            if (root == null) return;
            preOrder(root.left);
            System.out.println(root.value);
            preOrder(root.right);
        }

        //后序遍历
        void postOrder(Node root) {
            if (root == null) return;
            preOrder(root.left);
            preOrder(root.right);
            System.out.println(root.value);
        }

        //按层遍历
        void cengciOrder(Node root) {
            if (root == null) return;
            Queue queue = new LinkedList<Node>();
            ((LinkedList) queue).add(root);
            while (!queue.isEmpty()) {
                Node node = (Node)queue.poll();
                System.out.println(node.value);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null){
                    queue.add(root.right);
                }
            }
        }

        //小顶堆
        class smallHeap<E> {
            public Object[] value;
            private int size;

            //上升方法
            @SuppressWarnings("unchecked")
            private void siftUpComparable(int k, E x) {
                Comparable<E> key = (Comparable<E>)x;
                while (k > 0) {
                    int c = (k-1) >>> 1;
                    Object parent = value[c];
                    if (key.compareTo((E)parent) >= 0) break;
                    value[k] = parent;
                    k = c;
                }
                value[k] = x;
            }

            //下降方法
            @SuppressWarnings("unchecked")
            private void siftDownComparable(int k, E x) {
                Comparable<E> key = (Comparable<E>)x;
                int half = k >> 1;
                while (k < half) {
                    int child =  k << 1 + 1;
                    Object next = value[child];
                    int right = child + 1;
                    if(right < size &&
                            ((Comparable<E>)next).compareTo((E)value[right]) > 0) {
                        next = value[child = right];
                    }
                    if (key.compareTo((E)next) <= 0) break;
                    value[k] = next;
                    k = child;
                }
                value[k] = x;
            }

        }

        //大顶堆(和小顶堆相反)
        class bigHeap {

        }

        //优先级队列 参考 PriorityQueue

        //堆排序
        void heapSort(int[] array, int n) {
            buildHeap(array, n);
            int k = n;
            while (k > 1) {
                swap(array,k,0);
                k--;
                heapif(array, k, 0);
            }
        }

        void swap(int []array,int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        void buildHeap(int[] array, int n) {
            for (int i = n/2; i >= 1; i--) {
                heapif(array, n ,i);
            }
        }

        void heapif(int[] array,int n, int i) {
            while (true) {
                int maxPos = i;
                if (2*i < n && array[i] < array[2*i]) maxPos = 2*i;
                if (2*i+1 < n && array[i]< array[2*i+1]) maxPos = 2*i+1;
                if (maxPos == i ) break;
                swap(array, maxPos, i);
                i = maxPos;
            }
        }

    }
}
