package controllersTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetolp2.Psquiza.Psquiza;
import projetolp2.atividades.ControllerAtividade;
import projetolp2.pesquisa.ControllerPesquisa;

class PsquizaTest {
	private ControllerPesquisa controllerPesquisa;
	private ControllerAtividade controllerAtividade;
	private Psquiza psquiza;
	@BeforeEach
	public void criaControllerPesquisa() {
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerAtividade = new ControllerAtividade();
		this.psquiza = new Psquiza();
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerAtividade.cadastrarAtividadePesquisa("provando cerveja", "MEDIO", "problema de saude");
	}

	@Test
	public void associaAtividadeTest() {
		assertTrue(psquiza.associaAtividade("PC1", "A1"));
		
		
		}

}
