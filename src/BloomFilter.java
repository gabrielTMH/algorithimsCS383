//Elaine, Gabriel and Evan
//
//public class BloomFilter<String> {
//    //array of longs must be of length 1024
//    private long[] table;
//    public int hash1(String a){return a.hashCode()&65535;}
//    public int hash2(String a){return a.hashCode()>>>16;}
//    public BloomFilter(){
//        table = new long[1024];
//    }
//    public boolean mightContainCom(String s) {
//        //feed s into the hash functions to get the array positions.
//        //if any of the bits in those positions are 0, return false
//        long x = (int)((table[hash1(s)/64]>>hash1(s)%64)&(1));
//        long y = (table[hash2(s)/64]>>hash2(s)%64)&1;
//        if(x==0||y==0){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
//    public void addCom(String s) {
//        //feed s into the hash functions to get the array positions.
//        //set all the bits at those positions to 1
//        //get our two numbers, modulo them by 64, and then we are going to divide it by 64 and floor it
//        int h1,h2;
//        int i1,i2;
//        int b1,b2;
//        h1=hash1(s);
//        h2=hash2(s);
//        i1=h1/64;
//        i2=h2/64;
//        b1=h1%64;
//        b2=h2%64;
//        table[hash1(s)/64]=table[hash1(s)/64]|(1L<<hash1(s)%64);
//        table[hash2(s)/64]=table[hash2(s)/64]|(1L<<hash2(s)%64);
//
//    }
//
//
//
//
//
//
//    public boolean mightContain(String s) {
//        //feed s into the hash functions to get the array positions.
//        //if any of the bits in those positions are 0, return false
//        int h1,h2;
//        int i1,i2;
//        int b1,b2;
//        h1=hash1(s);
//        h2=hash2(s);
//        i1=h1/64;
//        i2=h2/64;
//        b1=h1%64;
//        b2=h2%64;
//        //int a = (int)table[i1];
//        long a2=(table[i1]>>b1);
//        long g=table[i1]>>53;
//        long x = (int)(a2&(1));
//        long check =(table[i1]>>b1);
//        long y = (table[i2]>>b2)&1;
//        if(x==0||y==0){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
//    public void add(String s) {
//        //feed s into the hash functions to get the array positions.
//        //set all the bits at those positions to 1
//        //get our two numbers, modulo them by 64, and then we are going to divide it by 64 and floor it
//        int h1,h2;
//        int i1,i2;
//        int b1,b2;
//        h1=hash1(s);
//        h2=hash2(s);
//        i1=h1/64;
//        i2=h2/64;
//        b1=h1%64;
//        b2=h2%64;
//        table[i1]=table[i1]|(1L<<b1);
//        table[i2]=table[i2]|(1L<<b2);
//
//    }
//
//    public int trueBits() {
//        int bits=0;
//        for (int i=0;i<1024;i++){
//            if(table[i]!=0){
//                bits+= Long.bitCount(table[i]);
//            }
//        }
//        return bits;
//    }
//}

public class BloomFilter<k> {

    private long[] bloom;

    public BloomFilter(){
        bloom = new long[65536/64];
    }

    /**
     * returns true if any of the bits from the hashing of the object are true
     * @param s
     * @return
     */
    public boolean mightContain(k s) {
        return get(s.hashCode() << 16 >>> 16) && get(s.hashCode() >>> 16);
    }

    /**
     * Adds the given parameter to the bloom
     * @param s object to add
     */
    public void add(k s) {
        set(s.hashCode() << 16 >>> 16);
        set(s.hashCode() >>> 16);
    }

    /**
     * sets the given index [0,65536) to 1
     * @param i
     */
    private void set(int i){
        bloom[i/64] |= (0x1L << (i%64));
    }

    /**
     * returns the status for the given index [0,65536)
     * @param i
     * @return
     */
    private boolean get(int i){
        return (bloom[i/64] >>> (i%64) & 1) == 1;
    }

    /**
     * counts the number of true bits in the bloom
     * @return integer number of bits set
     */
    public int trueBits() {
        int result = 0;
        for(int i = 0; i < bloom.length; i++){
            result += Long.bitCount(bloom[i]);
        }
        return result;
    }
}
