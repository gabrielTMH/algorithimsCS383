import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitVectorTest {


        @Test
        void initialBitIsOff(){
            BitVector v= new BitVector();
            assertFalse(v.get(7));
        }
        @Test
    void sestBit(){
            BitVector v = new BitVector();
            v.set(4);
            assertTrue(v.get(4));
        }
        @Test
        void deSetBit(){
            BitVector v = new BitVector();
            v.set(4);
            v.deSet(4);
            assertFalse(v.get(4));
        }
        @Test
        void allfuncs(){
            BitVector v = new BitVector();
            v.set(8);
            assertTrue(v.get(8));

            v.deSet(8);
            assertFalse(v.get(8));
        }
        @Test
        void unset(){
            BitVector v =new BitVector();
            v.unset(3);
            assertFalse(v.get(3));
        }
    @Test
    void unset2(){
        BitVector v =new BitVector();
        v.set(3);
        v.unset(3);
        assertFalse(v.get(3));
    }
}