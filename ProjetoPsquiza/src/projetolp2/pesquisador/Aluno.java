package projetolp2.pesquisador;

import java.io.Serializable;
/**
 * Classe reponsavel por representar uma funcao/especializacao de um Pesquisador.
 * @author Pedro Goncalves
 *
 */
public class Aluno implements Funcao,Serializable {
	/**
	 * nome da funcao do pesquisador.
	 */
	private String nome;
	/**
	 * semestre no qual o aluno pertence.
	 */
	private Integer semestre;
	/**
	 * representa seu indice de aprendizado na sua vida de estudante.
	 */
	private Double IEA;

	
	/**
	 * Contrutor de uma nova funcao Aluno, utilizada no metodo 'cadastraEspecialidadeAluno'.
	 * 
	 * @param string
	 * @param semestre
	 * @param IEA
	 */
	public Aluno(String string, Integer semestre, Double IEA) {
		this.nome = string;
		this.semestre = semestre;
		this.IEA = IEA;
	}

	public void setSemestre(String semestre) {
		Integer edita = Integer.parseInt(semestre);
		this.semestre = edita;
	}

	public void setIEA(String IEA) {
		Double edita = Double.parseDouble(IEA);
		this.IEA = edita;
	}

	/**
	 * representacao textual da sua funcao de estudante.
	 */
	@Override
	public String exibePesquisador() {
		if (semestre == null || IEA == null) {
			return "";
		} else {
			return " - " + semestre + "o SEMESTRE - " + IEA;

		}
	}

	@Override
	public void setAtributo(String atributo, String novoValor) {
		if (atributo.equals("SEMESTRE"))
			this.setSemestre(novoValor);
		else if (atributo.equals("IEA"))
			this.setIEA(novoValor);
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

}
