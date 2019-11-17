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
 * @author Holliver
 *
 */
public class Pesquisa {
	/**
	 * descricao da pesquisa
	 */
	private String descricao;
	/**
	 * campo de interesse de uma pesquisa
	 */
	private String campoDeInteresse;
	/**
	 * codigo da pesquisa
	 */
	private String codigo;
	/**
	 * status de uma pesquisa
	 */
	private String status;
	/**
	 * lista de objetivos
	 */
	private List<String> objetivos;
	private String idProblema;
	/**
	 * mapa de atividades
	 */
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
	/** 
	 * pega o codigo da pesquisa
	 * @return codigo
	 */
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
	/**
	 * pega a descricao da pesquisa
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * pega o campo de interesse da pesquisa
	 * @return capo de interesse
	 */
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
	/**
	 * associa uma uma atividade a uma pesquisa
	 * @param codigoAtividade
	 * @param controllerAtividade
	 * @return um boolean
	 */
	public boolean associaAtividade(String codigoAtividade, ControllerAtividade controllerAtividade) {
		if (!atividades.containsKey(codigoAtividade)) {
			atividades.put(codigoAtividade, controllerAtividade.getAtividade(codigoAtividade));
			return true;
		} else
			return false;
	}
	/**
	 * desassocia uma uma atividade a uma pesquisa
	 * @param codigoAtividade
	 * @param controllerAtividade
	 * @return um boolean
	 */
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
	/**
	 * verifica se uma pesquisa tem atividades pendentes
	 * @return boolean
	 */
		public boolean verificaPendencia() {
			boolean verifica = false;
			for(Atividade a : atividades.values()) {
				if(a.totalItemsPendentes()!=0) {
					verifica = true;
				}
			}
			return verifica;
		}
		/**
		 * ordena as atividades de acordo com a duracao
		 * @return uma lista ordenada
		 */
		private ArrayList<Atividade> adicionaAtividadesOrdenadosDuracao() {
			ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());

			Collections.sort(atividadesOrdenadas,new ComparatorDuracao());

			return atividadesOrdenadas;
		}
		/**
		 * pega a atividade com menos pendencia
		 * @return id atividade
		 */
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
		/**
		 * ordena as atividades de acordo com o risco
		 * @return uma lista ordenada
		 */
		private ArrayList<Atividade> adicionaAtividadesOrdenadosRisco() {
			ArrayList<Atividade> atividadesOrdenadas = new ArrayList<>(this.atividades.values());

			Collections.sort(atividadesOrdenadas,new ComparatorRisco());

			return atividadesOrdenadas;
		}
		/**
		 * pega a primeira atividade inserida que tenha algum item pendente
		 * @return id atividade
		 */
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
		/**
		 * sugere a proxima atividade a ser executada
		 * @param estrategia
		 * @return id atividade
		 */
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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
			Pesquisa other = (Pesquisa) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
}
