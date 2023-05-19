package roslalind;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Deg {


    public static Graph readG(String filename){
        In in = new In(filename);
        Graph g = new Graph(in.readInt());
        for(int i =0; i<in.readInt(); i++){
            g.addEdge(in.readInt()-1,in.readInt()-1);
        }
        return g;
    }

    public static void main(String[] args) {
        In in = new In("rosalind_deg.txt");
        int V = in.readInt();
        int e = in.readInt();
        int[] degrees = new int[V];
        for(int i = 0; i < e; i++){
            int p = in.readInt()-1;
            int q=in.readInt()-1;
            degrees[p]++;
            degrees[q]++;
        }
        for (int i = 0; i < V; i++) {
           // System.out.println(degrees[i]+" ");
            StdOut.print(degrees[i]+" ");
        }
    }

}
