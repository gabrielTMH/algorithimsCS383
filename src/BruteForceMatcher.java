//authors: Gabriel, ELain, Evan
public class BruteForceMatcher implements Matcher{

    //returns array [i,j] where i is the row and j is the column
    public int[] match(int[][] pattern, int[][] text) {
        int m = pattern.length;
        int n = text.length;
        int [] result=new int[2];

        for(int i=0;i<=n-m;i++){
            for(int j=0;j<=n-m;j++){
                int[][] temp = copy(text,i,j,m);
                if(equals(temp,pattern)){
                    result[0]=i; result[1]=j;
                    return result;
                }
            }
        }
        return null;
    }

    public int[][] copy(int[][]n,int i,int j,int m){
        int [][] a = new int[m][m];
        for(int k=0; k<m;k++){
            for(int l=0; l<m;l++){
                a[k][l]=n[i+k][j+l];
            }
        }
        return a;
    }

    public boolean equals(int[][]a,int[][]b){
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j< a.length;j++){
                if(a[i][j]!=b[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}