package controllersTests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetolp2.pesquisa.ControllerPesquisa;

class ControllerPesquisaTest {
	private ControllerPesquisa controllerPesquisa;

	@BeforeEach
	public void criaControllerPesquisa() {
		this.controllerPesquisa = new ControllerPesquisa();
	}

	@Test
	public void cadastraPesquisaTest() {
		assertEquals("SAU1",controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica"));
		assertEquals("POL1",controllerPesquisa.cadastraPesquisa("pesquisa sobre transito", "politica,transito"));
		assertEquals("SAU2",controllerPesquisa.cadastraPesquisa("pesquisa sobre saude mental", "saude,politica"));
		
		}

	@Test
	public void cadastraPesquisaDescricaoNulleVazioTest(){
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.cadastraPesquisa("", "saude,computacao,politica"));
		assertThrows(NullPointerException.class,
				() -> controllerPesquisa.cadastraPesquisa(null, "saude,computacao,politica"));
		 }
	@Test
	public void cadastraPesquisaCampoDeInteresseNulleVazioTest(){
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.cadastraPesquisa("pesquisa cientifica", ""));
		assertThrows(NullPointerException.class,
				() -> controllerPesquisa.cadastraPesquisa("pesquisa cientifica", null));
		 }
	
	@Test
	public void cadastraPesquisaCampoDeInteresseInvalidoTest(){
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.cadastraPesquisa("pesquisa cientifica", ", , ,"));
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude.computacao.politica"));
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica,transito,sistemas"));
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica, Lorem ipsum dolor sit amet consectetur adipiscing elit Integer euismod leo in consequat efficitur Proin commodo nisi eget ligula consequat imperdiet ac quis turpis In non fringilla orci Pellentesque pellentesque ipsum vel ipsum ultrices scelerisque Nulla facilisi Morbi ut tellus ante Suspendisse malesuada quis quam eu efficitur Ut mollis turpis magna sit amet auctor nunc pulvinar ultricies Nam pharetra scelerisque magna ut feugiat"));
		 }
	@Test
	public void alteraPesquisaTest(){	
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerPesquisa.alteraPesquisa("SAU1", "DESCRICAO", "transito urbano");
		assertEquals("SAU1 - transito urbano - saude,computacao,politica",controllerPesquisa.exibePesquisa("SAU1"));
		controllerPesquisa.alteraPesquisa("SAU1", "CAMPO", "computacao,saude");
		assertEquals("SAU1 - transito urbano - computacao,saude",controllerPesquisa.exibePesquisa("SAU1"));
	}
	@Test
	public void alteraPesquisaDescricaoNulleVazioTest(){
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.alteraPesquisa("SAU1", "DESCRICAO", ""));
		assertThrows(NullPointerException.class,
				() -> controllerPesquisa.alteraPesquisa("SAU1", "DESCRICAO", null));
	}
	@Test
	public void alteraPesquisaCampoDeInteresseNulleVazioTest(){
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.alteraPesquisa("SAU1", "CAMPO", ""));
		assertThrows(NullPointerException.class,
				() -> controllerPesquisa.alteraPesquisa("SAU1", "CAMPO", null));
	}
	@Test
	public void alteraPesquisaConteudoInvalidoNulleVazioTest(){
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.alteraPesquisa("SAU1", "interesse", "computacao,politica"));
		
	}
	@Test
	public void alteraPesquisaDesativadaTest(){
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerPesquisa.encerraPesquisa("SAU1", "sem dinheiro para continuar");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.alteraPesquisa("SAU1", "CAMPO", "computacao,politica"));	
	}
	@Test
	public void alteraPesquisaNaoExisteTest(){
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "computacao,politica"));	
	}
	
	@Test
	public void encerraPesquisaTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerPesquisa.encerraPesquisa("SAU1", "sem dinheiro para continuar");
		assertFalse(controllerPesquisa.ehAtiva("SAU1"));
	}
	@Test
	public void encerraPesquisaMotivoNulleVazioTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");

		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.encerraPesquisa("SAU1", ""));
		assertThrows(NullPointerException.class,
				() -> controllerPesquisa.encerraPesquisa("SAU1", null));
	}
	@Test
	public void encerraPesquisaJaDesativadaTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerPesquisa.encerraPesquisa("SAU1", "sem dinheiro para continuar");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.encerraPesquisa("SAU1", "nao temos recursos"));
	}
	@Test
	public void encerraPesquisaNaoExisteTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.encerraPesquisa("COM1", "nao temos recursos"));
	}
	@Test
	public void ativaPesquisaTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		controllerPesquisa.encerraPesquisa("SAU1", "sem dinheiro para continuar");
		controllerPesquisa.ativaPesquisa("SAU1");
		assertTrue(controllerPesquisa.ehAtiva("SAU1"));
	}
	@Test
	public void ativaPesquisaJaAtivaTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.ativaPesquisa("SAU1"));		
	}
	@Test
	public void ativaPesquisaNaoExisteTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.ativaPesquisa("COM1"));		
	}
	@Test
	public void exibePesquisatTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertEquals("SAU1 - pesquisa cientifica - saude,computacao,politica",controllerPesquisa.exibePesquisa("SAU1"));
	}
	@Test 
	public void exibePesquisaCodigoNaoExiteTest() {
		controllerPesquisa.cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
		assertThrows(IllegalArgumentException.class,
				() -> controllerPesquisa.exibePesquisa("COM1"));
		
	}
	
}


