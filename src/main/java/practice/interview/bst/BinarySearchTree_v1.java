package practice.interview.bst;

public class BinarySearchTree_v1<T extends Comparable<T>> {
    Node_v1<T> root = null;

    public Node_v1<T> getRoot() {
        return root;
    }

    public void add(T value) {
        add(new Node_v1(value));
    }

    public void add(Node_v1<T> nodeV1) {
        if(root != null) {
            add(root, nodeV1);
        } else {
            root = nodeV1;
        }
    }

    public void add(Node_v1<T> parent, Node_v1<T> child) {
        if(child.compareTo(parent) > 0) {
            if(parent.hasRight()) {
                add(parent.getRight(), child);
            } else {
                parent.setRight(child);
            }
        } else if(child.compareTo(parent) < 0) {
            if(parent.hasLeft()) {
                add(parent.getLeft(), child);
            } else {
                parent.setLeft(child);
            }
        }
    }

    public String printPath(T value) {
        String path = "* ";
        if(root != null) {
            path = printPath(root, value, path);
        }
        return path;
    }

    public Node_v1<T> find(final T value) {
        return find(root, value);
    }

    public Node_v1<T> find(Node_v1<T> nodeV1, final T value) {
        if(nodeV1 == null) {
            return null;
        }

        if(value.compareTo(nodeV1.getValue()) == 0) {
            return nodeV1;
        } else if(value.compareTo(nodeV1.getValue()) > 0) {
            return find(nodeV1.getRight(), value);
        } else {
            return find(nodeV1.getLeft(), value);
        }
    }

    public String printPath(Node_v1<T> nodeV1, T value, String path) {
        path = path + " -> " + nodeV1.getValue().toString();

        if(value.compareTo(nodeV1.getValue()) > 0) {
            if(nodeV1.hasRight()) {
                path = printPath(nodeV1.getRight(), value, path);
            }
        } else if(value.compareTo(nodeV1.getValue()) < 0) {
            if(nodeV1.hasLeft()) {
                path = printPath(nodeV1.getLeft(), value, path);
            }
        }

        return path;
    }

    public Integer getHeight() {
        return getHeight(root, 0);
    }

    public Integer getHeight(Node_v1<T> nodeV1, int height) {
        if(nodeV1 == null) {
            return height;
        }

        Integer heightOfLeft = getHeight(nodeV1.getLeft(), height + 1);
        Integer heightOfRight = getHeight(nodeV1.getRight(), height + 1);

        return Math.max(heightOfLeft, heightOfRight);
    }

    public Node_v1<T> delete(T value) {
        Node_v1<T> nodeV1 = find(value);

        if(nodeV1 != null) {
            Node_v1<T> parent = getParent(root, value);
            if(parent != null) {
                boolean isRight = parent.compareTo(value) < 0;
                // System.out.println("isRight = " + isRight);
                if(isLeaf(nodeV1)) {
                    System.out.println("delete leaf - " + value);
                    if(parent != null) {
                        if(isRight) {
                            parent.setRight(null);
                        } else {
                            parent.setLeft(null);
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else if(hasBoth(nodeV1)) {
                    Node_v1<T> smallest = getSmallest(nodeV1.getRight());
                    delete(smallest.getValue());

                    smallest.setLeft(nodeV1.getLeft());
                    smallest.setRight(nodeV1.getRight());
                    if(isRight) {
                        parent.setRight(smallest);
                    } else {
                        parent.setLeft(smallest);
                    }
                } else {
                    Node_v1<T> child = nodeV1.getLeft() != null ? nodeV1.getLeft() : nodeV1.getRight();
                    if(isRight) {
                        parent.setRight(child);
                    } else {
                        parent.setLeft(child);
                    }
                }
            }
        }

        return nodeV1;
    }

    private boolean isLeaf(Node_v1<T> nodeV1) {
        if(nodeV1 != null) {
            return (nodeV1.getRight() == null && nodeV1.getLeft() == null);
        } else {
            return false;
        }
    }

    private boolean hasBoth(Node_v1<T> nodeV1) {
        if(nodeV1 != null) {
            return (nodeV1.getRight() != null && nodeV1.getLeft() != null);
        } else {
            return false;
        }
    }

    public Node_v1<T> getParent(T value) {
        return getParent(root, value);
    }

    private Node_v1<T> getParent(Node_v1<T> nodeV1, T value) {
        if(nodeV1 == null) {
            return null;
        }

        if((nodeV1.getRight() != null && nodeV1.getRight().compareTo(value) == 0) ||
           (nodeV1.getLeft() != null && nodeV1.getLeft().compareTo(value) == 0)) {
            return nodeV1;
        }

        if(nodeV1.compareTo(value) < 0) {
            return getParent(nodeV1.getRight(), value);
        } else if(nodeV1.compareTo(value) > 0) {
            return getParent(nodeV1.getLeft(), value);
        }
        return null;
    }

    private Node_v1<T> getSmallest(Node_v1<T> nodeV1) {
        if(nodeV1 == null) {
            return null;
        }

        Node_v1<T> curr = nodeV1;
        while(curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }
}
