package geryy;

import edu.princeton.cs.algs4.*;

public class gm implements Gerrymanderer{
    public int[][] result;
    public int i;
    public boolean [] voters;
    public boolean[][] taken;
    @Override
    public int[][] gerrymander(Electorate e, boolean party) {
        int d = e.getNumberOfDistricts();
        result = new int[d][d];
        voters=e.getVoters();
        i = 0;
        //we need districts to be the same size and connected
        //once a node is added to a district, assign it a number that is shared by all the nodes in said district
        //we can use this number to also check if a neighbor exists or is available
        //goal is to have d/2+1 of party in at least d/2+1 districts
        //gerry method will assign nodes the number, and loop will check the number
        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {
                if (party) {
                    if(result[x][y]==0){
                        //we can make new district
                        gerry(party,x,y,d,e);
                    }
                } else {
                    if(result[x][y]==0){
                        //we can make new district
                        //same code as above but we're calling gerry of !party
                        gerry(!party,x,y,d,e);
                    }
                }
            }
        }
        return result;
    }


    public boolean nonisolated(Graph g,int[][] result){
        int d = (int)Math.sqrt(g.V());
        boolean[][] visited = new boolean[d][d];
        //for every unmakered node check to see if it can get x%d=0 vertexes
        for(int i =0; i<g.V();i++){
            int x = i / d;
            int y = i % d;
            if(result[x][y]!=0) {
                DepthFirstSearch dfs = new DepthFirstSearch(g, i);
                if(dfs.count()%d!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public Graph removeDistrict(Electorate e, int[][] result){
        Graph g= new Graph(e.getGraph().V());
        //V=d*d
        int d= (int)Math.sqrt(g.V());
        //go through every vertex and say okay is it allready in a district
        // if not add its neighbors that arnt in districts
       for(int i =0 ; i < g.V();i++){
           int x = i / d;
           int y = i % d;
           if(result[x][y]!=0) {
               if (x < d - 1) {
                   // Add neighbor to east
                   if(result[x+1][y]!=0) {
                       g.addEdge(i, (x + 1) * d + y);
                   }
               }
               if (y < d - 1) {
                   // Add neighbor to north
                   if(result[x][y+1]!=0) {
                       g.addEdge(i, x * d + y + 1);
                   }
               }
           }
       }
        return g;
    }

    public void gerry(boolean party, int x, int y, int d,Electorate e) {
        //if i > d/2 then just do whatever
        int goal = d/2+1;
        //have we reached our goal number of voters
        int goaliter=0;
        //
        int filliter=0;
        if(!taken[x][y]) {
            result[x][y]=i;
            if (voters[dim(x,y,d)]){
                goaliter++;
                filliter++;
            }
//connected components depth first search
            //make a copy of the graph that does not include districts
            //or we make a graph class of our own that allows us to remove edges
            //we should check to see if bags can be removed and if they cant we would check to see if
            //we cant use bag then removededgegraph class could use an array of lists.

            if (x > 0) {
                //check
                //if check succeeds, do the following
                if (voters[dim(x-1,y,d)]) {
                    
                }
            }
            if (x < d - 2) {
                //check
            }
            if (y > 0) {
                //check
            }
            if (y < d - 2) {
                //check

            }
        }
        //check if neighbors are available
        //we need to check the party of the neighbors
        //goal is to get a majority in a majority of districts
        //check if i is above d/2, then don't worry.
        //watch out for making an impossible district. don't isolate nodes.
        //we will use transitive closure ?? to determine if nodes can reach d-1 other nodes, so they aren't isolated
        //we'll do the above for each neighbor node of the one's we've marked
        //check if the remaining nodes are a multiple of d
    }

    public int dim(int x, int y,int d){
        return x*d+y%d;
    }
    public boolean isoStop(boolean party, int x, int y, int d,Electorate e){
        //going through neighbors of the one's we're assigning
        //if taken, then skip, if !taken create graph
       Graph g = e.getGraph();
       return false;

    }
}
