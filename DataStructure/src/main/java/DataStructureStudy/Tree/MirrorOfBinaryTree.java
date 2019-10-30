package DataStructureStudy.Tree;

/**
 * Created by wangbo on 2018/4/5.
 *
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像
 *
 */
public class MirrorOfBinaryTree {

    public void mirrorRecursively(Node root) {

        if (root == null) {
            return;
        }

        Node leftNode = root.getLeftNode();
        Node rightNode = root.getRightNode();

        if (leftNode != null || rightNode != null) {

            root.setLeftNode(rightNode);
            root.setRightNode(leftNode);

            mirrorRecursively(leftNode);
            mirrorRecursively(rightNode);
        }

    }

    class Node{

        Node leftNode;

        Node rightNode;

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

    }
}
