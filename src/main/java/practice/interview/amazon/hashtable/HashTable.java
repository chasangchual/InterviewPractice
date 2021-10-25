package practice.interview.amazon.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HashTable {
    Node[] buckets = new Node[1000];

    public void put(Integer key, Object data) {
        Integer bucketPos = key.hashCode() % 1000;
        put(key, data, bucketPos);
    }

    public Object get(Integer key) {
        Integer bucketPos = key.hashCode() % 1000;
        return get(key, bucketPos);
    }

    private Object get(Integer key, Integer bucketPos) {
        if(Objects.nonNull(buckets[bucketPos])) {
            Optional<Node> found = find(buckets[bucketPos], key);
            if(found.isPresent()) {
                return found.get().getData();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private void put(Integer key, Object data, Integer bucketPos) {
        if(Objects.isNull(buckets[bucketPos])) {
            buckets[bucketPos] = new Node(key, data);
        } else {
            Node node = buckets[bucketPos];
            Optional<Node> found = find(node, key);
            if(found.isPresent()) {
                found.get().setData(data);
            } else {
                while(Objects.nonNull(node.getNext())) {
                    node = node.getNext();
                }
                node.setNext(new Node(key, data));
            }
        }
    }

    private Optional<Node> find(final Node node, Integer key) {
        Node curr = node;
        Boolean found = false;
        while(Boolean.FALSE.equals(found) && Objects.nonNull(curr)) {
            if(curr.getKey().equals(key)) {
                found = true;
            } else {
                curr = curr.getNext();
            }
        }

        return found ? Optional.of(curr) : Optional.empty();
    }

    class Node {
        Integer key;
        Object data;
        Node next;

        public Node(Integer key, Object data) {
            this.key = key;
            this.data = data;
        }

        public Integer getKey() {
            return this.key;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
