package roslalind;

import edu.princeton.cs.algs4.*;

//public class hampath {
//    public static Digraph read(int V, int E, In in) {
//        Digraph G = new Digraph(V);
//        for (int i = 0; i < E; i++) {
//            int from = in.readInt()-1;
//            int to = in.readInt()-1;
//
//            G.addEdge(from, to);
//        }
//        return G;
//
//    }
//
//
//
//    public static void main(String[] args) {
//        In in = new In("rosalind.txt");
//        int i = in.readInt();
//        int key=0;
//        for(int j =0;j<i;j++){
//            int v = in.readInt();
//            int e = in.readInt();
//            Digraph g = read(v,e,in);
//            Topological top = new Topological(g);
//            for(int d :top.order()){
//              //DepthFirstDirectedPaths dfdp = new DepthFirstDirectedPaths(g,d);
//                    for(int c : g.adj(d)){
//                        if(top.rank(c)==top.rank(d)+1){
//                            key++;
//                            break;
//                        }
//                    }
//
//            }
//           if(key+1==v){
//               StdOut.print("\n"+1+" ");
//                for(int d: top.order()){
//                    StdOut.print(d+1+" ");
//                }
//           }else{
//               StdOut.print("\n"+-1+" ");
//           }
//        }
//
//    }
//}



public class hampath {
    private static Iterable<Integer> order;
    private static int[] rank;
    private static boolean[] marked;
    private static int[] edgeTo;
    private static int[] distTo;

    public hampath(Digraph G){
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    public static boolean hasPathTo(int v){
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public static void validateVertex(int v){
        int i = distTo.length;
        if (v < 0 || v >= i){
            throw new IllegalArgumentException("vertex " + v + "is not between 0 and " + (i -1));
        }
    }

    public static boolean isConnected(Digraph g, int v1, int v2){
        boolean check = false;
        for (int n : g.adj(v1)){
            if (n == v2){
                check = true;
            }
            n = n+1;
        }
        return check;
    }

    public static Digraph readG(In file) {
        int vertices = file.readInt();
        int edges = file.readInt();
        Digraph graph = new Digraph(vertices);
        marked = new boolean[vertices];
        edgeTo = new int[vertices];
        distTo = new int[vertices];
        for (int i = 0; i < edges; i++){
            int v1 = file.readInt();
            int v2 = file.readInt();
            graph.addEdge(v1-1, v2-1);
        }
        return graph;
    }

    public static void main(String[] args) {
        In file = new In("rosalind1.txt");
        int numgraphs = file.readInt();
        for (int i =0; i < numgraphs; i++){
            Digraph g = readG(file);
            hampath top = new hampath(g);
            int p = -1;
            int check = 0;
            for (int n : top.order){
                if (p != -1){
                    if(isConnected(g, p, n)){
                        check = 1;
                    } else {
                        check = -1;
                        break;
                    }
                }
                p = n;
            }
            if (check == 1){
                StdOut.print(check + " ");
                for (int n : top.order){
                    StdOut.print(n+1 + " ");
                }
            }
            else if (check == -1){
                StdOut.print(check);
            }
            StdOut.print("\n");
        }
    }
}