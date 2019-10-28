package projetolp2;

import java.util.HashMap;

public class ControllerPesquisa {
	private HashMap<String, Pesquisa> pesquisas;
	private Validacao validacao;
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.validacao = new Validacao();
	}
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validacao.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
		validacao.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 2);
		Pesquisa pesquisa = new Pesquisa(descricao, campoDeInteresse, codigo);
		if(!pesquisas.containsKey(codigo))
			pesquisas.put(codigo, pesquisa);
//		else 
//			throw new IllegalArgumentException("Erro no cadastro do cliente: cliente ja existe.");
	
		return codigo;
	}
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if(pesquisas.get(codigo).getStatus().equals("ativada")){
				if(conteudoASerAlterado.toLowerCase().equals("descricao")) 
					pesquisas.get(codigo).setDescricao(novoConteudo);
				else if(conteudoASerAlterado.toLowerCase().equals("campodednteresse"))
					pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		}
	}
	public void encerraPesquisa(String codigo, String motivo) {
		pesquisas.get(codigo).setStatus("desativada");	
	}
	public void ativaPesquisa(String codigo) {
		pesquisas.get(codigo).setStatus("ativada");
	}
	public String exibePesquisa(String codigo) {
		return pesquisas.get(codigo).toString();
	}
	public boolean ehAtiva(String codigo) {
		if(pesquisas.get(codigo).getStatus().equals("ativada")) {
			return true;
		}else {
			return false;
		}
	}
}
