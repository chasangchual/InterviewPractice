package practice.interview.bst;

public class BinarySearchTreeTest_v1 {
    private static BinarySearchTree_v1<Integer> buildTree() {
        BinarySearchTree_v1<Integer> bst = new BinarySearchTree_v1();
        bst.add(5);
        bst.add(3);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        bst.add(8);
        bst.add(6);
        bst.add(9);
        bst.add(7);
        bst.add(10);
        /**
         *                     5
         *                /       \
         *               3         8
         *              / \       / \
         *             2   4     6   9
         *            / \       / \   \
         *           1             7  10
         */
        return bst;
    }
    public static void add_success() {
        BinarySearchTree_v1<Integer> bst = buildTree();

        System.out.println(bst.printPath(1));
        System.out.println(bst.printPath(10));
        System.out.println(bst.printPath(3));
        System.out.println(bst.printPath(3));
        System.out.println(bst.printPath(4));
        System.out.println(bst.printPath(5));
        System.out.println(bst.printPath(7));
        System.out.println(bst.printPath(6));

        System.out.println(bst.getHeight());

        Node_v1<Integer> parent = null;

        parent = bst.getParent(5);
        System.out.println("expecting null, actual = " + parent);

        parent = bst.getParent(3);
        System.out.println("expecting 5, actual = " + parent.getValue());
        parent = bst.getParent(8);
        System.out.println("expecting 5, actual = " + parent.getValue());

        parent = bst.getParent(1);
        System.out.println("expecting 2, actual = " + parent.getValue());

        parent = bst.getParent(7);
        System.out.println("expecting 6, actual = " + parent.getValue());

        parent = bst.getParent(10);
        System.out.println("expecting 9, actual = " + parent.getValue());
    }

    public static void tree_height() {
        BinarySearchTree_v1<Integer> bst = new BinarySearchTree_v1();
        bst.add(3);
        bst.add(1);
        bst.add(2);
        bst.add(4);

        System.out.println(bst.getHeight());
    }

    public static void delete_leaf() {
        System.out.println("----------------------- delete_leaf -----------------------");
        BinarySearchTree_v1<Integer> bst = buildTree();
        Node_v1<Integer> nodeV1 = null ;

        nodeV1 = bst.find(2);
        System.out.println(Boolean.valueOf(nodeV1.getLeft() == null && nodeV1.getRight() == null));
        bst.delete(1);
        nodeV1 = bst.find(2);
        System.out.println(Boolean.valueOf(nodeV1.getLeft() == null && nodeV1.getRight() == null));

        nodeV1 = bst.find(6);
        System.out.println(Boolean.valueOf(nodeV1.getLeft() == null && nodeV1.getRight() == null));
        bst.delete(7);
        nodeV1 = bst.find(6);
        System.out.println(Boolean.valueOf(nodeV1.getLeft() == null && nodeV1.getRight() == null));

        nodeV1 = bst.find(9);
        System.out.println(Boolean.valueOf(nodeV1.getLeft() == null && nodeV1.getRight() == null));
        bst.delete(10);
        nodeV1 = bst.find(9);
        System.out.println(Boolean.valueOf(nodeV1.getLeft() == null && nodeV1.getRight() == null));
        System.out.println("----------------------- delete_leaf -----------------------");
    }

    public static void delete_single_child() {
        System.out.println("----------------------- delete_single_child -----------------------");
        BinarySearchTree_v1<Integer> bst = buildTree();
        Node_v1<Integer> nodeV1 = null ;

        System.out.println(bst.printPath(1));
        bst.delete(2);
        System.out.println(bst.printPath(1));

        System.out.println(bst.printPath(7));
        bst.delete(6);
        System.out.println(bst.printPath(7));

        System.out.println(bst.printPath(10));
        bst.delete(9);
        System.out.println(bst.printPath(10));

        System.out.println("----------------------- delete_single_child -----------------------");
    }

    public static void delete_both_child() {
        System.out.println("----------------------- delete_both_child -----------------------");
        BinarySearchTree_v1<Integer> bst = buildTree();
        Node_v1<Integer> nodeV1 = null ;

        System.out.println(bst.printPath(1));
        bst.delete(3);
        System.out.println(bst.printPath(1));

        System.out.println(bst.printPath(7));
        System.out.println(bst.printPath(10));
        bst.delete(8);
        System.out.println(bst.printPath(7));
        System.out.println(bst.printPath(10));

        System.out.println("----------------------- delete_both_child -----------------------");
    }

    public static void main(String[] args) {
        BinarySearchTreeTest_v1.add_success();
        BinarySearchTreeTest_v1.tree_height();

        BinarySearchTreeTest_v1.delete_leaf();
        BinarySearchTreeTest_v1.delete_single_child();
        BinarySearchTreeTest_v1.delete_both_child();
    }
}
