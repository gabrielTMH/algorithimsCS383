package roslalind;
import edu.princeton.cs.algs4.*;

public class strong {
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
        int v = in.readInt();
        int e = in.readInt();
        Digraph g = read(v,e,in);
        TarjanSCC T = new TarjanSCC(g);
        StdOut.print(T.count()-1);
    }

}
