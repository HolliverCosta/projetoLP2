package projetolp2.usecase1;

import java.util.HashMap;

public class ControllerPesquisa {
	private HashMap<String, Pesquisa> pesquisas;
	private HashMap<String, Integer> idPesquisas;
	private Validacao validacao;

	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.validacao = new Validacao();
		this.idPesquisas = new HashMap<String, Integer>();
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {

		this.validacao.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
		this.validacao.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
		validacao.validaCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3);
		if (!idPesquisas.containsKey(codigo)) {
			idPesquisas.put(codigo, 1);

		} else {
			idPesquisas.put(codigo, idPesquisas.get(codigo) + 1);

		}

		String codigoKey = (codigo + idPesquisas.get(codigo)).toUpperCase();

		Pesquisa pesquisa = new Pesquisa(descricao, campoDeInteresse, codigoKey);

		if (!pesquisas.containsKey(codigoKey)) {

			pesquisas.put(codigoKey.toUpperCase(), pesquisa);
		}
		return codigoKey;
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {

		if (pesquisas.get(codigo).getStatus().equals("ativada")) {
			if (conteudoASerAlterado.toLowerCase().equals("descricao"))
				this.pesquisas.get(codigo).setDescricao(novoConteudo);

			else if (conteudoASerAlterado.toLowerCase().equals("campodednteresse")) {
				this.pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
			}

		}
	}

	public void encerraPesquisa(String codigo, String motivo) {
		if (pesquisas.containsKey(codigo))
			if (pesquisas.get(codigo).getStatus().equals("desativada"))
				throw new IllegalArgumentException("Pesquisa desativada.");
			else
				pesquisas.get(codigo).setStatus("desativada");
		else
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}

	public void ativaPesquisa(String codigo) {
		if (pesquisas.containsKey(codigo))
			if (pesquisas.get(codigo).getStatus().equals("ativada"))
				throw new IllegalArgumentException("Pesquisa ja ativada.");
			else
				pesquisas.get(codigo).setStatus("ativada");
		else
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}

	public String exibePesquisa(String codigo) {
		if (!pesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		return pesquisas.get(codigo).toString();
	}

	public boolean ehAtiva(String codigo) {
		validacao.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (pesquisas.get(codigo).getStatus().equals("ativada"))
			return true;
		else {
			return false;
		}
	}
}
