//import edu.princeton.cs.algs4.StdOut;
//
//import java.util.ArrayList;
//
//public class GeneralTree<Value extends Comparable<Value>> {
//
//
//    public GeneralTree() {
//        this.root = null;
//    }
//
//    public class Node{
//        Value key;
//        Node parent;
//        java.util.ArrayList<Node> children= new java.util.ArrayList<Node>(100);
//
//        public Node(Node parent,Value key){
//            this.parent = parent;
//            this.key=key;
//        }
//        public Node(Value key){
//            this.key=key;
//        }
//    }
//    Node root;
//
//    public void setRoot(Value n){
//        this.root= new Node(n);
//    }
//    public Node find(Value c)
//    {
//        return find(root, c);
//
//    }
//    public Node find(Node node, Value c){
//        int cmp = c.compareTo(node.key);
//        if(cmp==0){
//           // StdOut.print("\n tryna add to this parent"+node.key);
//            return node;
//        }else if(node.children.size()>0){
//            for(Node abc : node.children){
//                Node res=find(abc,c);
//                if(res!=null){
//                    return res;
//                }
//            }
//        }
//            return null;
//    }
//    //this will add a new node of value c to the node with value p
//    public void addChild(Value p, Value c){
//        Node node = new Node(c);
//        Node parent=find(p);//.children.add(node);
//
//        parent.children.add(node);
//        //System.out.println("here");
//    }
//
//
//    public String toString(){
//         String s="";
//        if (this.root != null) {
//            s += toString(this.root);
//        }else{
//            s="()";
//        }
//         return s;
//    }
//    public String toString(Node n){
//       String s = "(" + n.key;
//        if(n.children!=null) {
//            for(Node abc : n.children){
//               s+= " "+toString(abc);
//            }
//        }
//            return s+")";
//
//    }
//
//    public static void main(String[] args) {
//        GeneralTree tree = new GeneralTree<>();
//        tree.setRoot(5);
//        //tree.addChild(5,3);
//        tree.addChild(5,2);
//        tree.addChild(2,6);
//        tree.addChild(2,8);
//        tree.addChild(5,3);
//        tree.addChild(3,10);
//        tree.addChild(3,11);
//        GeneralTree.Node j = tree.root;
//            StdOut.print(tree.find(3).children.size());
//    }
//
//}

import java.util.ArrayList;

public class GeneralTree<K> {

    // Creating the Node Class
    private Node root = null;

    class Node{
        K key;
        ArrayList<Node> node = new ArrayList<>();  // Every Node can have child Nodes

        // Node Constructor
        public Node(K nodeKey) {
            this.key = nodeKey;
        }
    }

    //set the root to a given value
    public void setRoot(K root) {
        this.root.key = root;
    }

    public void addChild(K parentKey, K childKey) {
        if (parentKey == null || childKey == null || this.root.key == null) {
            return;
        }// when case is not valid
        else if (this.root.key == parentKey) {
            this.root.node.add(new Node(childKey)); return;
        } // root equals parentKey
        Node parent = find(this.root, parentKey);
        parent.node.add(new Node(childKey));
    }

    private Node find(Node x, K key) {
        if (x.key.equals(key)) return x;//if they are the same return else search
        for (Node c : x.node) {
            Node search = find(c, key);
            if (search != null) return search;// if it has been searched then it returns
        }
        return null;
    }

    @Override
    public String toString (){
        return toString(this.root);
    }

    private String toString(Node x) {
        String s ="";
        if(x == root) { s = "("; }
        else { s = " ("; }
        if (x.key != null) {
            s += x.key;
            if (x.node.size() != 0) { for (Node child : x.node) s += toString(child); }
        }
        return s + ")"; // formatted to answer

    }
    public GeneralTree() {
        root = new Node(null);
    }

}