public class LinkedQueue<K> implements Queue<K> {

    /** List node. */
    private class Node {

        K key;

        Node next;

        Node(K key, Node next) {
            this.key = key;
            this.next = next;
        }

    }

    /** The front node of this queue, or null if this queue is empty. */
    private Node front;

    /** The back node of this queue. Undefined if this queue is empty. */
    private Node back;

    @Override
    public K dequeue() {
        K result = front.key;
        front = front.next;
        return result;
    }

    @Override
    public void enqueue(K key) {
        Node n = new Node(key, null);
        if (front == null) {
            front = n;
            back = n;
        } else {
            back.next = n;
            back = n;
        }
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

}
