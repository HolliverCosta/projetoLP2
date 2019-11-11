package controllersTests;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projetolp2.Psquiza.Psquiza;

class ControllerBuscaTest {
	
	private Psquiza psquiza;
	
    @BeforeEach
    public void criaPsquiza() {
        psquiza = new Psquiza();
        psquiza.getControllerPesquisa().cadastraPesquisa("pesquisa cientifica", "saude,computacao,politica");
        psquiza.getControllerPesquisa().cadastraPesquisa("pesquisa sobre transito", "politica,transito");
       	psquiza.getControllerPesquisa().cadastraPesquisa("pesquisa sobre saude mental", "saude,politica");
       	psquiza.getControllerPesquisa().cadastraPesquisa("O comportamento so jovens nos games de hoje", "games,jovens");
 		psquiza.getControllerPO().cadastraObjetivo("GERAL", "Estudar a computação no mundo dos jovens", 1, 3);
        psquiza.getControllerPO().cadastraObjetivo("ESPECIFICO", "Identificar os códigos usuados na atualiade", 5, 5);
        psquiza.getControllerPO().cadastraObjetivo("ESPECIFICO", "Identificar o mau uso da computação atualmente", 4, 3);
        psquiza.getControllerPO().cadastraObjetivo("GERAL", "Estudo computacional", 2, 5);
        psquiza.getControllerPO().cadastraObjetivo("ESPECIFICO", "Analise do comportamento na internet", 3, 3);
        psquiza.getControllerPO().cadastraProblema("A péssima qualidade da linguagem formal dos jovens", 3);
        psquiza.getControllerPO().cadastraProblema("A péssima qualidade de vida digital dos jovens", 2);
        psquiza.getControllerPO().cadastraProblema("O Compromisso com a realidade.", 5);
        psquiza.getControllerPO().cadastraProblema("A péssima qualidade das relações amorosas dos jovens", 3);
        psquiza.getControllerPO().cadastraProblema("O uso da computação para o mal", 1);
        psquiza.getControllerAtividade().cadastrarAtividadePesquisa("Analisa da Computação no mundo.", "BAIXO", "Uso da computação nao tem riscos.");
        psquiza.getControllerAtividade().cadastrarAtividadePesquisa("Atividade 02", "MEDIO", "Uso do messenger/Facebook");
    }

	@Test
	void testBuscarGeral() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().buscarGeral("",psquiza);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().buscarGeral(null,psquiza);});
        assertEquals("SAU1: saude,computacao,politica | GAM1: O comportamento so jovens nos games de hoje | P5: O uso da computação para o mal | P3: O Compromisso com a realidade. | O5: Analise do comportamento na internet | O4: Estudo computacional | O3: Identificar o mau uso da computação atualmente | O1: Estudar a computação no mundo dos jovens | A1: Analisa da Computação no mundo. | A1: Uso da computação nao tem riscos.", psquiza.getControllerBusca().buscarGeral("comp", psquiza));
	}

	@Test
	void testBuscarEspecífico() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().buscarEspecífico("",3,psquiza);});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().buscarEspecífico(null,3,psquiza);});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().buscarEspecífico("Computação",-2,psquiza);});
		assertEquals("O4: Estudo computacional", psquiza.getControllerBusca().buscarEspecífico("comp", 6, psquiza));
	}

	@Test
	void testContarResultado() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().contarResultado("",psquiza);});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {psquiza.getControllerBusca().contarResultado(null,psquiza);});
		assertEquals(10, psquiza.getControllerBusca().contarResultado("comp", psquiza));
	}
}
