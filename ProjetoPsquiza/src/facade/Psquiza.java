package facade;

import easyaccept.EasyAccept;
import projetolp2.atividades.ControllerAtividade;
import projetolp2.pesquisa.ControllerPesquisa;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.po.ControllerPO;

public class Psquiza {

	private ControllerPesquisa controllerPesquisa;
	private ControllerAtividade controllerAtividade;
	private ControllerPesquisador controllerPesquisador;
	private ControllerPO poController;

	public static void main(String[] args) {
		args = new String[] { "facade.Psquiza", "TestesDeAceitacao/use_case_1.txt", "TestesDeAceitacao/use_case_2.txt",
				"TestesDeAceitacao/use_case_3.txt","TestesDeAceitacao/use_case_4.txt" };
		EasyAccept.main(args);
	}

	public Psquiza() {
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerAtividade = new ControllerAtividade();
		this.controllerPesquisador = new ControllerPesquisador();
		this.poController = new ControllerPO();
	}
	//-----------------------------------------------------Pesquisa-----------------------------------------------------------//
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controllerPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		controllerPesquisa.encerraPesquisa(codigo, motivo);
	}

	public void ativaPesquisa(String codigo) {
		controllerPesquisa.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return controllerPesquisa.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return controllerPesquisa.ehAtiva(codigo);
	}

	//----------------------------------------Pesquisador----------------------------------------------------------//
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.controllerPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.controllerPesquisador.alteraPesquisador(email, atributo, novoValor);
	}

	public void desativaPesquisador(String email) {
		this.controllerPesquisador.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		this.controllerPesquisador.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return this.controllerPesquisador.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return this.controllerPesquisador.pesquisadorEhAtivo(email);
	}
	
	//-------------------------------------------------Problemas e Objetivos-----------------------------------------------//
	public String cadastraProblema(String descricao, int viabilidade) {
		return poController.cadastraProblema(descricao, viabilidade);
	}
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return poController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}
	public void apagarProblema(String codigo) {
		poController.apagarProblema(codigo);
	}
	public void apagarObjetivo(String codigo) {
		poController.apagarObjetivo(codigo);
	}
	public String exibeProblema(String codigo) {
		return poController.exibeProblema(codigo);
	}
	public String exibeObjetivo(String codigo) {
		return poController.exibeObjetivo(codigo);
	}
	
	//----------------------------------------------Atividades Metodologicas------------------------------------------------//
	public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
		return controllerAtividade.cadastrarAtividadePesquisa(Descricao, nivelRisco, descricaoRisco);
	}

	
	public void apagaAtividade(String codigo) {
		controllerAtividade.apagarAtividade(codigo);
	}

	
	public void cadastraItem(String codigo, String item) {
		controllerAtividade.cadastrarItem(codigo, item);
	}

	
	public String exibeAtividade(String codigo) {
		return controllerAtividade.exibirAtividade(codigo);
	}

	
	public int contaItensPendentes(String codigo) {
		return controllerAtividade.contarItensPendentes(codigo);
	}

	
	public int contaItensRealizados(String codigo) {
		return controllerAtividade.contarItensRealizados(codigo);
	}

	

}
