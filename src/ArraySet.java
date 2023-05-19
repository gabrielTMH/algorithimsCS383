public class ArraySet<K> implements Set<K> {

    /** Array of keys. Only the first size elements are part of this set. */
    private K[] keys;

    /** Number of keys currently in this set. */
    private int size;

    public ArraySet() {
        keys = (K[])new Object[1];
    }

    @Override
    public void add(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return;
            }
        }
        expandIfNecessary();
        keys[size] = key;
        size++;
    }

    @Override
    public boolean contains(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                keys[i] = keys[size - 1];  // Swaps in last key
                size--;
                return;
            }
        }
    }

    /** If this set is full, copies the keys into an array twice as large. */
    private void expandIfNecessary() {
        if (size == keys.length) {
            K[] stretched = (K[])new Object[size * 2];
            for (int i = 0; i < size; i++) {
                stretched[i] = keys[i];
            }
            keys = stretched;
        }
    }

}
