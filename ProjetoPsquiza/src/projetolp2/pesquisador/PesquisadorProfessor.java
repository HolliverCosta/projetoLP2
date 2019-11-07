package projetolp2.pesquisador;

public class PesquisadorProfessor extends Pesquisador {
	
	private String formacao, unidade, data;

	public PesquisadorProfessor(String nome, String funcao, String biografia, String email, String fotoURL, String formacao, String unidade, String data) {
		super(nome, funcao, biografia, email, fotoURL);
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}
	@Override
	public String toString() {
		if(formacao.isEmpty() || unidade.isEmpty() || data.isEmpty()) {
			return super.toString();
		}else {
			return super.toString() +  " - " + formacao + " - " + unidade + " - " + data;

		}
		
			}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
