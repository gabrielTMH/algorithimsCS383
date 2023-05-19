

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//
//public class Bubble {
//    public static void sort(Double[] a){
//        int n = a.length-1;
//        double swap;
//        for(int i =0; i<n;i++){
//            for(int j =0; j<n-i;j++){
//                if(a[j]>a[j+1]){
//                    swap=a[j];
//                    a[j]=a[j+1];
//                    a[j+1]=swap;
//                }
//            }
//        }
//    }
//
//}


public class Bubble {

    public static void sort(Double[] b) {

        Double[] nums = b;

        for (int x = 0; x <= nums.length; x++) {
            for (int i = 0; i <= nums.length; i++) {
                if (i + 1 >= nums.length) {
                    break;
                }
                if (nums[i] > nums[i + 1]) {
                    double temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                }
            }
        }
    }
}