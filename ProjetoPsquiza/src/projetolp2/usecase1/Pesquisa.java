package projetolp2.usecase1;

public class Pesquisa {
	private String descricao, campoDeInteresse, codigo, status;

	public Pesquisa(String descricao, String campoDeInteresse) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.status = "ativada";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}



	public String toString() {
		return String.format("%s - %s - %s", codigo, descricao, campoDeInteresse);
	}
}
