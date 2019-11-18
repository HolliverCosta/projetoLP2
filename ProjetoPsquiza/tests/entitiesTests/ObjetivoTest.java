package entitiesTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import projetolp2.po.Objetivo;

class ObjetivoTest {

    Objetivo obj1 = new Objetivo("GERAL", "objetivo 1", 1, 5, "O1");
    Objetivo obj2 = new Objetivo("GERAL", "objetivo 2", 4, 4, "O2");
    Objetivo obj3 = new Objetivo("ESPECIFICO", "objetivo 3", 2, 1, "O3");

    @Test
    void testCampos() {
        assertEquals("GERAL",obj1.getTipo());
        assertEquals("GERAL",obj2.getTipo());
        assertEquals("ESPECIFICO",obj3.getTipo());

        assertEquals("objetivo 1", obj1.getDescricao());
        assertEquals("objetivo 2", obj2.getDescricao());
        assertEquals("objetivo 3", obj3.getDescricao());

        assertEquals(1, obj1.getAderencia());
        assertEquals(4, obj2.getAderencia());
        assertEquals(2, obj3.getAderencia());

        assertEquals(5,obj1.getViabilidade());
        assertEquals(4,obj2.getViabilidade());
        assertEquals(1,obj3.getViabilidade());

        assertEquals("O1",obj1.getId());
        assertEquals("O2",obj2.getId());
        assertEquals("O3",obj3.getId());
    }

    @Test
    void testEquals() {
        Objetivo obj4 = new Objetivo("GERAL","objetivo 1", 1, 5, "O1");
        Objetivo obj5 = new Objetivo("ESPECIFICO", "Objetivo 3", 2, 1, "O3");

        assertFalse(obj1.equals(obj2));
        assertFalse(obj1.equals(obj3));
        assertFalse(obj2.equals(obj3));
        assertTrue(obj1.equals(obj4));
        assertFalse(obj3.equals(obj5));
    }

    @Test
    void testToString() {
        assertEquals("O1 - GERAL - objetivo 1 - 6",obj1.toString());
        assertEquals("O2 - GERAL - objetivo 2 - 8",obj2.toString());
        assertEquals("O3 - ESPECIFICO - objetivo 3 - 3",obj3.toString());
    }

    @Test
    void testCamposInvalidos(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Objetivo("JOAO", "objetivo", 2, 2, "O5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Objetivo("Topico", "objetivo", 2, 2, "O5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Objetivo("ESPECIFICO", "objetivo", -2, 2, "O5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Objetivo("GERAL", "objetivo", 0, 2, "O5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Objetivo("GERAL", "objetivo", 2, 0, "O5"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Objetivo("ESPECIFICO", "objetivo", 6, 2, "O5"));
    }
}
