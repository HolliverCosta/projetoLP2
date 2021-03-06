package projetolp2.atividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.Serializable;
import projetolp2.busca.Pair;

/**
 * Representação de uma Atividade Metodológica. Todoa Atividade possui um ID
 * (códido), uma descrição da Atividade, um nível de risco, uma descrição do
 * nível de risco e uma duração (em dias).
 * 
 * @author caiom
 */
public class Atividade implements Serializable {

	/**
	 * Código da atividade;
	 */
	private String idAtividade;
	/**
	 * Descrição da Atividade;
	 */
	private String descricaoAtividade;
	/**
	 * Nível de risco da Atividade;
	 */
	private String nivelRisco;
	/**
	 * Descrição do risco;
	 */
	private String descricaoRisco;
	/**
	 * Duração de uma Atividade;
	 */
	private Integer duracao;

	/**
	 * Conjunto de IDs que precedem esta atividade;
	 */
	private ArrayList<String> idPrecedentes;

	/**
	 * ID da atividade que vem após esta;
	 */
	private String idSubsequente;

	/**
	 * Lista dos resultados esperados.
	 */
	private ArrayList<Item> resultadosEsperados = new ArrayList<Item>();
	/**
	 * mapa que guarda os resultados de uma atividade
	 */
	private HashMap<Integer, String> resultados;
	/*
	 * contador para gerar o id dos resultados
	 */

	private int contadorResultado;
	/**
	 * variavel para saber se uma atividade esta associada com alguma pesquisa
	 */
	private boolean associado;

	/**
	 * Constrói uma Atividade, a partir da sua descricao, do nível de risco, da
	 * descrição do Risco e de um ID:número de identificação para formar o código.
	 * 
	 * @param descricaoAtividade
	 * @param nivelRisco
	 * @param descricaoRisco
	 * @param iD
	 */
	public Atividade(String descricaoAtividade, String nivelRisco, String descricaoRisco, String iD) {
		this.descricaoAtividade = descricaoAtividade;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.idAtividade = iD;
		this.duracao = 0;
		this.resultados = new HashMap<>();
		this.contadorResultado = 0;
		this.idPrecedentes = new ArrayList<String>();
		this.idSubsequente = null;
		this.associado = false;
	}

	// --------------------------------------------------------------ITEM-------------------------------------------------------------//

	/**
	 * Cadasta um item na lista de resultados esperados;
	 * 
	 * @param descricaoItem
	 */
	public void cadastroItem(String descricaoItem) {
		Item item = new Item(descricaoItem);
		resultadosEsperados.add(item);
	}

	/**
	 * Altera o status de um item de pendente para realizado;
	 * 
	 * @param descricaoItem
	 */
	public void alteraStatusItem(String descricaoItem) {
		for (Item item : resultadosEsperados) {
			if (item.getDescricao().equals(descricaoItem)) {
				item.setStatus("REALIZADO");
			}
		}
	}

	/**
	 * Conta quantos itens pedentens existem na lista;
	 * 
	 * @return
	 */
	public int totalItemsPendentes() {
		int count = 0;
		for (int i = 0; i < resultadosEsperados.size(); i++) {
			if (resultadosEsperados.get(i).getStatus().equals("PENDENTE")) {
				count = count + 1;
			}
		}
		return count;
	}

	/**
	 * Conta quantos itens realizados existem na lista;
	 * 
	 * @return
	 */
	public int totalItemsRealizados() {
		int count = 0;
		for (int i = 0; i < resultadosEsperados.size(); i++) {
			if (resultadosEsperados.get(i).getStatus().equals("REALIZADO")) {
				count = count + 1;
			}
		}
		return count;
	}

	/**
	 * exibe os itens e suas respectivas duracoes.
	 * 
	 * @return duracoes dos itens
	 */
	public String exibeDuracaoItens() {
		String duracaoItens = "";
		for (int i = 0; i < this.resultadosEsperados.size(); i++) {

			if (!this.resultadosEsperados.isEmpty()) {
				if (this.resultadosEsperados.get(i).getDuracao() != 0) {
					duracaoItens += "            - ITEM" + (i + 1) + " - "
							+ this.resultadosEsperados.get(i).getDuracao() + "\n";
				}

			}
		}
		return duracaoItens;
	}

