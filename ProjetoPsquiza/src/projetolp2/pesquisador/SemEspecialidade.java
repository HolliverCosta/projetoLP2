package projetolp2.pesquisador;

import java.io.Serializable;

public class SemEspecialidade implements Funcao,Serializable {
	private String nome;
	
	public SemEspecialidade(String funcao) {
		this.nome = funcao;
	}


	@Override
	public String exibePesquisador() {
		return "";
	}

	@Override
	public void setAtributo(String atributo, String novoValor) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	
	

}
