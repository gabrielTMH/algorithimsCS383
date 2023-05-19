////authors: Gabriel, ELain, Evan
//
//public class FingerprintMatcher implements Matcher{
//
//    public boolean equals(int[][]a,int[][]b){
//        for(int i=0;i<a.length;i++)
//        {
//            for(int j=0;j< a.length;j++){
//                if(a[i][j]!=b[i][j]){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public int[][] copy(int[][]n,int i,int j,int m){
//        int [][] a = new int[m][m];
//        for(int k=0; k<m;k++){
//            for(int l=0; l<m;l++){
//                a[k][l]=n[i+k][j+l];
//            }
//        }
//        return a;
//    }
//
//    @Override
//    public int[] match(int[][] pattern ,int[][] text){
//        int[] c = new int[2];
//        int goodCode= pc(pattern,pattern.length);
//        int textcode;//used to initialize as a pc
//        for(int row =0;row<=text.length-pattern.length;row++){
//            textcode=pc(copy(text,row,0,pattern.length),pattern.length);
//            for(int col =0;col<=text.length-pattern.length;col++){
//                if(goodCode==textcode  && equals(copy(text,row,col,pattern.length),pattern)){
//                        c[0]=row;
//                        c[1]=col;
//                        return c;
//                }
//                if(col<text.length-pattern.length) {
//                    textcode = xor(textcode, text, row, col, pattern.length);
//                }
//            }
//            ///recompute for new row
//        }
//        return null;
//    }
//
//    public static int xor(int code,int[][] text, int row, int col, int leng ){
//        int res = 0;
//        for(int i=0;i<leng;i++){
//            res = res^ text[row+i][col] ^ text[row+i][col+leng];
//        }
//        res=res^code;
//        return res;
//    }
//
//    public static int pc(int a[][],int leng){
//        int res=0;
//        for(int i = 0; i<leng;i++){
//            for(int j =0; j <leng;j++){
//                res = res ^ a[i][j];
//            }
//        }
//        return res;
//    }
//
//
//
///*
//    public int[][] copyarray(int[][] a, int cp, int rp, int l){
//        int[][] na = new int[l][l];
//        for(int i =0; i<l;i++){
//            for(int j = 0; j<l;j++){
//                na[i][j]=a[cp+i][rp+j];
//            }
//        }
//        return na;
//    }
//
//    public int[][] previousBox(int[][] a,int i,int j ,int m ){
//        int[][] res = new int[m][m];
//        if(i==0 && j!=0){
//            res = copyarray(a, a.length-1,j-1, m );
//        }else if(i==0 && j==0){
//            return null;
//        }
//        return res;
//    }*/
//
//    //public static int nc(a[][],)
//
//    public static void main(String[] args) {
//        String x = "jam";
//        String d = "jaming";
//        int p = x.hashCode();
//        int m = d.hashCode();
//        int[][] a = new int[2][3];
//        a[0][0]=900;
//        a[0][1]=100;
//        a[0][2]=90;
//        a[1][0]=1;
//        a[1][1]=200;
//        a[1][2]=0;
//      //    int pa = FingerprintMatcher.pc(a);
//       // System.out.println(pa );
//
////250421012
////250421012
//        //3639276
//        //247
//    }
//}




import edu.princeton.cs.algs4.StdOut;



public class FingerprintMatcher implements Matcher {

    public FingerprintMatcher(){
    }

    public int computePatternHash(int[][] pattern){
        int hash = 0;
        for (int i=0; i < pattern.length; i++){
            for(int j=0; j < pattern.length; j++){
                hash = hash + pattern[i][j];
            }
        }
        return hash;
    }

    public int computeTextHash(int[][] text, int ii, int jj, int len){
        int hash = 0;
        for (int i=0; i < len; i++){
            for(int j=0; j < len; j++){
                hash = hash + text[ii+i][jj+j];
            }
        }
        return hash;
    }

    public int updateHash(int oldHash, int[][] text, int i, int j, int m){
//        int newHash = oldHash;
        for (int ii=0; ii < m; ii++){
            oldHash -= text[i+ii][j];    // subtracting
            oldHash += text[i+ii][j+m];  // adding
        }
        return oldHash;
    }

    private boolean found(int[][] pattern, int[][] text, int i, int j) {
        for (int ii = 0; ii < pattern.length; ii++) {
            for (int jj = 0; jj < pattern.length; jj++) {
                if (pattern[ii][jj] != text[i + ii][j + jj])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int[] match(int[][] pattern, int[][] text) {
        int hash = computePatternHash(pattern);
        for (int i=0; i < text.length - pattern.length + 1; i++){
            int textHash = 0;
            for(int j=0; j < text.length - pattern.length + 1; j++){
                if (j == 0) textHash = computeTextHash(text, i, j, pattern.length);
                else        textHash = updateHash(textHash, text, i, j - 1, pattern.length);
                if (hash == textHash){
                    if (found(pattern, text, i, j)){
                        return new int[] {i, j};
                    }
                }
            }
        }
        return null;
    }

    static public void main(String[] unused) {
        int[][] pattern = { { 0xf, 0xf0 }, { 0xf00, 0xf000} };
        int[][] text = { { 0xf, 0xf0, 0x0 }, { 0xf00, 0xf000, 0}, { 0xf0000, 0xf00000, 0x0 } };
        FingerprintMatcher matcher = new FingerprintMatcher();
        StdOut.printf("%08x\n", matcher.computePatternHash(pattern));
        StdOut.printf("%08x\n", matcher.computeTextHash(text, 0, 0, 2));
        StdOut.printf("%08x\n", matcher.computeTextHash(text, 0, 1, 2));
        StdOut.printf("%08x\n", matcher.computeTextHash(text, 1, 0, 2));
        StdOut.printf("%08x\n", matcher.computeTextHash(text, 1, 1, 2));
        int hash = matcher.computeTextHash(text, 0, 0, 2);
        StdOut.printf("%08x\n", matcher.updateHash(hash, text, 0, 0, 2));
    }
}