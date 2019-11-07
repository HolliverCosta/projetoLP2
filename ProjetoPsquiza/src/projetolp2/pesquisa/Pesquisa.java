package projetolp2.pesquisa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.pesquisador.Pesquisador;

/**
 * Pesquisa contem uma descricao, campo de interesse, codigo identificador e um
 * status predefinido como ativada
 * 
 * @author Holindo
 *
 */
public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private String status;
	private List<String> objetivos;
	private String idProblema;
	private HashSet<String> atividades;
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
		this.objetivos = new ArrayList<String>();
		this.atividades = new HashSet<>();
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}

	public void addObjetivo(String idObjetivo) {
		this.objetivos.add(idObjetivo);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public List<String> getObjetivos() {
		return this.objetivos;
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

	public String getDescricao() {
		return descricao;
	}

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

	// ----------------------------------------------US7---------------------------------------------------//
	public boolean associaAtividade(String codigoAtividade) {
		if (!atividades.contains(codigoAtividade)) {
			atividades.add(codigoAtividade);
			return true;
		} else
			return false;
	}

	public boolean desassociaAtividade(String codigoAtividade) {
		if (!atividades.contains(codigoAtividade)) {
			return false;
		} else
			atividades.remove(codigoAtividade);
		return true;
	}

	public boolean associaPesquisador(String emailPesquisador, ControllerPesquisador controllerPesquisador) {
		if (pesquisadores.containsKey(emailPesquisador))
			return false;
		this.pesquisadores.put(emailPesquisador, controllerPesquisador.getPesquisador(emailPesquisador));
		return true;

	}

	public boolean desassociaPesquisador(String emailPesquisador) {
		if (!pesquisadores.containsKey(emailPesquisador))
			return false;
		this.pesquisadores.remove(emailPesquisador);
		return true;
	}
}
