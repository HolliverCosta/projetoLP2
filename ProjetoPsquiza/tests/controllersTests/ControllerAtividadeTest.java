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
        assertEquals("A1",controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte"));
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)", controllerAtividade.exibirAtividade("A1"));
    }

    @Test
    void testApagarAtividade() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.apagarAtividade("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.apagarAtividade(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.apagarAtividade("A1");});
        assertEquals("A1",controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte"));
        assertEquals("A2", controllerAtividade.cadastrarAtividadePesquisa("Atividade 02", "ALTO", "Risco de morte"));
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)", controllerAtividade.exibirAtividade("A1"));
        assertEquals("Atividade 02 (ALTO - Risco de morte)", controllerAtividade.exibirAtividade("A2"));
        controllerAtividade.apagarAtividade("A2");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade("A2");});
    }

    @Test
    void testCadastrarItem() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("","Monitoramento Facebook/Messenger");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem(null,"Monitoramento Facebook/Messenger");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("A1","");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("A1",null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.cadastrarItem("A2","Monitoramento Facebook/Messenger");});
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Monitoramento"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals("Atividade 01 (BAIXO - Monitoramento) | PENDENTE - Monitoramento Facebook/Messenger",controllerAtividade.exibirAtividade("A1"));
    }

    @Test
    void testExibirAtividadeCorreto() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.exibirAtividade("A1");});
        assertEquals("A1",controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte"));
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)", controllerAtividade.exibirAtividade("A1"));
        assertEquals("A2", controllerAtividade.cadastrarAtividadePesquisa("Atividade 02", "ALTO", "Risco de morte"));
        assertEquals("Atividade 02 (ALTO - Risco de morte)", controllerAtividade.exibirAtividade("A2"));
    }
    
    @Test
    void testContarItensPendentes() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensPendentes("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensPendentes(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensPendentes("A1");});
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Monitoramento de chats dos alunos de computacao do primeiro periodo", "BAIXO", "Monitoramento"));
        assertEquals(0, controllerAtividade.contarItensPendentes("A1"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(1, controllerAtividade.contarItensPendentes("A1"));
    }

    @Test
    void testContarItensRealizados() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensRealizados("");});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensRealizados(null);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerAtividade.contarItensRealizados("A1");});
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Monitoramento de chats dos alunos de computacao do primeiro periodo", "BAIXO", "Monitoramento"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(1, controllerAtividade.contarItensPendentes("A1"));
        assertEquals(0, controllerAtividade.contarItensRealizados("A1"));
        controllerAtividade.finalizaStatusItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(0, controllerAtividade.contarItensPendentes("A1"));
        assertEquals(1, controllerAtividade.contarItensRealizados("A1"));
    }
}
