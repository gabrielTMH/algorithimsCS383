public class LinkedSet<K> implements Set<K> {
    private class Node{
        K key;
        Node next;
        Node(K key, Node next){
            this.key=key;
            this.next=next;
        }
    }
    private Node front;
    @Override
    public void add(K key){
        for(Node n = front; n!= null; n=n.next){
            if(n.key.equals(key)){
                return;
            }
        }
        front= new Node(key,front);
    }
    @Override
    public  boolean contains(K key){
        for (Node n = front; n!= null; n=n.next){
            if(n.key.equals(key)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isEmpty(){
        if (front == null){
            return true;
        }
        return false;
    }
    @Override
    public void remove(K key){
        if(front == null){
            return;
        }else if(front.key.equals(key)){
            front=front.next;
        }else{
            for (Node p = front; p.next!=null; p =p.next){
                Node n = p.next;
                if(n.key.equals(key)){
                    p.next = n.next;
                    return;
                }
            }
        }
    }
}