	/**
	 * Exibe todos os status dos resultados existentes;
	 * 
	 * @return status dos itens.
	 */
	public String exibeStatusItem() {

		String resultados = "";
		for (int i = 0; i < this.resultadosEsperados.size(); i++) {

			if (!this.resultadosEsperados.isEmpty()) {
				resultados += "            - " + this.resultadosEsperados.get(i).getStatus() + " - ITEM" + (i + 1)
						+ "\n";

			}
		}
		return resultados;
	}

	// ------------------------------------------------------------toString-----------------------------------------------//
	@Override
	public String toString() {
		String conc = "";
		if (resultadosEsperados.isEmpty()) {
			conc = this.descricaoAtividade + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")";

		} else {
			conc = this.descricaoAtividade + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")" + " | "
					+ getStringRealizados() + getStringPendentes();
		}
		return conc;
	}

	public String exibeAtividade() {
		return this.descricaoAtividade + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")";
	}

	// -----------------------------------------------RESULTADO------------------------------------------------//
	/**
	 * cadastra o resultado
	 * 
	 * @param resultado
	 * @return o id do resultado
	 */
	public Integer cadastraResultado(String resultado) {
		contadorResultado += 1;
		resultados.put(contadorResultado, resultado);

		return contadorResultado;
	}

	/**
	 * remove o resultado da atividade
	 * 
	 * @param numeroResultado
	 * @return boolean
	 */
	public boolean removeResultado(Integer numeroResultado) {
		if (!resultados.containsKey(numeroResultado))
			throw new IllegalArgumentException("Resultado nao encontrado.");
		if (resultados.get(numeroResultado).equals("removido"))
			return false;
		else {
			resultados.put(numeroResultado, "removido");
			return true;
		}

	}

	/**
	 * lista todos os resultados da atividade
	 * 
	 * @return representacao textual de todos os resultados
	 */
	public String listaResultados() {
		String msg = "";
		for (String r : resultados.values()) {
			if (!r.equals("removido"))
				msg += r + " | ";
		}
		return msg.substring(0, msg.length() - 3);
	}

	/**
	 * exibe todos os resultados da atividade.
	 * 
	 * @return resultados
	 */
	public String exibeResultadosCadastados() {
		String msg = "";
		for (String r : resultados.values()) {
			if (!r.equals("removido"))
				msg += "            - " + r + "\n";
		}
		return msg;
	}

	// -------------------------------------------------BUSCA-------------------------------------------------------//
	/**
	 * Busca o termo nos itens dentro da Atividade;
	 * 
	 * @param termo
	 * @return
	 */
	public ArrayList<Pair> buscaTermoNoItem(String termo) {
		String procurarPor = termo;

		ArrayList<Pair> pares = new ArrayList<Pair>();

		for (int i = 0; i < resultadosEsperados.size(); i++) {
			if (resultadosEsperados.get(i).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
				Pair par = new Pair(this.idAtividade, resultadosEsperados.get(i).getDescricao());
				pares.add(par);
			}
		}

		return pares;
	}

	/**
	 * Conta a quantidade de casos do termo encontrado nos itens;
	 * 
	 * @param termo
	 * @return
	 */
	public int contaTermoNoItem(String termo) {
		String procurarPor = termo;
		int count = 0;

		for (int i = 0; i < resultadosEsperados.size(); i++) {
			if (resultadosEsperados.get(i).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
				count = count + 1;
			}
		}
		return count;
	}

	// -------------------------------------------------------US9--------------------------------------------------------------//

	/**
	 * Retorna a lista de Precedentes
	 * 
	 * @return
	 */
	public ArrayList<String> getIdPrecedentes() {
		return idPrecedentes;
	}

	/**
	 * Retorna o Idsubsequente
	 * 
	 * @return
	 */
	public String getIdSubsequente() {
		return idSubsequente;
	}

	/**
	 * Define o idsubsequente;
	 * 
	 * @param idSubsequente
	 */
	public void setIdSubsequente(String idSubsequente) {
		this.idSubsequente = idSubsequente;
	}

