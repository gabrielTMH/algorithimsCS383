package roslalind;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

public class twosat {
    public static Digraph read(int V, int E, In in) {
        Digraph G = new Digraph(V*2+1);
        for (int i = 0; i < E; i++) {
            int from = in.readInt()+V;
            int to = in.readInt()+V;
            int nfrom=(V*2-from);
            int  nto=(V*2-to);
              //nfrom/2=v-from->

            //i need to add from to negation og to and negation of from to to
            G.addEdge(nfrom, to);
            G.addEdge(nto, from);
        }
        return G;
    }

    public static void main(String[] args) {
        In in = new In("rosalind.txt");
        int i = in.readInt();
        for(int j =0;j<i;j++) {
            int v = in.readInt();
            int e = in.readInt();
            Digraph g = read(v, e, in);
          // StdOut.print(g.toString());
            boolean sat = true;
            KosarajuSharirSCC KS= new KosarajuSharirSCC(g);
            for(int t=0;t<v;t++){
               if(KS.stronglyConnected(t,2*v-t)){
                   sat=false;
                   break;
               }
            }
            StdOut.print(sat+"\n");
        }
    }

}
