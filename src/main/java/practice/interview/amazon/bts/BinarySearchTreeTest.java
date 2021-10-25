package practice.interview.amazon.bts;

import java.util.List;
import java.util.Objects;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(15);
        bst.add(5);
        bst.add(25);
        bst.add(35);
        bst.add(2);
        bst.add(7);
        bst.add(12);
        bst.add(17);
        bst.add(22);
        bst.add(27);
        bst.add(32);
        bst.add(37);

        /**
         *                                 20
         *                         /                     \
         *                       10                      30
         *                   /         \             /         \
         *                  5          15          25            35
         *                /    \      /    \     /     \       /     \
         *              2       7   12     17  22      27   32       37
         *
         */
        printDFS(bst);
        printBFS(bst);
        printInorder(bst);
        printPreorder(bst);
        printPostorder(bst);
        printIsBst(bst);
    }

    private static void printDFS(final BinarySearchTree bst) {
        List<Node> nodes = bst.dfs();
        System.out.println("depth first: " + nodes);
    }

    private static void printBFS(final BinarySearchTree bst) {
        List<Node> nodes = bst.bfs();
        System.out.println("breadth first: " + nodes);
    }

    private static void printInorder(final BinarySearchTree bst) {
        List<Node> nodes = bst.inorder();
        System.out.println("inorder: " + nodes);
    }

    private static void printPreorder(final BinarySearchTree bst) {
        List<Node> nodes = bst.preorder();
        System.out.println("preorder: " + nodes);
    }

    private static void printPostorder(final BinarySearchTree bst) {
        List<Node> nodes = bst.postorder();
        System.out.println("post order: " + nodes);
    }

    private static void printIsBst(final BinarySearchTree bst) {
        System.out.println("Is Bst ? : " + isBST(bst));

    }

    private static Boolean isBST(final BinarySearchTree bst) {
        Node node = bst.getRoot();

        return(isBST(node.getLeft(), null, node.getData()) && isBST(node.getRight(), node.getData(), null));
    }

    private static Boolean isBST(final Node node, Integer min, Integer max) {
        if(Objects.isNull(node)) {
            return true;
        }

        if(Objects.nonNull(min) && node.getData() < min) {
            return false;
        }

        if(Objects.nonNull(max) && node.getData() > max) {
            return false;
        }

        return isBST(node.getLeft(), min, node.getData()) && isBST(node.getRight(), node.getData(), max);
    }
}
