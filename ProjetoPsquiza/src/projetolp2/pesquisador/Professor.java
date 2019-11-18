package projetolp2.pesquisador;

import java.io.Serializable;

public class Professor implements Funcao,Serializable {
	/**
	 * representa o nome da funcao exercidade, no caso professor.
	 */
	private String nome;
	/**
	 * formacao obtida pelo pesquisador.
	 */
	private String formacao;
	private String unidade;
	private String data;
	/**
	 * Construtor de uma nova funcao de um pesquisador.
	 * @param funcao
	 * @param formacao
	 * @param unidade
	 * @param data
	 */
	public Professor(String funcao, String formacao, String unidade, String data) {
		this.nome = funcao;
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
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
	/**
	 * representacao textual da funcao.
	 */
	@Override
	public String exibePesquisador() {
		// TODO Auto-generated method stub
		if(formacao.isEmpty() || unidade.isEmpty() || data.isEmpty() || data == null || unidade == null || formacao == null) {
			return "";
		}else {
			return " - " + formacao + " - " + unidade + " - " + data;

		}
	}
	public void setAtributo(String atributo, String novoValor) {
		if(atributo.equals("FORMACAO")) 
			this.setFormacao(novoValor);
		else if(atributo.equals("UNIDADE"))
			this.setUnidade(novoValor);
		else if(atributo.equals("DATA"))
			this.setData(novoValor);
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}
	
	
}
