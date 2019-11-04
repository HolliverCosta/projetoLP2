package projetolp2.Psquiza;

import projetolp2.atividades.ControllerAtividade;
import projetolp2.pesquisa.ControllerPesquisa;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.po.ControllerPO;
import projetolp2.po.ValidaCampos;

public class Psquiza {
	private ControllerAtividade controllerAtividade;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerPO controllerPO;
	
	public Psquiza() {	
		this.controllerAtividade = new ControllerAtividade();
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerPesquisador = new ControllerPesquisador();
		this.controllerPO = new ControllerPO();				
	}
	public ControllerAtividade getControllerAtividade() {
		return this.controllerAtividade;
	}
	
	public ControllerPesquisa getControllerPesquisa() {
		return this.controllerPesquisa;
	}
	public ControllerPesquisador getControllerPesquisador() {
		return this.controllerPesquisador;
	}
	public ControllerPO getControllerPO() {
		return this.controllerPO;
	}
	
	public boolean associaProblema(String idPesquisa, String idProblema) {
	       ValidaCampos.validaCamposString(new String[] {idPesquisa, idProblema},
	                new String[] {"idPesquisa", "idProblema"});
	    if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    return this.controllerPesquisa.associaProblema(idPesquisa, idProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
	       ValidaCampos.validaCamposString(new String[] {idPesquisa, idProblema},
	                new String[] {"idPesquisa", "idProblema"});
	    if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    return this.controllerPesquisa.desassociaProblema(idPesquisa, idProblema);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
	    ValidaCampos.validaCamposString(new String[] {idPesquisa, idObjetivo},
                new String[] {"idPesquisa", "idObjetivo"});
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
	    return this.controllerPesquisa.associaObjetivo(idPesquisa, idObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
	    ValidaCampos.validaCamposString(new String[] {idPesquisa, idObjetivo},
                new String[] {"idPesquisa", "idObjetivo"});
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
	    return this.controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
	public String listaPesquisas(String ordem) {
	    return this.controllerPesquisa.listaPesquisas(ordem);
	}
}
