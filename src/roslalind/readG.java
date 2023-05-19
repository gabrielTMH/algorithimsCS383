package roslalind;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class readG {
    public static Graph readGraph(String filename){
        In in = new In(filename);
        Graph g = new Graph(in.readInt());
        int edgeNum =in.readInt();
        for(int i =0; i<edgeNum; i++){
            g.addEdge(in.readInt()-1,in.readInt()-1);
        }
        return g;
    }

    public static void main(String[] args) {
        Graph g = readGraph("rosalind.txt");
        StdOut.print(g.toString());
    }
}
