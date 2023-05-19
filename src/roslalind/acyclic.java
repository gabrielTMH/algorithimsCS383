package roslalind;

import edu.princeton.cs.algs4.*;

public class acyclic {

    public static Digraph read(int V, int E, In in) {
        Digraph G = new Digraph(V + 1);
        for (int i = 0; i < E; i++) {
            int from = in.readInt();
            int to = in.readInt();

            G.addEdge(from, to);
        }
        return G;

    }

    public static void main(String[] args) {
        In in = new In("rosalind.txt");
        int i = in.readInt();
        for (int I = 0; i > I; I++) {
            int v = in.readInt();
            int e = in.readInt();
            Digraph p = read(v, e, in);
            DirectedCycle c = new DirectedCycle(p);
            if (c.hasCycle()) {
                StdOut.print("-1 ");
            } else {
                StdOut.print("1 ");

            }
        }
    }
}
