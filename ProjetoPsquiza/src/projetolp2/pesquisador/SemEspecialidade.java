package projetolp2.pesquisador;

import java.io.Serializable;
/**
 * Classe reponsavel por representar uma funcao/especializacao de um Pesquisador.
 * @author Pedro Goncalves
 *
 */
public class SemEspecialidade implements Funcao,Serializable {
	/**
	 * String do nome de sua funcao.
	 */
	private String nome;
	/**
	 * contrutor de uma funcao em que nao ha especialidade.
	 * @param funcao
	 */
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