	/**
	 * Verifica se o idsubsequente já foi preenchido ou está vazio;
	 * 
	 * @return
	 */
	public boolean checaSubsequente() {
		if (this.idSubsequente != null) {
			return true;
		}
		return false;
	}

	/**
	 * Remove uma atividade precedente a esta;
	 * 
	 * @param idPrecedente
	 */
	public void removePrecedente(String idPrecedente) {
		idPrecedentes.remove(idPrecedente);
	}

	/**
	 * Retorna uma string com a lista de todos os precedentes desta atividade;
	 * 
	 * @return
	 */
	public String retornaListaPrecedentes() {
		String preceds = "";
		if (idPrecedentes.isEmpty()) {
			return "Sem precedentes.";
		} else {
			for (int i = 0; i < idPrecedentes.size(); i++) {
				if (i == idPrecedentes.size() - 1) {
					preceds = preceds + idPrecedentes.get(i);
				} else {
					preceds = preceds + idPrecedentes.get(i) + " - ";
				}
			}
			return preceds;
		}
	}

	// ---------------------------------------------------GETS E SETS--------------------------------------------------//
	public Integer getDuracao() {
		return duracao;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	public boolean isAssociado() {
		return associado;
	}

	public void setAssociado(boolean associado) {
		this.associado = associado;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public String getNivelRisco() {
		return nivelRisco;
	}

	/**
	 * muda a duracao da atividade e do item
	 * 
	 * @param duracao
	 */
	public void setDuracao(Integer item, Integer duracao) {
		resultadosEsperados.get(item - 1).setDuracao(duracao);
		this.duracao += duracao;
	}

	/**
	 * Retorna o as informações de todos os items Realizaods;
	 * 
	 * @return
	 */
	private String getStringRealizados() {
		String realizados = "";
		for (int i = 0; i < resultadosEsperados.size(); i++) {
			if (resultadosEsperados.get(i).getStatus().equals("REALIZADO")) {
				if (i >= resultadosEsperados.size() - 1) {
					realizados = realizados + "REALIZADO" + " - " + resultadosEsperados.get(i).getDescricao();
				} else {
					realizados = realizados + "REALIZADO" + " - " + resultadosEsperados.get(i).getDescricao() + " | ";
				}
			}
		}
		return realizados;
	}

	/**
	 * Retorna as informações de todos os itens pendentes;
	 * 
	 * @return
	 */
	private String getStringPendentes() {
		String pendentes = "";
		for (int i = 0; i < resultadosEsperados.size(); i++) {
			if (resultadosEsperados.get(i).getStatus().equals("PENDENTE")) {
				if (i >= resultadosEsperados.size() - 1) {
					pendentes = pendentes + "PENDENTE" + " - " + resultadosEsperados.get(i).getDescricao();
				} else {
					pendentes = pendentes + "PENDENTE" + " - " + resultadosEsperados.get(i).getDescricao() + " | ";
				}
			}
		}
		return pendentes;
	}

	/**
	 * muda o status de um item
	 * 
	 * @param itemPosicao
	 */
	public void setStatusItem(Integer itemPosicao) {
		if ((itemPosicao - 1) > resultadosEsperados.size())
			throw new IllegalArgumentException("Item nao encontrado.");
		if (resultadosEsperados.get(itemPosicao - 1).getStatus().equals("REALIZADO"))
			throw new IllegalArgumentException("Item ja executado.");

		resultadosEsperados.get(itemPosicao - 1).setStatus("REALIZADO");

	}
	/**
	 * pega o status de um item
	 * @param itemPosicao
	 * @return pendente ou realizado
	 */
	public String getStatusItem(Integer itemPosicao) {
		return resultadosEsperados.get(itemPosicao).getStatus();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAtividade == null) ? 0 : idAtividade.hashCode());
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
		Atividade other = (Atividade) obj;
		if (idAtividade == null) {
			if (other.idAtividade != null)
				return false;
		} else if (!idAtividade.equals(other.idAtividade))
			return false;
		return true;
	}

}