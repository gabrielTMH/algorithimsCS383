//public class log {
//
//    public static int log(int a, int b) {
//
//        int result =0;
//        while(b>1){
//            b=b/a;
//            result++;
//        }if(b<1){
//            result--;
//        }
//        return result;
//    }
//
//
//
//    public static void main(String[] args) {
//        System.out.println(log(3,8));
//    }
//}


import java.util.Scanner;

public class log {

    // function to calculate how many times you divide (b / a) to get to 1
    // can't use any methods from math package

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number b:");
        int b = scan.nextInt();
        System.out.println("Enter a base number a:");
        int a = scan.nextInt();

        System.out.println(log(a, b));
    }

    public static int log(int a, int b) {
        int result = 0;
        while(b >= 1) {
            b = b / a;
            result++;
        }
        return result - 1;
    }
}