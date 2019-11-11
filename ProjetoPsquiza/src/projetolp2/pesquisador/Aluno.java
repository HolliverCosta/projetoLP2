package projetolp2.pesquisador;

public class Aluno implements Funcao {
	private String nome;
	private Integer semestre;
	private Double IEA;

	

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
