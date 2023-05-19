public class expo {
    public static int ex(int a, int b) {
        int result = 1;
        for( int i = 0 ; i <b; i++){
            result = result*a;
        }
      return result;
    }

    public static int exDiv(int a , int b){


        if( b==0){
            return 1;
        }else
        if(b%2==0){
          int g =  exDiv(a,b/2);
          return g*g;
        }else{
            int o =exDiv(a,(b-1)/2);
            return o*o*a;
        }

    }

    public static void main(String[] args) {

        System.out.println();
    }

}
