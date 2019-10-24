package projetolp2;

import easyaccept.EasyAccept;

public class Psquiza {
		ControllerPesquisa controllerPesquisa;
	public static void main(String[] args) {
		args = new String[] {"projetolp2.Psquiza", "TestesDeAceitacao/use_case_1.txt"};
		EasyAccept.main(args);
	}
	public Psquiza() {
		controllerPesquisa = new ControllerPesquisa();
	}
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}
	
//	public void alteraPesquisa(String código, String conteúdoASerAlterado, String novoConteúdo) {
//		
//	}
//	
//	public void encerraPesquisa(String codigo, String motivo) {
//		
//	}
//	public void ativaPesquisa(String codigo) {
//		
//	}
//	public String exibePesquisa(String codigo) {
//		
//	}
//	public boolean ehAtiva(String codigo) {
//		
//	}
}
