package projetolp2.pesquisador;

public  class Pesquisador {

	private String nome;
	private boolean status;
	private Funcao funcao;
	private String biografia;
	private String email;
	private String fotoURL;

	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.nome = nome;
		this.funcao = new SemEspecialidade(funcao);
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.status = true;
	}

	@Override
	public String toString() {
		return  nome + " (" + funcao.getNome() + ") - " + biografia + " - " + email + " - " + fotoURL + this.funcao.exibePesquisador();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFuncao(String novoValor) {
		this.funcao = new SemEspecialidade(novoValor);
	}

	public String getNome() {
		return nome;
	}
	public boolean getStatus() {
		return this.status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public String getEmail() {
		return email;
	}

	public String getFotoURL() {
		return fotoURL;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	public void setAtributo(String atributo, String novoValor) {
		this.funcao.setAtributo(atributo, novoValor);
	}

	

	public void especializaProfessor(String string, String formacao, String unidade, String data) {
		this.funcao = new Professor(string, formacao, unidade, data);
		
	}

	public void especializaAluno(String string, Integer semestre, Double IEA) {
		this.funcao = new Aluno(string , semestre, IEA);
		
	}

}
