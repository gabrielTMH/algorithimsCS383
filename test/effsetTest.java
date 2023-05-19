import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class effsetTest {
    @Test
    void DoesAdd(){
        effset a = new effset();
        a.add(61);
        assertTrue(a.contains(61));
    }

    @Test
    void DoesRemove(){
        effset a = new effset();
        a.add(61);
        a.remove(61);
        assertFalse(a.contains(61));
    }

    @Test
    void DoesRecognizeEmpty(){
        effset a = new effset();
        a.add(61);
        a.remove(61);
        assertTrue(a.isEmpty());
    }
    @Test
    void DoesRecognizeIsNotEmpty(){
        effset a = new effset();
        a.add(61);
        assertFalse(a.isEmpty());
    }


}