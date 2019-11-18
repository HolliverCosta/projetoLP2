package facade;

import easyaccept.EasyAccept;
import projetolp2.Psquiza.Psquiza;

public class Facade {

	Psquiza controllerGeral;

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "TestesDeAceitacao/use_case_1.txt", "TestesDeAceitacao/use_case_2.txt",
				"TestesDeAceitacao/use_case_3.txt", "TestesDeAceitacao/use_case_4.txt",
				"TestesDeAceitacao/use_case_5.txt", "TestesDeAceitacao/use_case_6.txt",
				"TestesDeAceitacao/use_case_7.txt", "TestesDeAceitacao/use_case_8.txt",
				"TestesDeAceitacao/use_case_9.txt", "TestesDeAceitacao/use_case_10.txt" };
		String[] args2 = new String[] {"facade.Facade",
		        "TestesDeAceitacao/use_case_12SALVAR.txt", 
		        "TestesDeAceitacao/use_case_12CARREGAR.txt"};
		//EasyAccept.main(args);
		EasyAccept.main(args2);
	}

	public Facade() {
		this.controllerGeral = new Psquiza();
	}

	// -----------------------------------------------------Pesquisa-----------------------------------------------------------//
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controllerGeral.getControllerPesquisa().cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controllerGeral.getControllerPesquisa().alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		controllerGeral.getControllerPesquisa().encerraPesquisa(codigo, motivo);
	}

	public void ativaPesquisa(String codigo) {
		controllerGeral.getControllerPesquisa().ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return controllerGeral.getControllerPesquisa().exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return controllerGeral.getControllerPesquisa().ehAtiva(codigo);
	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controllerGeral.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return controllerGeral.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerGeral.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerGeral.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	public String listaPesquisas(String ordem) {
		return controllerGeral.getControllerPesquisa().listaPesquisas(ordem);
	}

	// ----------------------------------------Pesquisador----------------------------------------------------------//
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		controllerGeral.getControllerPesquisador().cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		controllerGeral.getControllerPesquisador().alteraPesquisador(email, atributo, novoValor);

	}

	public void desativaPesquisador(String email) {
		controllerGeral.getControllerPesquisador().desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		controllerGeral.getControllerPesquisador().ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return controllerGeral.getControllerPesquisador().exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return controllerGeral.getControllerPesquisador().pesquisadorEhAtivo(email);
	}

	// -------------------------------------------------Problemas e Objetivos-----------------------------------------------//
	public String cadastraProblema(String descricao, int viabilidade) {
		return controllerGeral.getControllerPO().cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return controllerGeral.getControllerPO().cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		controllerGeral.getControllerPO().apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		controllerGeral.getControllerPO().apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return controllerGeral.getControllerPO().exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return controllerGeral.getControllerPO().exibeObjetivo(codigo);
	}

	// ----------------------------------------------Atividades Metodologicas------------------------------------------------//
	public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
		return controllerGeral.getControllerAtividade().cadastrarAtividadePesquisa(Descricao, nivelRisco,
				descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		controllerGeral.getControllerAtividade().apagarAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		controllerGeral.getControllerAtividade().cadastrarItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return controllerGeral.getControllerAtividade().exibirAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return controllerGeral.getControllerAtividade().contarItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return controllerGeral.getControllerAtividade().contarItensRealizados(codigo);
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return this.controllerGeral.getControllerPesquisa().associaPesquisador(idPesquisa, emailPesquisador,
				controllerGeral.getControllerPesquisador());
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return this.controllerGeral.getControllerPesquisa().desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		this.controllerGeral.getControllerPesquisador().cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(String email, Integer semestre, Double IEA) {
		this.controllerGeral.getControllerPesquisador().cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public String listaPesquisadores(String tipo) {
		return this.controllerGeral.getControllerPesquisador().listaPesquisadores(tipo);
	}

	// ----------------------------------------------US7--------------------------------------------------------//
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerGeral.associaAtividade(codigoPesquisa, codigoAtividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerGeral.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, Integer item, Integer duracao) {
		controllerGeral.executaAtividade(codigoAtividade, item, duracao);
	}

	public Integer cadastraResultado(String codigoAtividade, String resultado) {
		return controllerGeral.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, Integer numeroResultado) {
		return controllerGeral.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return controllerGeral.listaResultados(codigoAtividade);
	}

	public Integer getDuracao(String codigoAtividade) {
		return controllerGeral.getDuracao(codigoAtividade);
	}

	// -----------------------------------------------------US8---------------------------------------------------//
	public String busca(String termo) {
		return controllerGeral.getControllerBusca().buscarGeral(termo, controllerGeral);
	}

	public String busca(String termo, int numeroDoResultado) {
		return controllerGeral.getControllerBusca().buscarEspecífico(termo, numeroDoResultado, controllerGeral);
	}

	public int contaResultadosBusca(String termo) {
		return controllerGeral.getControllerBusca().contarResultado(termo, controllerGeral);
	}

	// -----------------------------------------------------------US10--------------------------------------------------//
	public void configuraEstrategia(String estrategia) {
		controllerGeral.configuraEstrategia(estrategia);
	}

	public String proximaAtividade(String codigoPesquisa) {
		return controllerGeral.proximaAtividade(codigoPesquisa);
	}
	
	public void salvar() {
	    controllerGeral.salva();
	}
	
	public void carregar() {
	    controllerGeral.carrega();
	}

}
