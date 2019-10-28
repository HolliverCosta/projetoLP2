package projetolp2;

public class Pesquisa {
	private String descricao, campoDeInteresse, codigo, status;

	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
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

	public int getNumCodigo() {
		return Integer.parseInt(codigo.substring(3));
	}

	public String toString() {
		return String.format("%s - %s - %s", codigo, descricao, campoDeInteresse);
	}
}
