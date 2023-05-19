import java.util.Arrays;

public class matrix {



    static double[][] mult(double a[][], double b[][]) {
        double res[][] = new double[a.length][b[0].length];
        double sum;
            for(int i =0; i<a.length;i++){
                for(int j=0; j<b[1].length;j++){
                    sum=0;
                    for(int k=0;k<b[1].length;k++) {
                        res[i][j] += a[k][i] * b[j][k];
                    }
                }
            }
///this takes order n^3
        return res;
    }
    public static void main(String[] args) {
        double a[][] =new double[3][3];
        double b[][] =new double[3][3];

        for(int i=0;i<3;i++){
            for(int j = 0;j<3;j++){
                a[i][j]=i;
                b[i][j]=j*2;
            }
        }
        double res[][]= new double[3][3];
        res = mult(a,b);
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));

        System.out.println(Arrays.deepToString(res));

    }

}
/*
* 0 1 2
* 0 1 2
* 0 1 2
*
* 0 0 0
* 2 2 2
* 4 4 4
*
*
*
*
*
* */