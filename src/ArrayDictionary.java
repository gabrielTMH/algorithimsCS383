//public class ArrayDictionary<Key,Value> implements Dictionary<Key,Value> {
//    /** Array of keys. Only the first size elements are part of this set. */
//    private Key[] keys;
//    private Value[] values;
//    /** Number of keys currently in this set. */
//    private int size;
//    public ArrayDictionary() {
//        keys = (Key[])new Object[1];
//        values = (Value[])new Object[1];
//    }
//
//    @Override
//    public Value get(Key key){
//        for(int i =0; i<size;i++){
//            if(keys[i].equals(key)){
//                return values[i];
//            }
//        }
//        return null;
//    }
//
//    /** Returns true if this dictionary is empty. */
//    @Override
//    public boolean isEmpty(){
//        return size ==0;
//    }
//
//    /**
//     * Associates key with value in this dictionary. Overwrites any value
//     * previously associated with key. If value is null, removes any association
//     * with key.
//     */
//    @Override
//    public void put(Key key, Value value){
//        for(int i =0; i<size;i++){
//            if(keys[i].equals(key)){
//                if(value==null){
//                    remove(key);
//                }else{
//
//                    values[i]=value;
//                }
//            }
//        }
//        expandIfNecessary();
//        if(value!=null) {
//            keys[size] = key;
//            values[size] = value;
//            size++;
//        }
//    }
//    public void remove(Key key){
//        for(int i =0; i<size;i++){
//            if(keys[i].equals(key)){
//                keys[i]=keys[size-1];
//                values[i]=values[size-1];
//                size--;
//            }
//        }
//
//
//    }
//    private void expandIfNecessary() {
//        if (size == keys.length) {
//            Key[] stretchedK = (Key[])new Object[size * 2];
//            Value[] stretchedV = (Value[])new Object[size*2];
//            for (int i = 0; i < size; i++) {
//                stretchedK[i] = keys[i];
//                stretchedV[i]=values[i];
//            }
//            keys = stretchedK;
//            values=stretchedV;
//        }
//    }
//
//}

import java.util.ArrayList;
public class ArrayDictionary<K,V> implements Dictionary<K,V>  {

    //Arrays of Keys,Values
    ArrayList<java.lang.Object> keys = new ArrayList<>();
    ArrayList<java.lang.Object> values = new ArrayList<>();

    public void ArrayDictionary(K keys, V values) {
    }

    @Override
    public boolean isEmpty() {
        if (keys.size()==0) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        for(int z=0; z< keys.size(); ++z) {
            if(keys.get(z)==key) {
                return (V) values.get(z);
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int index = ((Integer) key)-1;
        int current_size = keys.size();

        if(value==null) {
            //if value is null, then make rest of list null afterwards
            keys.remove(key);
            values.remove(value);
        }
        else {
            //add value to list
            keys.add(index, key);
            values.add(index, value);

        }
    }

    public static void main(String[] args) {
        ArrayDictionary f = new ArrayDictionary();;
        f.put(1, "one");
        f.put(2, "two");
        f.put(1, null);

        //StdOut.println(f.values);
        //StdOut.println(f.keys);

    }
}