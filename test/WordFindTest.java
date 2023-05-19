import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordFindTest {




    @Test
    void doesfindfirstword(){
        String g = "timeouttimeout";
        String p= "ime";
        assertEquals(1, WordFind.find(g,p));
    }

    @Test
    void doesfind(){
        String g = "timeout";
        String p ="time";
        assertEquals(0, WordFind.find(g,p));
    }

    @Test
    void doesnotfind(){
        String g = "timeout";
        String p ="meot";
        assertEquals(-1, WordFind.find(g,p));
    }
}