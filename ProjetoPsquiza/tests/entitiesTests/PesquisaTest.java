package entitiesTests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetolp2.pesquisa.Pesquisa;

class PesquisaTest {
	private Pesquisa pesquisa;
	@BeforeEach
	void testPesquisa() {
		this.pesquisa = new Pesquisa("pesquisa cientifica", "saude,computacao,politica", "PC1");
	}


	@Test
	void testToString() {
	 assertEquals("PC1 - pesquisa cientifica - saude,computacao,politica",pesquisa.toString());
	}

	

}
