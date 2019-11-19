package controllersTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projetolp2.atividades.ControllerAtividade;

class ControllerAtividadeTest {

    private ControllerAtividade controllerAtividade;

    @BeforeEach
    public void criaControllerAtividade() {
        controllerAtividade = new ControllerAtividade();
        controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte");
    	controllerAtividade.cadastrarAtividadePesquisa("Atividade 02", "ALTO", "Risco de morte");
        controllerAtividade.cadastrarAtividadePesquisa("Atividade 03", "ALTO", "Risco de morte");
        controllerAtividade.cadastrarAtividadePesquisa("Atividade 04", "MEDIO", "Poderá ocorrer algumas doenças fatais");
        controllerAtividade.cadastrarAtividadePesquisa("Atividade 05", "ALTO", "Risco de morte");
        controllerAtividade.cadastrarAtividadePesquisa("Atividade 06", "BAIXO", "Não envolve mortes");
        controllerAtividade.cadastrarAtividadePesquisa("Atividade 07", "MEDIO", "Poderá ocorrer algumas doenças fatais");
    }

//----------------------------------------------------------------------------------------------------------------//    
    @Test
    void testCadastrarAtividadePesquisa() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarAtividadePesquisa("", "BAIXO", "Não envolve morte");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarAtividadePesquisa(null, "BAIXO", "Não envolve morte");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarAtividadePesquisa("A1", "", "Não envolve morte");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarAtividadePesquisa("A1", null, "Não envolve morte");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarAtividadePesquisa("A1", "BAIXO", "");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarAtividadePesquisa("A1", "BAIXO", null);});
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)", controllerAtividade.exibirAtividade("A1"));
    }

    @Test
    void testApagarAtividade() {
    	criaControllerAtividade();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.apagarAtividade("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.apagarAtividade(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.apagarAtividade("A8");});
        
        controllerAtividade.apagarAtividade("A2");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade("A2");});
    }

    @Test
    void testCadastrarItem() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("","Monitoramento Facebook/Messenger");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem(null,"Monitoramento Facebook/Messenger");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("A1","");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("A1",null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("A8","Monitoramento Facebook/Messenger");});
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals("Atividade 01 (BAIXO - Não envolve morte) | PENDENTE - Monitoramento Facebook/Messenger",controllerAtividade.exibirAtividade("A1"));
    }

    @Test
    void testExibirAtividadeCorreto() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade("A8");});
        
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)", controllerAtividade.exibirAtividade("A1"));
        assertEquals("Atividade 02 (ALTO - Risco de morte)", controllerAtividade.exibirAtividade("A2"));
    }
    
    @Test
    void testContarItensPendentes() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensPendentes("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensPendentes(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensPendentes("A8");}); 
        assertEquals(0, controllerAtividade.contarItensPendentes("A1"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(1, controllerAtividade.contarItensPendentes("A1"));
    }

    @Test
    void testContarItensRealizados() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensRealizados("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensRealizados(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensRealizados("A8");});
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(1, controllerAtividade.contarItensPendentes("A1"));
        assertEquals(0, controllerAtividade.contarItensRealizados("A1"));
        controllerAtividade.finalizaStatusItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(0, controllerAtividade.contarItensPendentes("A1"));
        assertEquals(1, controllerAtividade.contarItensRealizados("A1"));
    }
    //-----------------------------------------------------------------US9-----------------------------------------------------------------------------//
    @Test
    void testDefinirProximaAtividade() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.definirProximaAtividade("","A1");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.definirProximaAtividade(null,"A1");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.definirProximaAtividade("A1","");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.definirProximaAtividade("A1",null);});
        controllerAtividade.definirProximaAtividade("A1", "A4");
        controllerAtividade.definirProximaAtividade("A4", "A3");
        controllerAtividade.definirProximaAtividade("A3", "A5");
        controllerAtividade.definirProximaAtividade("A6", "A2");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.definirProximaAtividade("A2","A6");});
        controllerAtividade.definirProximaAtividade("A2", "A7");
        assertEquals("A4",controllerAtividade.getAtividade("A1").getIdSubsequente());
        assertEquals("A3",controllerAtividade.getAtividade("A4").getIdSubsequente());
    }
    @Test
    void testContaProximas() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarProximas("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarProximas(null);});  	
        controllerAtividade.definirProximaAtividade("A1", "A4");
        controllerAtividade.definirProximaAtividade("A4", "A3");
        controllerAtividade.definirProximaAtividade("A3", "A5");
        controllerAtividade.definirProximaAtividade("A6", "A2");
        controllerAtividade.definirProximaAtividade("A2", "A7");
        assertEquals(3,controllerAtividade.contarProximas("A1"));
        assertEquals(1,controllerAtividade.contarProximas("A2"));
    }
    
    @Test
    void testTirarProximaAtividade() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.tirarProximaAtividade("");});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.tirarProximaAtividade(null);});
        controllerAtividade.definirProximaAtividade("A1", "A4");
        controllerAtividade.definirProximaAtividade("A4", "A3");
        controllerAtividade.definirProximaAtividade("A3", "A5");
        controllerAtividade.definirProximaAtividade("A6", "A2");
        controllerAtividade.definirProximaAtividade("A2", "A7");
        controllerAtividade.tirarProximaAtividade("A2");
        controllerAtividade.tirarProximaAtividade("A1");
        controllerAtividade.tirarProximaAtividade("A3");
        assertEquals(null,controllerAtividade.getAtividade("A2").getIdSubsequente());
        assertEquals("A6",controllerAtividade.getAtividade("A2").retornaListaPrecedentes());
        assertEquals(null,controllerAtividade.getAtividade("A1").getIdSubsequente());
        assertEquals("Sem precedentes.",controllerAtividade.getAtividade("A1").retornaListaPrecedentes());
        assertEquals(null,controllerAtividade.getAtividade("A3").getIdSubsequente());
        assertEquals("A4",controllerAtividade.getAtividade("A3").retornaListaPrecedentes());
    }
    
    @Test
    void testPegarEnesimaProxima() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.pegarEnesimaProxima(null, 3);});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.pegarEnesimaProxima("", 3);});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.pegarEnesimaProxima("A1", -2 );});
    	controllerAtividade.definirProximaAtividade("A1", "A4");
        controllerAtividade.definirProximaAtividade("A4", "A3");
        controllerAtividade.definirProximaAtividade("A3", "A5");
        controllerAtividade.definirProximaAtividade("A6", "A2");
        controllerAtividade.definirProximaAtividade("A2", "A7");
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.pegarEnesimaProxima("A3",2);});
        assertEquals("A3",controllerAtividade.pegarEnesimaProxima("A1", 2));
        assertEquals("A5",controllerAtividade.pegarEnesimaProxima("A1", 3));
    }
    
    @Test
    void testPegarMaiorRiscoAtividades() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.pegarMaiorRiscoAtividades("");});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.pegarMaiorRiscoAtividades(null);});
        controllerAtividade.definirProximaAtividade("A6", "A2");
        controllerAtividade.definirProximaAtividade("A2", "A7");
        assertEquals("A2",controllerAtividade.pegarMaiorRiscoAtividades("A6"));   
    }
}
