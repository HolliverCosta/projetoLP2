package projetolp2.pesquisa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import projetolp2.atividades.Atividade;
import projetolp2.atividades.ComparatorDuracao;
import projetolp2.atividades.ComparatorPendencias;
import projetolp2.atividades.ComparatorRisco;
import projetolp2.atividades.ControllerAtividade;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.pesquisador.Pesquisador;
import projetolp2.po.Objetivo;
import projetolp2.po.Problema;

/**
 * Pesquisa contem uma descricao, campo de interesse, codigo identificador e um
 * status predefinido como ativada
 * 
 * @author Holliver
 *
 */
public class Pesquisa implements Serializable {
	/**
	 * descricao da pesquisa
	 */
	private String descricao;
	/**
	 * campo de interesse de uma pesquisa
	 */
	private String campoDeInteresse;
	/**
	 * codigo da pesquisa
	 */
	private String codigo;
	/**
	 * status de uma pesquisa
	 */
	private String status;
	/**
	 * lista de objetivos
	 */
	private Map<String, Objetivo> objetivos;
	/**
	 * código do problema associado.
	 */
	private String idProblema;
	private Problema problema;
	/**
	 * mapa de atividades
	 */
	private Map<String, Atividade> atividades;
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Constroi uma pesquisa
	 * 
	 * @param descricao
	 * @param campoDeInteresse
	 * @param codigo
	 */
	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.status = "ativada";
		this.codigo = codigo;
		this.idProblema = "";
		this.objetivos = new HashMap<String, Objetivo>();
		this.atividades = new LinkedHashMap<String, Atividade>();
		this.pesquisadores = new LinkedHashMap<String, Pesquisador>();
		this.problema = null;

	}

	public void addObjetivo(String idObjetivo, Objetivo objetivo) {
		this.objetivos.put(idObjetivo, objetivo);
	}

	/**
	 * pega o codigo da pesquisa
	 * 
	 * @return codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	public Map<String, Objetivo> getObjetivos() {
		return objetivos;
	}

	public String getProblema() {
		return this.idProblema;
	}

	/**
	 * @return status da pesquisa
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * metodo para mudar o status da pesquisa
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * metodo para mudar a descricao da pesquisa
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * pega a descricao da pesquisa
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * pega o campo de interesse da pesquisa
	 * 
	 * @return capo de interesse
	 */
	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	/**
	 * metodo para mudar o campo de interesse da pesquisa
	 * 
	 * @param campoDeInteresse
	 */
	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public void setProblema(String idProblema) {
		this.idProblema = idProblema;
	}

	/**
	 * @return representacao textual de uma pesquisa
	 */
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}

	
	/**
	 * associa uma uma atividade a uma pesquisa
	 * 
	 * @param codigoAtividade
	 * @param controllerAtividade
	 * @return um boolean
	 */
	public boolean associaAtividade(String codigoAtividade, ControllerAtividade controllerAtividade) {
		if (!atividades.containsKey(codigoAtividade)) {
			atividades.put(codigoAtividade, controllerAtividade.getAtividade(codigoAtividade));
			atividades.get(codigoAtividade).setAssociado(true);
			return true;
		} else
			return false;
	}

	/**
	 * desassocia uma uma atividade a uma pesquisa
	 * 
	 * @param codigoAtividade
	 * @param controllerAtividade
	 * @return um boolean
	 */
	public boolean desassociaAtividade(String codigoAtividade) {
		
		if (!atividades.containsKey(codigoAtividade)) {
			return false;
		} else {
			atividades.get(codigoAtividade).setAssociado(false);
			atividades.remove(codigoAtividade);
			
			return true;
		}
	}
	/**
	 * associa um pesquisador a essa pesquisa.
	 * @param emailPesquisador
	 * @param controllerPesquisador
	 * @return true or false.
	 */
	public boolean associaPesquisador(String emailPesquisador, ControllerPesquisador controllerPesquisador) {
		if (pesquisadores.containsKey(emailPesquisador))
			return false;
		this.pesquisadores.put(emailPesquisador, controllerPesquisador.getPesquisador(emailPesquisador));
		return true;

	}
	/**
	 * desassocia um pesquisador que estava associado a essa pesquisa.
	 * @param emailPesquisador
	 * @return true or false.
	 */
	public boolean desassociaPesquisador(String emailPesquisador) {
		if (!pesquisadores.containsKey(emailPesquisador))
			return false;
		this.pesquisadores.remove(emailPesquisador);
		return true;
	}
	

	/**
	 * verifica se uma pesquisa tem atividades pendentes
	 * 
	 * @return boolean
	 */
	public boolean verificaPendencia() {
		boolean verifica = false;
		for (Atividade a : atividades.values()) {
			if (a.totalItemsPendentes() != 0) {
				verifica = true;
			}
		}
		return verifica;
	}

	/**
	 * ordena as atividades de acordo com a duracao
	 * 
	 * @return uma lista ordenada
	 */
	private ArrayList<Atividade> adicionaAtividadesOrdenadosDuracao() {
		ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());

		Collections.sort(atividadesOrdenadas, new ComparatorDuracao());

		return atividadesOrdenadas;
	}

	/**
	 * pega a atividade com menos pendencia
	 * 
	 * @return id atividade
	 */
	private String adicionaAtividadesOrdenadosPendencias() {
		ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());
		Collections.sort(atividadesOrdenadas, new ComparatorPendencias());
		String id = "";
		int menor = atividadesOrdenadas.get(0).totalItemsPendentes();
		for (Atividade atividade : atividadesOrdenadas) {
			if (atividade.totalItemsPendentes() < menor && atividade.totalItemsPendentes() > 0) {
				menor = atividade.totalItemsPendentes();
				id = atividade.getIdAtividade();
			}
		}
		return id;
	}

	/**
	 * ordena as atividades de acordo com o risco
	 * 
	 * @return uma lista ordenada
	 */
	private ArrayList<Atividade> adicionaAtividadesOrdenadosRisco() {
		ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());

		Collections.sort(atividadesOrdenadas, new ComparatorRisco());

		return atividadesOrdenadas;
	}

	/**
	 * pega a primeira atividade inserida que tenha algum item pendente
	 * 
	 * @return id atividade
	 */
	private String atividadeMaisAntiga() {

		if (atividades.entrySet().iterator().next().getValue().totalItemsPendentes() != 0)
			return atividades.entrySet().iterator().next().getValue().getIdAtividade();
		else
			for (Atividade a : atividades.values()) {
				if (a.totalItemsPendentes() > 0) {
					return a.getIdAtividade();
				}
			}
		return null;
	}

	/**
	 * sugere a proxima atividade a ser executada
	 * 
	 * @param estrategia
	 * @return id atividade
	 */
	public String proximaAtividade(String estrategia) {
		String atividade = "";
		if (estrategia.equals("MAIS_ANTIGA")) {
			atividade = atividadeMaisAntiga();
		} else if (estrategia.equals("MENOS_PENDENCIAS")) {
			atividade = adicionaAtividadesOrdenadosPendencias();
		} else if (estrategia.equals("MAIOR_RISCO")) {
			atividade = adicionaAtividadesOrdenadosRisco().get(0).getIdAtividade();
		} else if (estrategia.equals("MAIOR_DURACAO")) {
			atividade = adicionaAtividadesOrdenadosDuracao().get(0).getIdAtividade();
		}

		return atividade;

	}

	/**
	 * metodo feito pra pecorrer o mapa de pesquisadores que fazem parte da
	 * pesquisa.
	 * 
	 * @return pesquisadores
	 */
	private String exibePesquisadores() {
		Set<String> chaves = this.pesquisadores.keySet();
		String pesquisadores = "";
		for (String chave : chaves) {
			pesquisadores += "        - " + this.pesquisadores.get(chave).toString() + "\n";
		}
		return pesquisadores;
	}

	/**
	 * metodo feito pra pecorrer o mapa de objetivos que fazem parte da pesquisa.
	 * 
	 * @return objetivos
	 */
	private String exibeObjetivos() {
		Set<String> chaves = this.objetivos.keySet();
		String objetivos = "";
		for (String chave : chaves) {
			objetivos += "        - " + this.objetivos.get(chave).toString() + "\n";
		}
		return objetivos;
	}

	/**
	 * metodo feito pra pecorrer o mapa de atividades que fazem parte da pesquisa.
	 * 
	 * @return atividades
	 */
	private String exibeAtividades() {
		Set<String> chaves = this.atividades.keySet();
		String atividades = "";
		for (String chave : chaves) {
			atividades += "        - " + this.atividades.get(chave).exibeAtividade() + "\n"
					+ this.atividades.get(chave).exibeStatusItem();
		}
		return atividades;
	}

	/**
	 * metodo feito para exibir a classe Pesquisa no modo que foi exigido no metodo
	 * 'gravarResumo'.
	 * 
	 * @return resumo de pesquisa.
	 */
	public String exibeResumoPesquisa() {
		return "- Pesquisa: " + toString() + "\n" + "    - Pesquisadores:\n" + exibePesquisadores()
				+ "    - Problema:\n        - " + this.problema.toString() + "\n    - Objetivos:\n" + exibeObjetivos()
				+ "    - Atividades:\n" + exibeAtividades();
	}
	/**
	 * exibe os resultados presentes na pesquisa.
	 * @return resultados
	 */
	public String exibeResultadoPesquisa() {
			return "- Pesquisa: " + toString() + "\n" + "    - Resultados:\n" +  listaDescricoes();
		}
	/**
	 * lista a descricoes da atividade, junto com eles os itens e duracao de intens.
	 * @return lista de descricoes.
	 */
	public String listaDescricoes() {
		Set<String> chaves = this.atividades.keySet();
		String descricao = "";
		for (String chave : chaves) {
			descricao += "        - " + this.atividades.get(chave).getDescricaoAtividade() + "\n"
					+ this.atividades.get(chave).exibeDuracaoItens()
					+ this.atividades.get(chave).exibeResultadosCadastados();
		}
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * remove o problema que estava relacionado a essa pesquisa.
	 */
	public void removeProblema() {
		this.problema = null;

	}

	/**
	 * adiciona um problema relacionado a essa pesquisa.
	 * 
	 * @param novoProblema
	 */
	public void adicionaProblema(Problema novoProblema) {
		this.problema = novoProblema;

	}
}
