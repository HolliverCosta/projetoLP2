package projetolp2.Psquiza;

import projetolp2.atividades.ControllerAtividade;
import projetolp2.busca.ControllerBusca;
import projetolp2.misc.ValidaCampos;
import projetolp2.pesquisa.ControllerPesquisa;
import projetolp2.pesquisa.Validacao;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.po.ControllerPO;

public class Psquiza {
	private ControllerAtividade controllerAtividade;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerPO controllerPO;
	private ControllerBusca controllerBusca;
	private Validacao validacao;
	public Psquiza() {	
		this.controllerAtividade = new ControllerAtividade();
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerPesquisador = new ControllerPesquisador();
		this.controllerPO = new ControllerPO();	
		this.controllerBusca = new ControllerBusca();
		this.validacao = new Validacao();
	}
	public ControllerAtividade getControllerAtividade() {
		return this.controllerAtividade;
	}
	
	public ControllerPesquisa getControllerPesquisa() {
		return this.controllerPesquisa;
	}
	public ControllerPesquisador getControllerPesquisador() {
		return this.controllerPesquisador;
	}
	public ControllerPO getControllerPO() {
		return this.controllerPO;
	}
	public ControllerBusca getControllerBusca() {
		return controllerBusca;
	}
	//-------------------------------------------------US5---------------------------------------------------------//
	public boolean associaProblema(String idPesquisa, String idProblema) {
	       ValidaCampos.validaCamposString(new String[] {idPesquisa, idProblema},
	                new String[] {"idPesquisa", "idProblema"});
	    if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    return this.controllerPesquisa.associaProblema(idPesquisa, idProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
	       ValidaCampos.validaCamposString(new String[] {idPesquisa, idProblema},
	                new String[] {"idPesquisa", "idProblema"});
	    if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    return this.controllerPesquisa.desassociaProblema(idPesquisa, idProblema);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
	    ValidaCampos.validaCamposString(new String[] {idPesquisa, idObjetivo},
                new String[] {"idPesquisa", "idObjetivo"});
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
	    return this.controllerPesquisa.associaObjetivo(idPesquisa, idObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
	    ValidaCampos.validaCamposString(new String[] {idPesquisa, idObjetivo},
                new String[] {"idPesquisa", "idObjetivo"});
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
	    return this.controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validacao.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(controllerAtividade.verificaExisteAtividade(codigoAtividade)==false)throw new IllegalArgumentException("Atividade nao encontrada");
		return controllerPesquisa.associaAtividade(codigoPesquisa, codigoAtividade,controllerAtividade);
	}
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validacao.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(controllerAtividade.verificaExisteAtividade(codigoAtividade)==false)throw new IllegalArgumentException("Atividade nao encontrada");
		return controllerPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}
	public void executaAtividade(String codigoAtividade, Integer item, Integer duracao) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validacao.validaInteiro(item, "Item nao pode ser nulo ou negativo.");
		validacao.validaInteiro(duracao, "Duracao nao pode ser nula ou negativa.");
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}
	public Integer cadastraResultado(String codigoAtividade, String resultado) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validacao.validaString(resultado, "Resultado nao pode ser nulo ou vazio.");
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}
	public boolean removeResultado(String codigoAtividade, Integer numeroResultado) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validacao.validaInteiro(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}
	public String listaResultados(String codigoAtividade) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.listaResultados(codigoAtividade);
	}
	public Integer getDuracao(String codigoAtividade) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.getDuracao(codigoAtividade);
	}
	public void configuraEstrategia(String estrategia) {
		validacao.validaString(estrategia, "Estrategia nao pode ser nula ou vazia.");
		validacao.validaEstrategia(estrategia, "Valor invalido da estrategia");
		controllerPesquisa.setEstrategia(estrategia);
	}
	public String proximaAtividade(String codigoPesquisa) {
		validacao.validaString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		
		return controllerPesquisa.proximaAtividade(codigoPesquisa);
	}
}
