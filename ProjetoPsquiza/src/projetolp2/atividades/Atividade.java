package projetolp2.atividades;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representação de uma Atividade Metodológica. Todoa Atividade possui um ID
 * (códido), uma descrição da Atividade, um nível de risco, uma descrição do
 * nível de risco e uma duração (em dias).
 * 
 * @author caiom
 */
public class Atividade {

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
	 * Lista dos resultados esperados.
	 */
	private ArrayList<Item> resultadosEsperados = new ArrayList<Item>();
	private HashMap<Integer, String> resultados;
	private int contadorResultado;

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
	}

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

	// -----------------------------------------------US7------------------------------------------------//
	public void setDuracao(Integer duracao) {
		this.duracao += duracao;
	}

	public void setStatusItem(Integer itemPosicao) {
		if((itemPosicao-1)>resultadosEsperados.size())throw new IllegalArgumentException("Item nao encontrado.");
		if (resultadosEsperados.get(itemPosicao - 1).getStatus().equals("REALIZADO"))
			throw new IllegalArgumentException("Item ja executado.");

		resultadosEsperados.get(itemPosicao - 1).setStatus("REALIZADO");

	}

	public Integer cadastraResultado(String resultado) {
		contadorResultado += 1;
		resultados.put(contadorResultado, resultado);

		return contadorResultado;
	}

	public boolean removeResultado(Integer numeroResultado) {
		if(!resultados.containsKey(numeroResultado))throw new IllegalArgumentException("Resultado nao encontrado."); 
		if (resultados.get(numeroResultado).equals("removido"))
			return false;
		else {
			resultados.put(numeroResultado, "removido");
			return true;
		}
		
	}

	public String listaResultados() {
		String msg = "";
		for (String r : resultados.values()) {
			if(!r.equals("removido"))
				msg += r + " | ";
		}
		return msg.substring(0, msg.length() - 3);
	}

	public Integer getDuracao() {
		return duracao;
	}
}