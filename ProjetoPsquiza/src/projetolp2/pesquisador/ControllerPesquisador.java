package projetolp2.pesquisador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import easyaccept.EasyAccept;
import projetolp2.busca.Pair;

public class ControllerPesquisador {
	private Map<String, Pesquisador> pesquisadores;
	
	private Validacao validacao;

	public static void main(String[] args) {
		args = new String[] { "Projeto/ControllerPesquisador", "Teste.use_case_2.txt" };
		EasyAccept.main(args);
	}

	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
		
		this.validacao = new Validacao();
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		validacao.validaString(nome, "Campo nome nao pode ser nulo ou vazio.");
		validacao.validaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validacao.validaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		validacao.validaString(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		validacao.validaEmail(email, "Formato de email invalido.");
		validacao.validaFotoURL(fotoURL, "Formato de foto invalido.");

		if (!ehCadastrado(email)) {
			Pesquisador novoPesquisador = new Pesquisador(nome, funcao, biografia, email, fotoURL);
			this.pesquisadores.put(email, novoPesquisador);
		} else
			throw new IllegalArgumentException("Pesquisador já cadastrado.");

	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		validacao.validaString(email, "Campo email nao pode ser vazio ou nulo.");
		validacao.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		if (ehCadastrado(email)) {
			switch (atributo) {
			case "EMAIL":
				validacao.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				validacao.validaEmail(novoValor, "Formato de email invalido.");
				if (this.pesquisadores.get(email).getStatus()) {
					Pesquisador novoPesquisador = this.pesquisadores.get(email);
					this.pesquisadores.put(novoValor, novoPesquisador);
					this.pesquisadores.get(novoValor).setEmail(novoValor);
					this.pesquisadores.remove(email);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "NOME":
				validacao.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setNome(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "FUNCAO":
				validacao.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setFuncao(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "BIOGRAFIA":
				validacao.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setBiografia(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "FOTO":
				validacao.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				validacao.validaFotoURL(novoValor, "Formato de foto invalido.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setFotoURL(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "FORMACAO":
				validacao.validaString(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setAtributo(atributo, novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "UNIDADE":
				validacao.validaString(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setAtributo(atributo, novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "DATA":
				validacao.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setAtributo(atributo, novoValor);;
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "SEMESTRE":
				validacao.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setAtributo(atributo, novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "IEA":
				validacao.validaString(novoValor, "Campo IEA nao pode ser nulo ou vazio.");
				if (this.pesquisadores.get(email).getStatus()) {
					this.pesquisadores.get(email).setAtributo(atributo, novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			default:
				throw new IllegalArgumentException("Atributo invalido.");
			}
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	private boolean ehCadastrado(String email) {
		if (!(this.pesquisadores.containsKey(email)))
			return false;
		return true;
	}

	public void desativaPesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			if (this.pesquisadores.get(email).getStatus()) {
				this.pesquisadores.get(email).setStatus(false);
			} else {
				throw new IllegalArgumentException("Pesquisador inativo.");
			}
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	public void ativaPesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			if (!pesquisadorEhAtivo(email)) {
				this.pesquisadores.get(email).setStatus(true);
			} else {
				throw new IllegalArgumentException("Pesquisador ja ativado.");
			}
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	public String exibePesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			if (this.pesquisadores.containsKey(email)) {
				return this.pesquisadores.get(email).toString();
			} else
				throw new IllegalArgumentException("Pesquisador inativo.");
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	public boolean pesquisadorEhAtivo(String email) {
		validacao.validaString(email, "Email nao pode ser vazio ou nulo.");
		if (ehCadastrado(email)) {
			if (this.pesquisadores.get(email).getStatus()) {
				return true;
			} else
				return false;
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	// --------------------------------------------US8------------------------------------------------------//
	public ArrayList<Pair> retornaBuscaGeralPesquisador(String termo) {
		String procurarPor = termo;
		ArrayList<Pair> pares = new ArrayList<Pair>();

		for (String key : pesquisadores.keySet()) {
			if (pesquisadores.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
				Pair par = new Pair(key, pesquisadores.get(key).getBiografia());
				pares.add(par);
			}
		}

//		for (String key : pesquisadoresInativos.keySet()) {
//			if (pesquisadoresInativos.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
//				Pair par = new Pair(key, pesquisadoresInativos.get(key).getBiografia());
//				pares.add(par);
//			}
//		}
		return pares;
	}

	public int contaResultadoBusca(String termo) {
		String procurarPor = termo;
		int count = 0;

		for (String key : pesquisadores.keySet()) {
			if (pesquisadores.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
				count = count + 1;
			}
		}

//		for (String key : pesquisadoresInativos.keySet()) {
//			if (pesquisadoresInativos.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
//				count = count + 1;
//			}
//		}
		return count;
	}

	public Pesquisador getPesquisador(String emailPesquisador) {
		return this.pesquisadores.get(emailPesquisador);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		validacao.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validacao.validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validacao.validaString(data, "Campo data nao pode ser nulo ou vazio.");
		validacao.validaData(data, "Atributo data com formato invalido.");
		if (!ehCadastrado(email))
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		if (!this.pesquisadores.get(email).getFuncao().getNome().equals("professor"))
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		if (pesquisadorEhAtivo(email)) {
			this.pesquisadores.get(email).especializaProfessor("professor",formacao, unidade, data);
		} else {
			throw new IllegalArgumentException("Pesquisador inativo.");

		}

	}

	public void cadastraEspecialidadeAluno(String email, Integer semestre, Double IEA) {
		String semestreS = semestre.toString();
		String IEAS = IEA.toString();
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (semestre < 1)
			throw new IllegalArgumentException("Atributo semestre com formato invalido.");
		if (IEA < 0 || IEA > 10)
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
		if (!ehCadastrado(email))
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		if (!this.pesquisadores.get(email).getFuncao().getNome().equals("estudante"))
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		if (pesquisadorEhAtivo(email)) {
			this.pesquisadores.get(email).especializaAluno("estudante",semestre, IEA);

			
		} else {
			throw new IllegalArgumentException("Pesquisador inativo.");

		}
	}

	public String listaPesquisadores(String TIPO) {
		validacao.validaString(TIPO, "Campo tipo nao pode ser nulo ou vazio.");
		Set<String> chaves = this.pesquisadores.keySet();
		String pesquisadores = "";
		if (TIPO.equals("EXTERNO")) {
			for (String chave : chaves) {
				if (this.pesquisadores.get(chave).getFuncao().getNome().equals("externo")) {
					pesquisadores += this.pesquisadores.get(chave).toString() + " | ";
				}
			}
		} else if (TIPO.equals("PROFESSORA")) {
			for (String chave : chaves) {
				if (this.pesquisadores.get(chave).getFuncao().getNome().equals("professor")) {
					pesquisadores += this.pesquisadores.get(chave).toString() + " | ";
				}
			}
		} else if (TIPO.equals("ALUNA")) {
			for (String chave : chaves) {
				if (this.pesquisadores.get(chave).getFuncao().getNome().equals("aluno")) {
					pesquisadores += this.pesquisadores.get(chave).toString() + " | ";
				}
			}

		} else {
			throw new IllegalArgumentException("Tipo " + TIPO + " inexistente.");
		}
		return pesquisadores.substring(0, pesquisadores.length() - 3);
	}
}