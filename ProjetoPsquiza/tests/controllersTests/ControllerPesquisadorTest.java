package controllersTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetolp2.pesquisador.ControllerPesquisador;

class ControllerPesquisadorTest {
	private ControllerPesquisador controllerPesquisador;

	@BeforeEach
	public void inicia() {
		this.controllerPesquisador = new ControllerPesquisador();
		this.controllerPesquisador.cadastraPesquisador("joel", "externo", "Interessado em fungos", "borutofathers@1997", "https://dattebayo");
		this.controllerPesquisador.cadastraPesquisador("Pedro henrick", "estudante", "Interessado em jogos, lolzin e pa", "pedro@ccc", "https://pedro123");
		this.controllerPesquisador.cadastraPesquisador("Livia", "professor", "Interessada em dar uma boa aula e bons aprendizados aos alunos", "livia@ccc", "https://livia123");
		this.controllerPesquisador.cadastraPesquisador("Caio medeiros", "estudante", "interessado em software", "caio@ccc", "https://caio123");
		this.controllerPesquisador.cadastraPesquisador("Danilo medeiros", "estudante", "Interessado no bem estar dos seres humanos", "danilo@ccc", "https://danilo123");
		this.controllerPesquisador.cadastraPesquisador("Holliver costa", "estudante", "Interessado em pubg e cs", "holliver@ccc", "https://holliver123");
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
	@Test
	void testCadastraPesquisador() {
		//CAMPOS VAZIOS OU NULOS
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("", "estudante", "teste", "vamo@vamo", "https://testar");});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraPesquisador(null,"externo", "teste",  "vamo@vamo", "https://testar");});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("jinx", "", "teste", "vamo@vamo", "https://testar");});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraPesquisador("annie", null, "teste", "vamo@vamo", "https://testar");});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("ryze", "externo", "",  "vamo@vamo", "https://testar");});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraPesquisador("vayne", "externo", null,  "vamo@vamo", "https://testar");});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("tresh","estudante", "teste",  "", "https://testar");});		
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraPesquisador("darius", "professor", "teste",  null , "https://testar");});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("oriana", "externo","teste",  "vamo@vamo", "");});		
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraPesquisador("hecarim", "estudante", "teste", "vamo@vamo", null);});
		//EMAIL COM FORMATO INVALIDO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("hecarim", "estudante", "teste", "DQLMODELO@", "https://dqlmodelin");});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("hecarim", "estudante", "teste", "@OUTROMODELO", "https://dqlmodelin");});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("hecarim", "estudante", "teste", "DESSEMODELO", "https://dqlmodelin");});
		//FOTOURL COM FORMATO INVALIDO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("tresh","estudante", "teste",  "SIM@SIM", "https");});		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("tresh","estudante", "teste",  "SIM@SIM", "https www.com");});	
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraPesquisador("tresh","estudante", "teste",  "SIM@SIM", "semnada");});		
		//EXIBE PESQUISADOR
		assertEquals("Pedro henrick (estudante) - Interessado em jogos, lolzin e pa - pedro@ccc - https://pedro123", controllerPesquisador.exibePesquisador("pedro@ccc"));


	}
	
	@Test
	void testAlteraPesquisador() {
		controllerPesquisador.alteraPesquisador("pedro@ccc", "NOME", "Pedro Goncalves");	
		assertEquals("Pedro Goncalves (estudante) - Interessado em jogos, lolzin e pa - pedro@ccc - https://pedro123", controllerPesquisador.exibePesquisador("pedro@ccc"));
		controllerPesquisador.alteraPesquisador("pedro@ccc", "FUNCAO", "externo");	
		assertEquals("Pedro Goncalves (externo) - Interessado em jogos, lolzin e pa - pedro@ccc - https://pedro123", controllerPesquisador.exibePesquisador("pedro@ccc"));
		controllerPesquisador.alteraPesquisador("pedro@ccc", "BIOGRAFIA", "Nao gosta de fazer testes junit");	
		assertEquals("Pedro Goncalves (externo) - Nao gosta de fazer testes junit - pedro@ccc - https://pedro123", controllerPesquisador.exibePesquisador("pedro@ccc"));
		controllerPesquisador.alteraPesquisador("pedro@ccc", "FOTO", "https://goncalves321");	
		assertEquals("Pedro Goncalves (externo) - Nao gosta de fazer testes junit - pedro@ccc - https://goncalves321", controllerPesquisador.exibePesquisador("pedro@ccc"));
		controllerPesquisador.alteraPesquisador("pedro@ccc", "EMAIL", "goncalves@pedro");	
		assertEquals("Pedro Goncalves (externo) - Nao gosta de fazer testes junit - goncalves@pedro - https://goncalves321", controllerPesquisador.exibePesquisador("goncalves@pedro"));
		//PESQUISADOR NAO ENCONTRADO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("galalau@babau", "NOME", "BLESSED");;});
		//ATRIBUTO INVALIDO 
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "sobrenome", "BLESSED");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "memoria", "hd");;});
		//ATRIBUTO VAZIO OU NULO 
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "", "abencoado");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", null, "amoroso");;});
		//NOVO VALOR VAZIO OU NULO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "NOME", "");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "EMAIL", null);;});
		//EMAIL COM FORMATO INVALIDO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "EMAIL", "@ABENCOADO");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "EMAIL", "fazobilly@");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "EMAIL", "soobasico");;});
		//FOTO COM FORMATO INVALIDO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("goncalves@pedro", "FOTO", "htps:videos");;});

		}
	@Test
	void testAtivaPesquisador() {
		//DESATIVANDO
		controllerPesquisador.desativaPesquisador("pedro@ccc");
		controllerPesquisador.desativaPesquisador("danilo@ccc");
		controllerPesquisador.desativaPesquisador("caio@ccc");
		controllerPesquisador.desativaPesquisador("livia@ccc");
		//ATIVANDO
		controllerPesquisador.ativaPesquisador("pedro@ccc");
		controllerPesquisador.ativaPesquisador("danilo@ccc");
		controllerPesquisador.ativaPesquisador("caio@ccc");
		controllerPesquisador.ativaPesquisador("livia@ccc");
		//PESQUISADOR NAO ENCONTRADO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("ban@BAN");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("b123@432");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("vamo@loucura");;});
		//PESQUISADOR JA ATIVO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("pedro@ccc");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("danilo@ccc");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("caio@ccc");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.ativaPesquisador("livia@ccc");;});
		
	}
	@Test
	void testDesativaPesquisador() {
		//DESATIVANDO
		controllerPesquisador.desativaPesquisador("pedro@ccc");
		controllerPesquisador.desativaPesquisador("danilo@ccc");
		controllerPesquisador.desativaPesquisador("caio@ccc");
		controllerPesquisador.desativaPesquisador("livia@ccc");
		//PESQUISADOR NAO ENCONTRADO 
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("ban@BAN");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("b123@432");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("vamo@loucura");;});
		//PESQUISADOR JA INATIVO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("pedro@ccc");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("danilo@ccc");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("caio@ccc");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.desativaPesquisador("livia@ccc");;});
	}

	

	@Test
	void testExibePesquisador() {
		//PESQUISADOR NAO ENCONTRADO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.exibePesquisador("MELIODAS@meliodas");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.exibePesquisador("amor@infinito");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.exibePesquisador("quero@loucura");;});
		//EMAIL VAZIO OU NULO
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.exibePesquisador("");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.exibePesquisador(null);;});
		//EXIBE PESQUISADOR
		assertEquals("joel (externo) - Interessado em fungos - borutofathers@1997 - https://dattebayo", controllerPesquisador.exibePesquisador("borutofathers@1997"));
		assertEquals("Livia (professor) - Interessada em dar uma boa aula e bons aprendizados aos alunos - livia@ccc - https://livia123", controllerPesquisador.exibePesquisador("livia@ccc"));

	}

	@Test
	void testPesquisadorEhAtivo() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.pesquisadorEhAtivo("");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.pesquisadorEhAtivo(null);;});
		
		assertTrue(controllerPesquisador.pesquisadorEhAtivo("pedro@ccc"));
		controllerPesquisador.desativaPesquisador("pedro@ccc");
		assertFalse(controllerPesquisador.pesquisadorEhAtivo("pedro@ccc"));

	}

	@Test
	void testCadastraEspecialidadeProfessor() {
		//CAMPOS VAZIOS OU NULOS 
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("", "doutorado", "DSC", "22/10/2000");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor(null, "doutorado", "DSC", "22/10/2000");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", "", "DSC", "22/10/2000");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", null, "DSC", "22/10/2000");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", "doutorado", "", "22/10/2000");;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", "doutorado", "DSC", null);;});
		//PESQUISADOR INEXISTENTE
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("tupac@jayz", "doutorado", "TCC", "22/10/2000");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("gugu@eliana", "doutorado", "TCC", "22/10/2000");;});
		//DATA INVALIDA 
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", "doutorado", "TCC", "22/101/2000");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", "doutorado", "TCC", "22/2000");;});
		//METODO EM ALUNOS OU EXTERNOS
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("pedro@ccc", "doutorado", "TCC", "22/10/2000");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("caio@ccc", "doutorado", "TCC", "22/10/2000");;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeProfessor("danilo@ccc", "doutorado", "TCC", "22/10/2000");;});
		//USANDO O METODO
		controllerPesquisador.cadastraEspecialidadeProfessor("livia@ccc", "Computacao", "UFCG", "30/09/2010");
		assertEquals("Livia (professor) - Interessada em dar uma boa aula e bons aprendizados aos alunos - livia@ccc - https://livia123 - Computacao - UFCG - 30/09/2010", controllerPesquisador.exibePesquisador("livia@ccc"));
	}

	@Test
	void testCadastraEspecialidadeAluno() {
		//CAMPOS VAZIOS
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("", 2, 10.0);;;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno(null, 5, 9.0);;});
		Assertions.assertThrows(NullPointerException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("pedro@ccc", null, 9.0);;});
		//PESQUISADOR INEXISTENTE
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("tupac@beyonce", 5, 7.0);;;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("EMINEM@snoop", 5, 7.0);;;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("beatles@beagle", 5, 7.0);;;});
		//USANDO O METODO EM PROFESSORES OU EXTERNOS
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("livia@ccc", 1, 8.0);;;});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.cadastraEspecialidadeAluno("borutofathers@1997", 5, 7.0);;;});
		//USANDO O METODO 
		controllerPesquisador.cadastraEspecialidadeAluno("pedro@ccc", 4, 9.99);
		assertEquals("Pedro henrick (estudante) - Interessado em jogos, lolzin e pa - pedro@ccc - https://pedro123 - 4o SEMESTRE - 9.99", controllerPesquisador.exibePesquisador("pedro@ccc"));
	}

	
}
