package projetolp2.pesquisa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import projetolp2.atividades.ControllerAtividade;
import projetolp2.busca.Pair;
import projetolp2.misc.GeraComparador;
import projetolp2.misc.Validacao;
import projetolp2.pesquisador.ControllerPesquisador;

/**
 * Controladora de pesquisas
 * 
 * @author HolliverCosta
 *
 */
public class ControllerPesquisa implements Serializable{
    /*
     * Mapa para controlar objetivos já associados.
     */
	private HashMap<String, Boolean> objetivosAssociados;
	/*
	 * Mapa de pesquisas onde a chave e as 3 primeiras letras do campoDeInterrese e
	 * um valor inteiro e o valor e o objeto Pesquisa
	 */
	private HashMap<String, Pesquisa> pesquisas;
	/*
	 * mapa que server como contador para gerar o id de pesquisas
	 */
	private HashMap<String, Integer> idPesquisas;
	/**
	 * Chamando a classe validacao para tratar sobe atributos vazios ou nulos
	 */
	private Validacao validacao;
	/*
	 * serve para guardar qual estrategia escolher na hora de sugerir uma atividade
	 */
	private String estrategia; 
	/**
	 * Contrutor da classe
	 */
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.validacao = new Validacao();
		this.idPesquisas = new HashMap<String, Integer>();
		this.objetivosAssociados = new HashMap<String, Boolean>();
		this.estrategia  = "MAIS_ANTIGA";
	}

	/**
	 * metodo para cadastrar uma pesquisa
	 * 
	 * @param descricao
	 * @param campoDeInteresse
	 * 
	 * @return o codigo da pesquisa
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validacao.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
		validacao.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
		validacao.validaCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");

		String codigo = campoDeInteresse.substring(0, 3);

		if (!idPesquisas.containsKey(codigo))
			idPesquisas.put(codigo, 1);
		else
			idPesquisas.put(codigo, idPesquisas.get(codigo) + 1);

		String codigoKey = (codigo + idPesquisas.get(codigo)).toUpperCase();

		Pesquisa pesquisa = new Pesquisa(descricao, campoDeInteresse, codigoKey);

		if (!pesquisas.containsKey(codigoKey))
			pesquisas.put(codigoKey.toUpperCase(), pesquisa);

		return codigoKey;
	}

	/**
	 * metodo que altera uma pesquisa, so pode alterar descricao e campo de
	 * interesse
	 * 
	 * @param codigo
	 * @param conteudoASerAlterado
	 * @param novoConteudo
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if (verificaExistePesquisa(codigo) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigo) == false)
			throw new IllegalArgumentException("Pesquisa desativada.");

		if (conteudoASerAlterado.equals("DESCRICAO")) {
			validacao.validaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			this.pesquisas.get(codigo).setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("CAMPO")) {
			validacao.validaString(novoConteudo, "Formato do campo de interesse invalido.");
			this.pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		} else
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");

	}

	/**
	 * metodo que muda os status da pesquisa para desativada
	 * 
	 * @param codigo
	 * @param motivo
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		validacao.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if (verificaExistePesquisa(codigo) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigo) == false)
			throw new IllegalArgumentException("Pesquisa desativada.");

		pesquisas.get(codigo).setStatus("desativada");

	}

	/**
	 * metodo que muda os status da pesquisa para ativada
	 * 
	 * @param codigo
	 */
	public void ativaPesquisa(String codigo) {
		if (verificaExistePesquisa(codigo) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigo) == true)
			throw new IllegalArgumentException("Pesquisa ja ativada.");

		pesquisas.get(codigo).setStatus("ativada");

	}

	/**
	 * metodo para exibir uma pesquisa
	 * 
	 * @param codigo
	 * @return representacao de uma pesquisa
	 */
	public String exibePesquisa(String codigo) {
		if (verificaExistePesquisa(codigo) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");

		return pesquisas.get(codigo).toString();
	}

	/**
	 * metodo para verificar se a pesquisa esta ativada
	 * 
	 * @param codigo
	 * @return true para ativada e false para desativada
	 */
	public boolean ehAtiva(String codigo) {
		validacao.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (verificaExistePesquisa(codigo) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		boolean msg = false;
		if (pesquisas.get(codigo).getStatus().equals("ativada"))
			msg = true;
		else if (pesquisas.get(codigo).getStatus().equals("desativada"))
			msg = false;

		return msg;
	}

	/**
	 * metodo para verificar se existe uma pesquisa cadastrada
	 * 
	 * @param codigo
	 * @return true se existir
	 */
	private boolean verificaExistePesquisa(String codigo) {
		return pesquisas.containsKey(codigo);
	}
	/**
	 * Associa um problema identificado pelo idProblema a uma pesquisa identificada por idPesquisa.
	 * 
	 * @param idPesquisa código da pesquisa
	 * @param idProblema código do problema
	 * @return true ou false. 
	 * @throws IllegalArgumentException Exceção lançada caso a pesquisa nao exista, esteja desativada ou ja esteja associada a um problema.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) throws IllegalArgumentException {
		if (!verificaExistePesquisa(idPesquisa))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		Pesquisa temp = this.pesquisas.get(idPesquisa);
		if (temp.getProblema().equals(idProblema))
			return false;
		if (!temp.getProblema().isEmpty())
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");

		temp.setProblema(idProblema);
		return true;
	}
	/**
	 * Desassocia o problema associado a pesquisa identificad pro idPesquisa.
	 * 
	 * @param idPesquisa código da pesquisa
	 * @return ture ou false.
	 * @throws IllegalArgumentException Exceção lançada caso a pesquisa nao exista ou esteja desativada.
	 */
	public boolean desassociaProblema(String idPesquisa) throws IllegalArgumentException {
		if (!verificaExistePesquisa(idPesquisa))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		Pesquisa temp = this.pesquisas.get(idPesquisa);
		if (temp.getProblema().isEmpty())
			return false;
		temp.setProblema("");
		return true;
	}
	/**
	 * Associa um objetivo identificado por idObjetivo a uma pesquisa identificada por idPesquisa.
	 * 
	 * @param idPesquisa código da pesquisa
	 * @param idObjetivo código do objetivo
	 * @return true ou false.
	 * @throws IllegalArgumentException Exceção lançada caso a pesquisa nao exista, esteja desativada ou objetivo ja esteja associado a uma pesquisa.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) throws IllegalArgumentException{
		if (!verificaExistePesquisa(idPesquisa))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		Pesquisa temp = this.pesquisas.get(idPesquisa);
		if (temp.getObjetivos().contains(idObjetivo))
			return false;
		if (this.objetivosAssociados.containsKey(idObjetivo) && this.objetivosAssociados.get(idObjetivo))
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		this.objetivosAssociados.put(idObjetivo, true);
		temp.addObjetivo(idObjetivo);
		return true;
	}
	/**
	 * Desassocia um objetivo identificado por idObjetivo de uma pesquisa identificada por idPesquisa.
	 * 
	 * @param idPesquisa código da pesquisa
	 * @param idObjetivo código do objetivo
	 * @return true ou false.
	 * @throws IllegalArgumentException Exceção lançada caso a pesquisa nao exista ou esteja desativada.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) throws IllegalArgumentException{
		if (!verificaExistePesquisa(idPesquisa))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		Pesquisa temp = this.pesquisas.get(idPesquisa);
		if (!temp.getObjetivos().contains(idObjetivo))
			return false;
		temp.getObjetivos().remove(idObjetivo);
		this.objetivosAssociados.replace(idObjetivo, false);
		return true;
	}

	private String geraStringOrdenada(List<Pesquisa> l1, List<Pesquisa> l2, Comparator<Pesquisa> comparador) {
		String output = "";
		Collections.sort(l1, comparador);
		Collections.sort(l2, new Comparator<Pesquisa>() {
			@Override
			public int compare(Pesquisa p0, Pesquisa p1) {
				return p1.getCodigo().compareTo(p0.getCodigo());
			}
		});
		l1.addAll(l2);
		for (Pesquisa p : l1) {
			if (l1.indexOf(p) == l1.size() - 1)
				output += p.toString();
			else
				output += p.toString() + " | ";
		}
		return output;
	}
	/**
	 * Retorna uma String contendo todas as pesquisas ordenadas a partir do critério ordem(PROBLEMA,OBJETIVOS,PESQUISA).
	 * 
	 * @param ordem criterio de ordenaçao
	 * @return String contendo todas as pesquisas ordenadas
	 */
	public String listaPesquisas(String ordem) {
		if (!ordem.equals("PROBLEMA") && !ordem.equals("OBJETIVOS") && !ordem.equals("PESQUISA"))
			throw new IllegalArgumentException("Valor invalido da ordem");

		String output = "";
		List<Pesquisa> comCriterio = new ArrayList<Pesquisa>();
		List<Pesquisa> semCriterio = new ArrayList<Pesquisa>();
		Comparator<Pesquisa> comparador = GeraComparador.geraComparador(ordem);
		if (ordem.equals("PROBLEMA")) {
			comCriterio.addAll(this.pesquisas.values().stream().filter(x -> !x.getProblema().isEmpty())
					.collect(Collectors.toList()));
			semCriterio.addAll(this.pesquisas.values().stream().filter(x -> x.getProblema().isEmpty())
					.collect(Collectors.toList()));
		} else if (ordem.equals("OBJETIVOS")) {
			comCriterio.addAll(this.pesquisas.values().stream().filter(x -> !x.getObjetivos().isEmpty())
					.collect(Collectors.toList()));
			semCriterio.addAll(this.pesquisas.values().stream().filter(x -> x.getObjetivos().isEmpty())
					.collect(Collectors.toList()));
		} else {
			comCriterio.addAll(this.pesquisas.values());
		}
		output = geraStringOrdenada(comCriterio, semCriterio, comparador);
		return output;
	}

	// ----------------------------------------US7------------------------------------------------------//
	/**
	 * associa uma uma atividade a uma pesquisa
	 * @param codigoPesquisa
	 * @param codigoAtividade
	 * @param controllerAtividade
	 * @return um boolean
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade, ControllerAtividade controllerAtividade) {
		if (verificaExistePesquisa(codigoPesquisa) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigoPesquisa) == false)
			throw new IllegalArgumentException("Pesquisa desativada.");
		return pesquisas.get(codigoPesquisa).associaAtividade(codigoAtividade, controllerAtividade);
	}
	/**
	 * desassocia uma uma atividade a uma pesquisa
	 * @param codigoPesquisa
	 * @param codigoAtividade
	 * @param controllerAtividade
	 * @return um boolean
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		if (verificaExistePesquisa(codigoPesquisa) == false)
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigoPesquisa) == false)
			throw new IllegalArgumentException("Pesquisa desativada.");
		return pesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}

	// --------------------------------------US8-----------------------------------------------------/-/
	public ArrayList<Pair> retornaBuscaGeralPesquisa(String termo) {
		String procurarPor = termo;

		ArrayList<Pair> pares = new ArrayList<Pair>();

		for (String key : pesquisas.keySet()) {
			if (pesquisas.get(key).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
				Pair par = new Pair(key, pesquisas.get(key).getDescricao());
				pares.add(par);
			}

			if (pesquisas.get(key).getCampoDeInteresse().toLowerCase().contains(procurarPor.toLowerCase())) {
				Pair par = new Pair(key, pesquisas.get(key).getCampoDeInteresse());
				pares.add(par);
			}
		}
		return pares;
	}

	public int contaResultadoBusca(String termo) {
		String procurarPor = termo;
		int count = 0;

		for (String key : pesquisas.keySet()) {
			if (pesquisas.get(key).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
				count = count + 1;
			}

			if (pesquisas.get(key).getCampoDeInteresse().toLowerCase().contains(procurarPor.toLowerCase())) {
				count = count + 1;
			}
		}
		return count;
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador,ControllerPesquisador controllerPesquisador) {
		validacao.validaString(idPesquisa,"Campo idPesquisa nao pode ser nulo ou vazio.");
		validacao.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");
		if (!controllerPesquisador.pesquisadorEhAtivo(emailPesquisador))
			throw new IllegalArgumentException();
		return this.pesquisas.get(idPesquisa).associaPesquisador(emailPesquisador, controllerPesquisador);

	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validacao.validaString(idPesquisa,"Campo idPesquisa nao pode ser nulo ou vazio.");
		validacao.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");
		return this.pesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
	}
	//----------------------------------------------US10------------------------------------------------------//
	/**
	 * pega a estrategia para sugerir a proxima atividade
	 * @return estrtegia
	 */
	public String getEstrategia() {
		return estrategia;
	}
	/**
	 * muda a estrategia
	 * @param estrategia
	 */
	public void setEstrategia(String estrategia) {
			this.estrategia = estrategia;
		
	}
	/**
	 * sugere a proxima atividade de acordo com a estrategia
	 * @param codigoPesquisa
	 * @return idAtividade
	 */
	public String proximaAtividade(String codigoPesquisa) {
		if(ehAtiva(codigoPesquisa)==false)	throw new IllegalArgumentException("Pesquisa desativada.");
		if(pesquisas.get(codigoPesquisa).verificaPendencia()==false)throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
    	return pesquisas.get(codigoPesquisa).proximaAtividade(getEstrategia());
	}

}
