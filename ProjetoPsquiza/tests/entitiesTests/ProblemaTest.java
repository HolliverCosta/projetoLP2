package entitiesTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import projetolp2.po.Problema;

class ProblemaTest {
    
    
    Problema prob1 = new Problema("problema 1", 2, "P1");
    Problema prob2 = new Problema("problema 2", 1, "P2");
    Problema prob3 = new Problema("problema 3", 5, "P3");
    @Test
    void testCampos() {
        
        assertEquals(2,prob1.getViabilidade());
        assertEquals(1,prob2.getViabilidade());
        assertEquals(5,prob3.getViabilidade());
        
        assertEquals("problema 1",prob1.getDescricao());
        assertEquals("problema 2",prob2.getDescricao());
        assertEquals("problema 3",prob3.getDescricao());
        
        assertEquals("P1",prob1.getId());
        assertEquals("P2",prob2.getId());
        assertEquals("P3",prob3.getId());
    }
    
    @Test
    void testEquals() {
        Problema prob4 = new Problema("problema 1", 2,  "P1");
        Problema prob5 = new Problema("Problema 2", 1, "P2");
        
        assertFalse(prob1.equals(prob2));
        assertFalse(prob1.equals(prob3));
        assertFalse(prob2.equals(prob3));
        
        assertTrue(prob1.equals(prob4));
        
        assertFalse(prob2.equals(prob5));
    }
    
    @Test
    void testToString() {
        assertEquals("P1 - problema 1 - 2",prob1.toString());
        assertEquals("P2 - problema 2 - 1",prob2.toString());
        assertEquals("P3 - problema 3 - 5",prob3.toString());
    }
    
    @Test
    void testViabilidadeInvalida() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Problema("problema", -1, "P5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Problema("problema", 6, "P5"));
    }

}
