package projetolp2.usecase2;

import java.util.HashMap;
import java.util.Map;

import easyaccept.EasyAccept;

public class ControllerPesquisador {
	private Map<String, Pesquisador> pesquisadoresAtivos;
	private Map<String, Pesquisador> pesquisadoresInativos;
	private Validacao validacao;
	
	public static void main(String[] args) {
		args =  new String[] { "Projeto/ControllerPesquisador", "Teste.use_case_2.txt"};
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
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		validacao.validaString(atributo, "Campo atributo nao pode ser nulo ou vazio.");
		if (ehCadastrado(email)) {
			switch (atributo) {
			case "email":
				validacao.validaString(novoValor, "Campo email nao pode ser nulo ou vazio.");
				validacao.validaEmail(novoValor, "Formato de email invalido.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.put(novoValor, this.pesquisadoresAtivos.get(email));
					this.pesquisadoresAtivos.remove(email);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "nome":
				validacao.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setNome(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "funcao":
				validacao.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setFuncao(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "biografia":
				validacao.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setBiografia(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			case "fotoURL":
				validacao.validaString(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
				validacao.validaFotoURL(novoValor, "Formato de foto invalido.");
				if (this.pesquisadoresAtivos.containsKey(email)) {
					this.pesquisadoresAtivos.get(email).setFotoURL(novoValor);
				} else
					throw new IllegalArgumentException("Pesquisador inativo.");
				break;
			default:
				throw new IllegalArgumentException("Atributo n�o encontrado.");
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
		if(ehCadastrado(email)) {
			if(this.pesquisadoresAtivos.containsKey(email)) {
				this.pesquisadoresInativos.put(email, this.pesquisadoresAtivos.get(email));
				this.pesquisadoresAtivos.remove(email);
			}else {
				throw new IllegalArgumentException("Pesquisador inativo.");
			}
		}else 
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}
	
	public void ativaPesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if(ehCadastrado(email)) {
			if(this.pesquisadoresInativos.containsKey(email)) {
				this.pesquisadoresAtivos.put(email, this.pesquisadoresInativos.get(email));
				this.pesquisadoresInativos.remove(email);
			}else {
				throw new IllegalArgumentException("Pesquisador ja ativado.");
			}
		}else 
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}
	
	public String exibePesquisador(String email) {
		validacao.validaString(email, "Campo email nao pode ser nulo ou vazio.");
		if(ehCadastrado(email)) {
			if(this.pesquisadoresAtivos.containsKey(email)) {
				return this.pesquisadoresAtivos.get(email).toString();
			}else
				throw new IllegalArgumentException("Pesquisador inativo.");
		}else
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		if(ehCadastrado(email)) {
			if(this.pesquisadoresAtivos.containsKey(email)) {
				return true;
			}else
				return false;
		}else 
			throw new IllegalArgumentException("Pesquisador nao encontrado");
	}
	
	
}
