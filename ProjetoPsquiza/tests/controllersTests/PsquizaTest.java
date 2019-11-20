package controllersTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void criaPsquiza() {
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerAtividade = new ControllerAtividade();
		this.psquiza = new Psquiza();
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerAtividade.cadastrarAtividadePesquisa("Atividade 01", "BAIXO", "Não envolve morte");
		controllerAtividade.cadastrarItem("A1", "Monitoramento Facebook/Messenger");
		psquiza.cadastraResultado("A1", "Analise nao foi possivel.");
	}

	@Test
	public void associaAtividadeTest() {
		assertTrue(psquiza.associaAtividade("PC1", "A1"));

	}

//	@Test
//	public void associaAtividadePesquisaNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.associaAtividade("", "A1"));
//		assertThrows(NullPointerException.class, () -> psquiza.associaAtividade(null, "A1"));
//	}
//
//	@Test
//	public void associaAtividadeAtividadeNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.associaAtividade("PC1", ""));
//		assertThrows(NullPointerException.class, () -> psquiza.associaAtividade("PC1", null));
//	}
//
//	@Test
//	public void associaAtividadeNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.associaAtividade("PC1", "A10"));
//
//	}
//
//	@Test
//	public void associaPesquisaNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.associaAtividade("COM2", "A1"));
//	}
//
//	@Test
//	public void associaPesquisaDesativadaTest() {
//		controllerPesquisa.encerraPesquisa("PC1", "muitos gastos");
//		assertThrows(IllegalArgumentException.class, () -> psquiza.associaAtividade("PC1", "A10"));
//
//	}
//
//	@Test
//	public void desassociaAtividadeTest() {
//		psquiza.desassociaAtividade("PC1", "A1");
//
//	}
//
//	@Test
//	public void desassociaAtividadePesquisaNUlleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.desassociaAtividade("", "A1"));
//		assertThrows(NullPointerException.class, () -> psquiza.desassociaAtividade(null, "A1"));
//	}
//
//	@Test
//	public void desassociaAtividadeAtividadeNUlleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.desassociaAtividade("PC1", ""));
//		assertThrows(NullPointerException.class, () -> psquiza.desassociaAtividade("PC1", null));
//	}
//
//	@Test
//	public void desassociaAtividadeNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.desassociaAtividade("PC1", "A10"));
//
//	}
//
//	@Test
//	public void desassociaPesquisaNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.desassociaAtividade("COM2", "A1"));
//	}
//
//	@Test
//	public void desassociaPesquisaDesativadaTest() {
//		controllerPesquisa.encerraPesquisa("PC1", "muitos gastos");
//		assertThrows(IllegalArgumentException.class, () -> psquiza.desassociaAtividade("PC1", "A10"));
//
//	}
//
//	@Test
//	public void executaAtividadeTest() {
//		psquiza.executaAtividade("A1", 1, 5);
//		assertEquals("REALIZADO", controllerAtividade.getAtividade("A1").getStatusItem(1));
//	}
//
//	@Test
//	public void executaAtividadeCodigoAtividadeNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.executaAtividade("", 1, 5));
//		assertThrows(NullPointerException.class, () -> psquiza.executaAtividade(null, 1, 5));
//	}
//
//	@Test
//	public void executaAtividadeInteiroNulleNegativoTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.executaAtividade("A1", -1, 5));
//		assertThrows(NullPointerException.class, () -> psquiza.executaAtividade("A1", null, 5));
//	}
//
//	@Test
//	public void executaAtividadeDuracaoNulleNegativoTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.executaAtividade("A1", 1, -5));
//		assertThrows(NullPointerException.class, () -> psquiza.executaAtividade("A1", 1, null));
//	}
//
//	@Test
//	public void executaAtividadeSemAssociacaoTest() {
//		psquiza.desassociaAtividade("PC1", "A1");
//		assertThrows(IllegalArgumentException.class, () -> psquiza.executaAtividade("A1", 1, 5));
//	}
//
//	@Test
//	public void executaAtividadeItemNaoEncontradoTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.executaAtividade("A1", 10, 5));
//	}
//
////	@Test
////	public void cadastraResultadoTest() {
////		assertEquals(1, psquiza.cadastraResultado("A1", "Analise nao foi possivel."));
////	}
//
//	@Test
//	public void cadastraResultadoAtividadeVazioeNullTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.cadastraResultado("", "Analise nao foi possivel."));
//		assertThrows(NullPointerException.class, () -> psquiza.cadastraResultado(null, "Analise nao foi possivel."));
//	}
//
//	@Test
//	public void cadastraResultadoResultadoVazioeNullTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.cadastraResultado("A1", ""));
//		assertThrows(NullPointerException.class, () -> psquiza.cadastraResultado("A1", null));
//	}
//
//	@Test
//	public void removeResultadoTest() {
//		assertTrue(psquiza.removeResultado("A1", 1));
//	}
//
//	@Test
//	public void removeResultadoAtividadeNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.removeResultado("", 1));
//		assertThrows(NullPointerException.class, () -> psquiza.removeResultado(null, 1));
//
//	}
//
//	@Test
//	public void removeResultadoResultadoNulleNegativoTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.removeResultado("A1", -1));
//		assertThrows(NullPointerException.class, () -> psquiza.removeResultado("A1", null));
//
//	}
//
//	@Test
//	public void removeResultadoAtividadeNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.removeResultado("A10", 1));
//
//	}
//
//	@Test
//	public void removeResultadResultadoNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.removeResultado("A1", 10));
//
//	}
//
//	@Test
//	public void removeResultadResultadoRemovidoTest() {
//		psquiza.removeResultado("A1", 1);
//		assertFalse(psquiza.removeResultado("A1", 1));
//	}
//
//	@Test
//	public void listaResultadosTest() {
//		assertEquals("Analise nao foi possivel.", psquiza.listaResultados("A1"));
//	}
//
//	@Test
//	public void listaResultadosAtividadeNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.listaResultados(""));
//		assertThrows(NullPointerException.class, () -> psquiza.listaResultados(null));
//
//	}
//
//	@Test
//	public void listaResultadosAtividadeNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.listaResultados("A10"));
//	}
//
//	// @Test
//	// public void getDuracaoTest() {
//	// assertEquals(5,psquiza.getDuracao("A1"));
//	// }
//	@Test
//	public void getDuracaoAtividadeNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.getDuracao(""));
//		assertThrows(NullPointerException.class, () -> psquiza.getDuracao(null));
//
//	}
//
//	@Test
//	public void getDuracaoAtividadeNaoEncontradaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.getDuracao("A10"));
//
//	}
//
//	@Test
//	public void configuraEstrategiaTest() {
//		psquiza.configuraEstrategia("MENOS_PENDENCIAS");
//		assertEquals("MENOS_PENDENCIAS", psquiza.getControllerPesquisa().getEstrategia());
//	}
//
//	@Test
//	public void configuraEstrategiaNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.configuraEstrategia(""));
//		assertThrows(NullPointerException.class, () -> psquiza.configuraEstrategia(null));
//	}
//
//	@Test
//	public void configuraEstrategiaInvalidaTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.configuraEstrategia("MAIS_RECENTE"));
//
//	}
//
//	@Test
//	public void proximaAtividadeTest() {
//		assertEquals("A1", psquiza.proximaAtividade("PC1"));
//	}
//
//	@Test
//	public void proximaAtividadeNulleVazioTest() {
//		assertThrows(IllegalArgumentException.class, () -> psquiza.proximaAtividade(""));
//		assertThrows(NullPointerException.class, () -> psquiza.proximaAtividade(null));
//
//	}

}
