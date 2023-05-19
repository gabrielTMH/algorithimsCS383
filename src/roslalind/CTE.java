//package roslalind;
//import edu.princeton.cs.algs4.*;
//
//public class CTE {
//    public static double shortcycle(EdgeWeightedDigraph g, DirectedEdge E){
//        double prev_res;
//        int to =E.to();
//        int from =E.from();
//        DijkstraSP dsp = new DijkstraSP(g,to);
//       //StdOut.print("\npath from to "+dsp.hasPath(from,to)+" \n to from"+dsp.hasPath(to,from)+"\n");
//        //StdOut.print("\npath from to "+dsp.dist(from,to)+" \n to from"+dsp.dist(to,from)+"\n");
//
//        prev_res=dsp.distTo(from)+E.weight();
//        if(prev_res!=Double.POSITIVE_INFINITY){
//            return prev_res;
//        }
//        return -1.0;
//    }
//
//    public static DirectedEdge read(int E, In in,EdgeWeightedDigraph G){
//        DirectedEdge ret = new DirectedEdge(0,0,0);
//        for(int i=0;i<E;i++) {
//            int from = in.readInt()-1;
//            int to = in.readInt()-1;
//            int weight = in.readInt();
//            DirectedEdge g = new DirectedEdge(from, to, weight);
//            if(i==0){
//                ret = g;
//            }
//            G.addEdge(g);
//        }
//        return ret;
//    }
//
//    public static void main(String[] args) {
//    In in = new In("rosalind.txt");
//    int num = in.readInt();
//    for(int i = 0; i<num;i++){
//        int gv=in.readInt();
//        int ge=in.readInt();
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(gv);
//        DirectedEdge d =read(ge,in,G);
//        double c=shortcycle(G,d);
//        StdOut.print((int)c+" ");
//    }
//    }
//}
package roslalind;
import edu.princeton.cs.algs4.*;

import edu.princeton.cs.algs4.*;


public class CTE {

    /** Simple max function to avoid importing a library */
    private static int max (int one, int two){
        if (one > two) return one;
        return two;
    }

    /** Method that checks whether a graph has a cycle */
    public static boolean allCycles(EdgeWeightedDigraph g){
        DijkstraAllPairsSP g1 = new DijkstraAllPairsSP(g);
        for (int j = 0; j < g.V(); j++)
            for (int i = 0; j < g.V(); j++)
                if (!g1.hasPath(j,i)) return false;
        return true;
    }

    /** Reading in the EdgeWeightedDigraph */
    public static EdgeWeightedDigraph[] readG(String filename) {
        In file = new In(filename);
        /** Taking notes of the number of graphs*/
        int nGraphs = file.readInt();

        /** Initializing an Array that will contain all the graphs */
        EdgeWeightedDigraph[] gArray = new EdgeWeightedDigraph[nGraphs];
        /** Creating a null graph to create a variable @ reachable scope  from function level */
        EdgeWeightedDigraph g = null;

        /** Iterating through the file, placing all the graphs in the array */
        for (int j = 0; j < nGraphs; j++) {
            /** Define the v , e for each graph */
            int vertices = file.readInt();
            int edges = file.readInt();
            g = new EdgeWeightedDigraph(vertices);
            int tempV[] = new int[(edges)], tempU[] = new int[(edges)];
            int tempW = -1;
            /** Look read each graphs */
            for (int i = 0; i < edges; i++) {
                int v = file.readInt();
                int u = file.readInt();
                double w = file.readInt();
                if (i == 0) { tempU[0] = u - 1; tempV[0] = v - 1; tempW = (int)w;}
                DirectedEdge e = new DirectedEdge(v - 1, u - 1, w);
                g.addEdge(e);
            }
            /** Adding the graph to the graph array */
            gArray[j] = g;

            /** Computing the Shortest Cycles */
            DijkstraSP g1 = new DijkstraSP(g,tempU[0]);
            if (g1.hasPathTo(tempV[0])) { System.out.print ((int) g1.distTo(tempV[0]) + tempW + " "); }
            else if (!g1.hasPathTo(tempV[0])) { System.out.print("-1 "); }
        }
        /** Return graph Array */
        return gArray;
    }



    public static void main (String[] args) {
        /** Reading Input Text into a Graph format*/
        EdgeWeightedDigraph gArray[] = readG("rosalind.txt");
        /** Reading test case */


    }
}