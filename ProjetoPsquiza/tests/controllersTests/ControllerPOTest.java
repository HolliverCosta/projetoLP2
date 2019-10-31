package controllersTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetolp2.usecase3.POController;

class POControllerTest {

    POController controller = new POController();
    @BeforeEach
    void inicia() {
        String descricao = "minha descricao";
        controller.cadastraObjetivo("GERAL", descricao, 1, 3);
        controller.cadastraObjetivo("GERAL", descricao, 2, 2);
        controller.cadastraObjetivo("ESPECIFICO", descricao, 4, 4);
        controller.cadastraObjetivo("ESPECIFICO", descricao, 5, 5);
        controller.cadastraProblema(descricao, 3);
        controller.cadastraProblema(descricao, 1);
        controller.cadastraProblema(descricao, 5);
        controller.cadastraProblema(descricao, 5);
    }
    
    @Test
    void testCadastraProblema() {
        String descricaoValida = "descricao valida";
        String descricaoVazia = "";
        String descricaoNula = null;
        int viabilidadeInvalida1 = -2;
        int viabilidadeInvalida2 = 6;
        int viabilidadeInvalida3 = 0;
        int viabilidadeValida = 3;
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controller.cadastraProblema(descricaoVazia, viabilidadeValida);});
        Assertions.assertThrows(NullPointerException.class, () -> {controller.cadastraProblema(descricaoNula, viabilidadeValida);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controller.cadastraProblema(descricaoValida, viabilidadeInvalida1);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controller.cadastraProblema(descricaoValida, viabilidadeInvalida2);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controller.cadastraProblema(descricaoValida, viabilidadeInvalida3);});
    }
    
    @Test
    void testCadastraObjetivo() {
        String tipoValido1 = "GERAL";
        String tipoValido2 = "ESPECIFICO";
        String tipoInvalido1 = "FESTA";
        String tipoInvalido2 = "SEILA";
        String tipoVazio = "";
        String tipoNulo = null;
        String descricaoVazia = "";
        String descricaoNula = null;
        String descricaoValida = "descricao valida";
        int aderenciaValida = 3;
        int viabilidadeValida = 3;
        int viabilidadeInvalida1 = 0;
        int viabilidadeInvalida2 = 6;
        int aderenciaInvalida1 = 0;
        int aderenciaInvalida2 = 6;
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoVazio, descricaoValida, aderenciaValida, viabilidadeValida);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoValido1, descricaoVazia, aderenciaValida, viabilidadeValida);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.cadastraObjetivo(tipoNulo, descricaoValida, aderenciaValida, viabilidadeValida);
        });
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.cadastraObjetivo(tipoValido2, descricaoNula, aderenciaValida, viabilidadeValida);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoValido1, descricaoValida, aderenciaInvalida1, viabilidadeValida);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoValido1, descricaoValida, aderenciaInvalida2, viabilidadeValida);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoValido1, descricaoValida, aderenciaValida, viabilidadeInvalida1);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoValido1, descricaoValida, aderenciaValida, viabilidadeInvalida2);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoInvalido1, descricaoValida, aderenciaValida, viabilidadeValida);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.cadastraObjetivo(tipoInvalido2, descricaoValida, aderenciaValida, viabilidadeValida);
        });
        
    }
    
    @Test
    void testExibeProblema() {
        String codigoVazio = "";
        String codigoNulo = null;
        String codigoInvalido1 = "P40";
        String codigoInvalido2 = "p3";
        String codigoInvalido3 = "A2";
        String codigoValido1 = "P1";
        String codigoValido2 = "P2";
        String codigoValido3 = "P3";
        String codigoValido4 = "P4";
        
        assertEquals("P1 - minha descricao - 3", controller.exibeProblema(codigoValido1));
        assertEquals("P2 - minha descricao - 1", controller.exibeProblema(codigoValido2));
        assertEquals("P3 - minha descricao - 5", controller.exibeProblema(codigoValido3));
        assertEquals("P4 - minha descricao - 5", controller.exibeProblema(codigoValido4));
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeProblema(codigoInvalido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeProblema(codigoInvalido2);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeProblema(codigoInvalido3);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeProblema(codigoVazio);});
        Assertions.assertThrows(NullPointerException.class,() -> {controller.exibeProblema(codigoNulo);});
    }
    
    @Test
    void testExibeObjetivo() {
        String codigoVazio = "";
        String codigoNulo = null;
        String codigoInvalido1 = "O40";
        String codigoInvalido2 = "o3";
        String codigoInvalido3 = "A2";
        String codigoValido1 = "O1";
        String codigoValido2 = "O2";
        String codigoValido3 = "O3";
        String codigoValido4 = "O4";
        
        assertEquals("O1 - GERAL - minha descricao - 4", controller.exibeObjetivo(codigoValido1));
        assertEquals("O2 - GERAL - minha descricao - 4", controller.exibeObjetivo(codigoValido2));
        assertEquals("O3 - ESPECIFICO - minha descricao - 8", controller.exibeObjetivo(codigoValido3));
        assertEquals("O4 - ESPECIFICO - minha descricao - 10", controller.exibeObjetivo(codigoValido4));
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeObjetivo(codigoInvalido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeObjetivo(codigoInvalido2);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeObjetivo(codigoInvalido3);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeObjetivo(codigoVazio);});
        Assertions.assertThrows(NullPointerException.class,() -> {controller.exibeObjetivo(codigoNulo);});   
    }
    
    @Test
    void testApagaProblema() {
        String codigoVazio = "";
        String codigoNulo = null;
        String codigoInvalido1 = "P40";
        String codigoInvalido2 = "p3";
        String codigoInvalido3 = "A2";
        String codigoValido1 = "P1";
        String codigoValido2 = "P4";
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarProblema(codigoInvalido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarProblema(codigoInvalido2);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarProblema(codigoInvalido3);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarProblema(codigoVazio);});
        Assertions.assertThrows(NullPointerException.class,() -> {controller.apagarProblema(codigoNulo);});
        
        controller.apagarProblema(codigoValido1);
        controller.apagarProblema(codigoValido2);
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarProblema(codigoValido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarProblema(codigoValido2);});
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeProblema(codigoValido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeProblema(codigoValido2);});
    }
    
    @Test
    void testApagaObjetivo() {
        String codigoVazio = "";
        String codigoNulo = null;
        String codigoInvalido1 = "O40";
        String codigoInvalido2 = "o3";
        String codigoInvalido3 = "A2";
        String codigoValido1 = "O1";
        String codigoValido2 = "O4";
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarObjetivo(codigoInvalido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarObjetivo(codigoInvalido2);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarObjetivo(codigoInvalido3);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarObjetivo(codigoVazio);});
        Assertions.assertThrows(NullPointerException.class,() -> {controller.apagarObjetivo(codigoNulo);});
        
        controller.apagarObjetivo(codigoValido1);
        controller.apagarObjetivo(codigoValido2);
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarObjetivo(codigoValido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.apagarObjetivo(codigoValido2);});
        
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeObjetivo(codigoValido1);});
        Assertions.assertThrows(IllegalArgumentException.class,() -> {controller.exibeObjetivo(codigoValido2);});
    }
}
