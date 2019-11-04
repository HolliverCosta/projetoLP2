package projetolp2.pesquisa;

import java.util.ArrayList;
import java.util.List;

/**
 * Pesquisa contem uma descricao, campo de interesse, codigo identificador e um status predefinido como ativada
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
	/**
	 * Constroi uma pesquisa
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
	}
	
	public void addObjetivo(String idObjetivo) {
	    this.objetivos.add(idObjetivo);
	}
	
	public String getCodigo() {
	    return this.codigo;
	}
	
	public List<String> getObjetivos(){
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
	 *metodo para mudar o status da pesquisa
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * metodo para mudar a descricao da pesquisa
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * metodo para mudar o campo de interesse da pesquisa
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
}
