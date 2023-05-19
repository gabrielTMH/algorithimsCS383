package roslalind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;


public class bfs {
    public static void main(String[] args) {
        In in = new In("rosalind1.txt");
        Digraph dg= new Digraph(in);
        BreadthFirstDirectedPaths bfd = new BreadthFirstDirectedPaths(dg,1);
        for(int i =1;i<dg.V();i++){
            if(bfd.distTo(i)==Integer.MAX_VALUE){
                StdOut.print(-1+" ");
            }else{
                StdOut.print(bfd.distTo(i)+" ");

            }
        }
    }

}
