public class BinaryTree {

    class Node{
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        private Node root;
    }

   // public BinaryTree() {
    //    root = new Node(3, null, new Node(5));
  //  }

    public static void main(String[] args) {
        BinaryTree t=new BinaryTree();
    }
}
