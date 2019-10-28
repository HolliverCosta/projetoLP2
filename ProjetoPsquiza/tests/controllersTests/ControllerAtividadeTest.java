package controllersTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetolp2.usecase4.ControllerAtividade;

class ControllerAtividadeTest {

    private ControllerAtividade controllerAtividade;
    
    @BeforeEach
    public void criaControllerAtividade() {
        controllerAtividade = new ControllerAtividade();
    }
    
//----------------------------------------------------------------------------------------------------------------//    
    @Test
    void testCadastrarAtividadePesquisa() {
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte"));
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)",controllerAtividade.exibirAtividade("A1"));
    }
    @Test
    void testApagarAtividade() {
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte"));
        assertEquals("A2", controllerAtividade.cadastrarAtividadePesquisa("Atividade 02", "ALTO", "Risco de morte"));
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)",controllerAtividade.exibirAtividade("A1"));
        assertEquals("Atividade 02 (ALTO - Risco de morte)",controllerAtividade.exibirAtividade("A2"));
        controllerAtividade.apagarAtividade("A2");
        try {
            controllerAtividade.exibirAtividade("A2");
        } catch (IllegalArgumentException e) {
            // td certo;
        }
    }
    @Test
    void testCadastrarItem() {
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Monitoramento"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals("Atividade 01 (BAIXO - Monitoramento) | PENDENTE - Monitoramento Facebook/Messenger", controllerAtividade.exibirAtividade("A1"));
    }

    @Test
    void testExibirAtividade() {
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte"));
        assertEquals("Atividade 01 (BAIXO - Não envolve morte)",controllerAtividade.exibirAtividade("A1"));
    }

    @Test
    void testContarItensPendentes() {
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Monitoramento de chats dos alunos de computacao do primeiro periodo", "BAIXO", "Monitoramento"));
        assertEquals(0, controllerAtividade.contarItensPendentes("A1"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(1, controllerAtividade.contarItensPendentes("A1"));
    }

    @Test
    void testContarItensRealizados() {
        assertEquals("A1", controllerAtividade.cadastrarAtividadePesquisa("Monitoramento de chats dos alunos de computacao do primeiro periodo", "BAIXO", "Monitoramento"));
        assertEquals(0, controllerAtividade.contarItensRealizados("A1"));
        controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
        assertEquals(0, controllerAtividade.contarItensRealizados("A1")); 
        assertEquals(1, controllerAtividade.contarItensPendentes("A1"));
    }

}
