package projetolp2.pesquisa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import projetolp2.atividades.Atividade;
import projetolp2.atividades.ComparatorDuracao;
import projetolp2.atividades.ComparatorPendencias;
import projetolp2.atividades.ComparatorRisco;
import projetolp2.atividades.ControllerAtividade;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.pesquisador.Pesquisador;

/**
 * Pesquisa contem uma descricao, campo de interesse, codigo identificador e um
 * status predefinido como ativada
 * 
 * @author Holindo
 *
 */
public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private String status;
	private List<String> objetivos;
	private String idProblema;
	private Map<String,Atividade> atividades;
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Constroi uma pesquisa
	 * 
	 * @param descricao
	 * @param campoDeInteresse
	 * @param codigo
	 */
	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.status = "ativada";
		this.codigo = codigo;
		this.idProblema = "";
		this.objetivos = new ArrayList<String>();
		this.atividades = new LinkedHashMap<>();
		this.pesquisadores = new HashMap<String, Pesquisador>();
	}

	public void addObjetivo(String idObjetivo) {
		this.objetivos.add(idObjetivo);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public List<String> getObjetivos() {
		return this.objetivos;
	}

	public String getProblema() {
		return this.idProblema;
	}

	/**
	 * @return status da pesquisa
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * metodo para mudar o status da pesquisa
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * metodo para mudar a descricao da pesquisa
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	/**
	 * metodo para mudar o campo de interesse da pesquisa
	 * 
	 * @param campoDeInteresse
	 */
	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public void setProblema(String idProblema) {
		this.idProblema = idProblema;
	}

	/**
	 * @return representacao textual de uma pesquisa
	 */
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}

	// ----------------------------------------------US7---------------------------------------------------//
	public boolean associaAtividade(String codigoAtividade, ControllerAtividade controllerAtividade) {
		if (!atividades.containsKey(codigoAtividade)) {
			atividades.put(codigoAtividade, controllerAtividade.getAtividade(codigoAtividade));
			return true;
		} else
			return false;
	}

	public boolean desassociaAtividade(String codigoAtividade) {
		if (!atividades.containsKey(codigoAtividade)) {
			return false;
		} else
			atividades.remove(codigoAtividade);
		return true;
	}

	public boolean associaPesquisador(String emailPesquisador, ControllerPesquisador controllerPesquisador) {
		if (pesquisadores.containsKey(emailPesquisador))
			return false;
		this.pesquisadores.put(emailPesquisador, controllerPesquisador.getPesquisador(emailPesquisador));
		return true;

	}

	public boolean desassociaPesquisador(String emailPesquisador) {
		if (!pesquisadores.containsKey(emailPesquisador))
			return false;
		this.pesquisadores.remove(emailPesquisador);
		return true;
	}
	// ---------------------------------------US10-------------------------------------------------//
		public boolean verificaPendencia() {
			boolean verifica = false;
			for(Atividade a : atividades.values()) {
				if(a.totalItemsPendentes()!=0) {
					verifica = true;
				}
			}
			return verifica;
		}
		private ArrayList<Atividade> adicionaAtividadesOrdenadosDuracao() {
			ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());

			Collections.sort(atividadesOrdenadas,new ComparatorDuracao());

			return atividadesOrdenadas;
		}
		private String adicionaAtividadesOrdenadosPendencias() {
	        ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());
	        Collections.sort(atividadesOrdenadas,new ComparatorPendencias());
	        String id = "";
	        int menor = atividadesOrdenadas.get(0).totalItemsPendentes();
	        for (Atividade atividade : atividadesOrdenadas) {
	            if(atividade.totalItemsPendentes()< menor && atividade.totalItemsPendentes() > 0) {
	                menor = atividade.totalItemsPendentes();
	                id = atividade.getIdAtividade();
	            }
	        }
	        return id;
	    }
		private ArrayList<Atividade> adicionaAtividadesOrdenadosRisco() {
			ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());

			Collections.sort(atividadesOrdenadas,new ComparatorRisco());

			return atividadesOrdenadas;
		}
		private String atividadeMaisAntiga() {
			
			if(atividades.entrySet().iterator().next().getValue().totalItemsPendentes()!=0)
				return  atividades.entrySet().iterator().next().getValue().getIdAtividade();
			else
				for(Atividade a : atividades.values()) {
					if(a.totalItemsPendentes()>0) {
						return a.getIdAtividade();
					}
				}
			return null;
		}
		
		public String proximaAtividade(String estrategia) {
			String atividade = "";
			if (estrategia.equals("MAIS_ANTIGA")) {
				atividade = atividadeMaisAntiga();
			} else if (estrategia.equals("MENOS_PENDENCIAS")) {
				atividade = adicionaAtividadesOrdenadosPendencias();
			} else if (estrategia.equals("MAIOR_RISCO")) {
				atividade = adicionaAtividadesOrdenadosRisco().get(0).getIdAtividade();
			} else if (estrategia.equals("MAIOR_DURACAO")) {
				atividade = adicionaAtividadesOrdenadosDuracao().get(0).getIdAtividade();
			}

			
			return atividade;
			
		}
}
