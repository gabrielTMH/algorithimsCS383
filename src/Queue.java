/** first in first out */
public interface Queue<K> {
    /**  */
    public K dequeue();
    public void enqueue(K key);
    public boolean isEmpty();
}
