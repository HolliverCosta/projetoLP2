package projetolp2.ControllerGeral;

import projetolp2.atividades.ControllerAtividade;
import projetolp2.pesquisa.ControllerPesquisa;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.po.ControllerPO;

public class ControllerGeral {
	private ControllerAtividade controllerAtividade;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerPO controllerPO;
	
	public ControllerGeral() {	
		this.controllerAtividade = new ControllerAtividade();
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerPesquisador = new ControllerPesquisador();
		this.controllerPO = new ControllerPO();				
	}
	public ControllerAtividade getControllerAtividade() {
		return controllerAtividade;
	}
	
	public ControllerPesquisa getControllerPesquisa() {
		return controllerPesquisa;
	}
	public ControllerPesquisador getControllerPesquisador() {
		return controllerPesquisador;
	}
	public ControllerPO getControllerPO() {
		return controllerPO;
	}
}
