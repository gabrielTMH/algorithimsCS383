package geryy;

public class gm1 {

    public int[] partyCount(Boolean[] voters){
        int count=0;
        int[] res=new int[2];
        for(int i=0;i<voters.length;i++ ){
            if(voters[i]){
                count++;
            }
        }
        res[0]=count;
        res[1]=voters.length-count;
        return res;
    }


/*
so we find the number of voters for each party

 */

    //@Override
    public int[][] gerrymander(Electorate e, boolean party) {
        int d = e.getNumberOfDistricts();
        int[][] result = new int[d][d];
        int i = 0;
        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {
                if (party) {
                    result[x][y] = i;
                } else {
                    result[y][x] = i;
                }
                i++;
            }
        }
        return result;
    }

}
