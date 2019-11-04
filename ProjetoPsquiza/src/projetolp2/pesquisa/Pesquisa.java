package projetolp2.usecase1;
/**
 * Pesquisa contem uma descricao, campo de interesse, codigo identificador e um status predefinido como ativada
 * @author Holindo
 *
 */
public class Pesquisa {
	private String descricao, campoDeInteresse, codigo, status;
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


	/**
	 * @return representacao textual de uma pesquisa
	 */
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}
}
