
/** a last in first out stack*/
public interface Stack<K> {
    public boolean isEmpty();

    public K pop();

    public void push(K key);
}
/** we can peak by poping somthing ans then pushing it back */