import java.util.HashMap;

class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        // Dummy nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // Remove a node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Add node just after head (Most Recently Used)
    private void add(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // Move accessed node to front
        remove(node);
        add(node);

        return node.value;
    }

    public void put(int key, int value) {

        // If key already exists
        if (map.containsKey(key)) {
            Node node = map.get(key);

            remove(node);
            node.value = value;
            add(node);

            return;
        }

        // Create new node
        Node node = new Node(key, value);
        map.put(key, node);
        add(node);

        // Capacity exceeded
        if (map.size() > capacity) {

            // Least Recently Used node
            Node lru = tail.prev;

            remove(lru);
            map.remove(lru.key);
        }
    }
}