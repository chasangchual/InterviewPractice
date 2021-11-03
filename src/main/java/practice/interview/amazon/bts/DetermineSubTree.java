package practice.interview.amazon.bts;

public class DetermineSubTree {
    public boolean isSubTree(BinarySearchTree bst, BinarySearchTree sub) {
        Node found = bst.getRoot();
        Node subRoot = sub.getRoot();
        boolean isSubTree = false;

        while(found != null) {
            if(found.getData().equals(subRoot.getData())) {
                break;
            }

            if(found.getData().compareTo(subRoot.getData()) > 0) {
                found = found.getLeft();
            } else {
                found = found.getRight();
            }
        }

        if(found != null) {
            return isSubTree(found, subRoot);
        }

        return isSubTree;
    }

    private boolean isSubTree(Node one, Node sub) {
        if((one != null && sub == null) || (one == null && sub == null)) {
            return true;
        }

        if(one == null && sub != null) {
            return false;
        }

        if(one.getData().equals(sub.getData())) {
            return isSubTree(one.getLeft(), sub.getLeft()) && isSubTree(one.getRight(), sub.getRight());
        } else {
            return false;
        }
    }

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
                             20
              10                          30
       5            15              25         35
    2      7    12      17       22    27   32    37

 */
        BinarySearchTree sub = new BinarySearchTree();
        sub.add(10);
        sub.add(15);
        sub.add(5);
        sub.add(2);
        sub.add(7);

        DetermineSubTree subTree = new DetermineSubTree();
        System.out.println(subTree.isSubTree(bst, sub));

        new BinarySearchTree();
        sub.add(10);
        sub.add(15);
        sub.add(5);
        sub.add(2);
        sub.add(1);

        System.out.println(subTree.isSubTree(bst, sub));
    }
}
