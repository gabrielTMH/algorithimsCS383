package roslalind;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class topo {

    public static void main(String[] args) {
        In in = new In("rosalind_ts.txt");
        Digraph g = new Digraph(in);
        Topological top = new Topological(g);
        for(int i :top.order()){
            if(i!=0) {
                StdOut.print(" " + i);
            }
        }
    }
}
