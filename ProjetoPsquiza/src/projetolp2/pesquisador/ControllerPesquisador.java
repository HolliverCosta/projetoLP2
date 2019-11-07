package projetolp2.pesquisador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import easyaccept.EasyAccept;
import projetolp2.busca.Pair;

public class ControllerPesquisador {
	private Map<String, Pesquisador> pesquisadoresAtivos;
	private Map<String, Pesquisador> pesquisadoresInativos;
	private Validacao validacao;

	public static void main(String[] args) {
		args = new String[] { "Projeto/ControllerPesquisador", "Teste.use_case_2.txt" };
		EasyAccept.main(args);
	}

	public ControllerPesquisador() {
		this.pesquisadoresAtivos = new HashMap<String, Pesquisador>();
		this.pesquisadoresInativos = new HashMap<String, Pesquisador>();
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
			this.pesquisadoresAtivos.put(email, novoPesquisador);
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
				if (this.pesquisadoresAtivos.containsKey(email)) {
					Pesquisador novoPesquisador = this.pesquisadoresAtivos.get(email);
					this.pesquisadoresAtivos.put(novoValor, novoPesquisador);
					this.pesquisadoresAtivos.get(novoValor).setEmail(novoValor);
					this.pesquisadoresAtivos.remove(email);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "NOME":
				validacao.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setNome(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "FUNCAO":
				validacao.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setFuncao(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "BIOGRAFIA":
				validacao.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setBiografia(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "FOTO":
				validacao.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				validacao.validaFotoURL(novoValor, "Formato de foto invalido.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setFotoURL(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "FORMACAO":
				validacao.validaString(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					((PesquisadorProfessor) pesquisadoresAtivos.get(email)).setFormacao(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "UNIDADE":
				validacao.validaString(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					((PesquisadorProfessor) this.pesquisadoresAtivos.get(email)).setUnidade(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "DATA":
				validacao.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					((PesquisadorProfessor) this.pesquisadoresAtivos.get(email)).setData(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "SEMESTRE":
				validacao.validaString(novoValor, "Campo data nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					((PesquisadorAluno) this.pesquisadoresAtivos.get(email)).setSemestre(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "IEA":
				validacao.validaString(novoValor, "Campo IEA nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					((PesquisadorAluno) this.pesquisadoresAtivos.get(email)).setIEA(novoValor);
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
		if (!(this.pesquisadoresAtivos.containsKey(email) || this.pesquisadoresInativos.containsKey(email)))
			return false;
		return true;
	}

	public void desativaPesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			if (this.pesquisadoresAtivos.containsKey(email)) {
				this.pesquisadoresInativos.put(email, this.pesquisadoresAtivos.get(email));
				this.pesquisadoresAtivos.remove(email);
			} else {
				throw new IllegalArgumentException("Pesquisador inativo.");
			}
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	public void ativaPesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			if (this.pesquisadoresInativos.containsKey(email)) {
				this.pesquisadoresAtivos.put(email, this.pesquisadoresInativos.get(email));
				this.pesquisadoresInativos.remove(email);
			} else {
				throw new IllegalArgumentException("Pesquisador ja ativado.");
			}
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	public String exibePesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			if (this.pesquisadoresAtivos.containsKey(email)) {
				return this.pesquisadoresAtivos.get(email).toString();
			} else
				throw new IllegalArgumentException("Pesquisador inativo.");
		} else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}

	public boolean pesquisadorEhAtivo(String email) {
		validacao.validaString(email, "Email nao pode ser vazio ou nulo.");
		if (ehCadastrado(email)) {
			if (this.pesquisadoresAtivos.containsKey(email)) {
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

		for (String key : pesquisadoresAtivos.keySet()) {
			if (pesquisadoresAtivos.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
				Pair par = new Pair(key, pesquisadoresAtivos.get(key).getBiografia());
				pares.add(par);
			}
		}

		for (String key : pesquisadoresInativos.keySet()) {
			if (pesquisadoresInativos.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
				Pair par = new Pair(key, pesquisadoresInativos.get(key).getBiografia());
				pares.add(par);
			}
		}
		return pares;
	}

	public int contaResultadoBusca(String termo) {
		String procurarPor = termo;
		int count = 0;

		for (String key : pesquisadoresAtivos.keySet()) {
			if (pesquisadoresAtivos.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
				count = count + 1;
			}
		}

		for (String key : pesquisadoresInativos.keySet()) {
			if (pesquisadoresInativos.get(key).getBiografia().toLowerCase().contains(procurarPor.toLowerCase())) {
				count = count + 1;
			}
		}
		return count;
	}

	public Pesquisador getPesquisador(String emailPesquisador) {
		return this.pesquisadoresAtivos.get(emailPesquisador);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		validacao.validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validacao.validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validacao.validaString(data, "Campo data nao pode ser nulo ou vazio.");
		validacao.validaData(data, "Atributo data com formato invalido.");
		if (!ehCadastrado(email))
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		if (!this.pesquisadoresAtivos.get(email).getFuncao().equals("professor"))
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		if (pesquisadorEhAtivo(email)) {
			Pesquisador pesquisadorProfessor = new PesquisadorProfessor(this.pesquisadoresAtivos.get(email).getNome(),
					this.pesquisadoresAtivos.get(email).getFuncao(), this.pesquisadoresAtivos.get(email).getBiografia(),
					this.pesquisadoresAtivos.get(email).getEmail(), this.pesquisadoresAtivos.get(email).getFotoURL(),
					formacao, unidade, data);
			this.pesquisadoresAtivos.remove(email);
			this.pesquisadoresAtivos.put(email, pesquisadorProfessor);
		} else {
			throw new IllegalArgumentException("Pesquisador inativo.");

		}

	}

	public void cadastraEspecialidadeAluno(String email, Integer semestre, Double IEA) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if (semestre < 1)
			throw new IllegalArgumentException("Atributo semestre com formato invalido.");
		if (IEA < 0 || IEA > 10)
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
		if (!ehCadastrado(email))
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		if (!this.pesquisadoresAtivos.get(email).getFuncao().equals("estudante"))
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		if (pesquisadorEhAtivo(email)) {
			Pesquisador pesquisadorAluno = new PesquisadorAluno(this.pesquisadoresAtivos.get(email).getNome(),
					this.pesquisadoresAtivos.get(email).getFuncao(), this.pesquisadoresAtivos.get(email).getBiografia(),
					this.pesquisadoresAtivos.get(email).getEmail(), this.pesquisadoresAtivos.get(email).getFotoURL(),
					semestre, IEA);
			this.pesquisadoresAtivos.remove(email);
			this.pesquisadoresAtivos.put(email, pesquisadorAluno);
		} else {
			throw new IllegalArgumentException("Pesquisador inativo.");

		}
	}

	public String listaPesquisadores(String TIPO) {
		validacao.validaString(TIPO, "Campo tipo nao pode ser nulo ou vazio.");
		Set<String> chaves = this.pesquisadoresAtivos.keySet();
		String pesquisadores = "";
		if (TIPO.equals("EXTERNO")) {
			for (String chave : chaves) {
				if (this.pesquisadoresAtivos.get(chave).getFuncao().equals("externo")) {
					pesquisadores += this.pesquisadoresAtivos.get(chave).toString() + " | ";
				}
			}
		} else if (TIPO.equals("PROFESSORA")) {
			for (String chave : chaves) {
				if (this.pesquisadoresAtivos.get(chave).getFuncao().equals("professor")) {
					pesquisadores += ((PesquisadorProfessor) this.pesquisadoresAtivos.get(chave)).toString() + " | ";
				}
			}
		} else if (TIPO.equals("ALUNA")) {
			for (String chave : chaves) {
				if (this.pesquisadoresAtivos.get(chave).getFuncao().equals("aluno")) {
					pesquisadores += ((PesquisadorAluno) this.pesquisadoresAtivos.get(chave)).toString() + " | ";
				}
			}

		} else {
			throw new IllegalArgumentException("Tipo " + TIPO + " inexistente.");
		}
		return pesquisadores.substring(0, pesquisadores.length() - 3);
	}
}