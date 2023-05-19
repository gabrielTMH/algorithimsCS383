package roslalind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
public class deg2 {

    public static void main(String[] args) {
        In in = new In("rosalind1.txt");
        Graph g = new Graph(in);
      //  StdOut.print(g.V());
        int[] res=new int[g.V()];
        //for every vertice in the graph compute the sum of their edges
        for(int i = 0; i < g.V(); i++){
            for( int a:  g.adj(i)){
                // res[i]+=g.adj(a).size();
                res[i]+=g.degree(a);
            }
        }
        for (int i = 1; i < g.V(); i++) {
            // System.out.println(degrees[i]+" ");
            StdOut.print(res[i]+" ");
        }


    }

}
