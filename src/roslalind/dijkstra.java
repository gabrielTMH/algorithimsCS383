package roslalind;
import edu.princeton.cs.algs4.*;

public class dijkstra {
    public static void main(String[] args) {
        In in = new In("rosalind.txt");
        EdgeWeightedDigraph ewd= new EdgeWeightedDigraph(in);
        DijkstraSP dsp= new DijkstraSP(ewd,1);
        for(int i =1;i<ewd.V();i++){
            if(dsp.distTo(i)!=Double.POSITIVE_INFINITY){
                StdOut.print((int)dsp.distTo(i)+" ");
            }else{
                StdOut.print(-1+" ");

            }
        }
    }
}
