public class BitVector {
    private int data;

    Boolean get(int i){
        return (data >>> i)%2 == 1;
        //
    }
    Boolean get1(int i){
        return( (data >>>i) &1)!=0;
        //the result will either be one or zero because using & with 1 will be represented as 00000001
    }

    public void set(int i) {
        data = data | (1<<i);
    }
    public void deSet(int i){
        data= data & (254<<i);
    }
    public void unset(int i){
        data = data & ~(1<<i);
    }



}
