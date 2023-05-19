package roslalind;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.CC;

public class CCme {

    public static void main(String[] args) {
        In in = new In("rosalind1.txt");
        Graph g = new Graph(in);
        CC c = new CC(g);
        StdOut.print("" + c.count());
    }
}
