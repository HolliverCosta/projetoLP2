package projetolp2.pesquisador;

public class PesquisadorAluno extends Pesquisador {
	private Integer semestre;
	private Double IEA;
	
	public PesquisadorAluno(String nome, String funcao, String biografia, String email, String fotoURL, Integer semestre, Double IEA) {
		super(nome, funcao, biografia, email, fotoURL);
		this.semestre = semestre;
		this.IEA = IEA;
	}
	
	@Override
	public String toString() {
		if(semestre == null || IEA == null ) {
			return super.toString();
		}else {
			return super.toString() + " - " + semestre + "o SEMESTRE - " + IEA;
		}
		
	}

	public void setSemestre(String semestre) {
		Integer edita = Integer.parseInt(semestre);
		this.semestre = edita;
	}

	public void setIEA(String IEA) {
		Double edita = Double.parseDouble(IEA);
		this.IEA = edita;
	}

	
	
	
}
