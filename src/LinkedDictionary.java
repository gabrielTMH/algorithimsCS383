//public class LinkedDictionary<Key,Value> implements Dictionary<Key,Value> {
//    private Node front;
//    private  class Node{
//        Key key;
//        Value value;
//        LinkedDictionary.Node next;
//        Node(Key key, Node next, Value value){
//            this.key=key;
//            this.next=next;
//            this.value= value;
//        }
//    }
//    public void remove(Key key){
//        if(front == null){
//            return;
//        }else if(front.key.equals(key)){
//            front=front.next;
//        }else{
//        for(Node p =front;p.next!=null;p=p.next) {
//            Node n = p.next;
//            if (n.key.equals(key)) {
//                p.next = n.next;
//                return;
//            }
//        }
//        }
//    }
//
//    public Value find(Key key){
//        Value result;
//        for(Node n = front; n!= null; n=n.next){
//            if(n.key.equals(key)){
//                return n.value;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Value get(Key key){
//       /**Value v= this.find(key);
//       if(v.equals(null)){
//           return null;
//       }else{
//           return v;
//       }
//*/
//        for(Node n = front; n!= null; n=n.next){
//            if(n.key.equals(key)){
//                return n.value;
//            }
//        }
//        return null;
//
//    }
//    @Override
//    public boolean isEmpty(){
//        if(this.front==null){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    @Override
//    public void put(Key key, Value value){
//    if(value==null){
//     remove(key);
//    }else{
//        front = new Node(key,front,value);
//    }
//    }
//
//
//}

public class LinkedDictionary<K,V> implements Dictionary<K,V> {

    private class Node {
        K key;
        V value;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node head;

    public LinkedDictionary() {
        head = null;
    }


    @Override
    public V get(K key) {
        Node n = head;
        for (; n != null && !n.key.equals(key); n = n.next) {
        }
        assert n == null || n.key.equals(key);
        if (n == null) return null;
        else return n.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
    @Override
    public void put(K key, V value) {
        if(head == null && value == null){ return;}
        if (head == null && value != null) {
            head = new Node(key, value);
            return;
        }
        if (head.key.equals((key)) && value == null) {
            head = head.next;
            return;
        }
        Node find = head;
        Node prev = null;
        for (;find != null && !find.key.equals(key);) {
            prev = find;
            find = find.next;
        }
        if (find == null) {
            prev.next = new Node(key, value);
            return;
        }
        if(find.key.equals(key) && value == null){
            Node p = head;
            Node n = head.next;
            while (n != null && !n.key.equals(key)) {
                p = n;
                n = n.next;
            }
            if (n != null) {
                p.next = n.next;
            }
            return;
        }
        if(value==null){ return;}
        find.value = value;
    }
}
