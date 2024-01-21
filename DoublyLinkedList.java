package linkedlist.problems.lru;

import java.util.HashMap;

class Node {
    int value;
    Node next;
    Node prev;

    Node(int value) {
        this.value = value;
    }
}

public class DoublyLinkedList {
    private Node HEAD;
    private Node TAIL;
    private HashMap<Integer, Node> nodeMap;

    public DoublyLinkedList() {
        this.HEAD = null;
        this.TAIL = null;
        this.nodeMap = new HashMap<>();
    }

    void addNode(int key) {
        Node node = new Node(key);
        node.next = null;
        node.prev = null;

        if (this.HEAD == null) {
            // List is empty
            this.HEAD = node;
            this.TAIL = this.HEAD;
        } else {
            this.TAIL.next = node;
            node.prev = this.TAIL;
            this.TAIL = node;
            node.next = null;
        }

        this.nodeMap.put(key, node);
    }

    int evict() {
        int evictedValue = -1;

        if (this.HEAD.next == null) {
            // Empty the List
            this.HEAD = null;
        } else {
            evictedValue = this.HEAD.value;
            this.HEAD = this.HEAD.next;
            this.HEAD.prev = null;
        }

        return evictedValue;
    }

    void refreshNode(int key) {
        Node node = this.nodeMap.get(key);

        if (node.next == null && node.prev == null) {
            // Single element in the node
            return;
        }
        if (node.prev == null) {
            // Node is at the HEAD
            this.HEAD = node.next;
            this.HEAD.prev = null;
            
            this.TAIL.next = node;
            node.prev = this.TAIL;
            this.TAIL = node;
            node.next = null;

            return;
        }

        if (node.prev != null && node.next != null) {
            // Node is in the middle
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            this.TAIL.next = node;
            node.prev = this.TAIL;
            this.TAIL = node;
            node.next = null;
        }
    }
}