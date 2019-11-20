package projetolp2.Psquiza;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import projetolp2.atividades.ControllerAtividade;
import projetolp2.busca.ControllerBusca;
import projetolp2.misc.Validacao;
import projetolp2.pesquisa.ControllerPesquisa;
import projetolp2.pesquisador.ControllerPesquisador;
import projetolp2.po.ControllerPO;
import projetolp2.po.Objetivo;
import projetolp2.po.Problema;

public class Psquiza {
    /**
     * 
     */
	private ControllerAtividade controllerAtividade;
	/**
	 * 
	 */
	private ControllerPesquisa controllerPesquisa;
	/**
	 * 
	 */
	private ControllerPesquisador controllerPesquisador;
	/**
	 * 
	 */
	private ControllerPO controllerPO;
	/**
	 * 
	 */
	private ControllerBusca controllerBusca;
	private Validacao validacao;
	/**
	 * 
	 */
	public Psquiza() {	
		this.controllerAtividade = new ControllerAtividade();
		this.controllerPesquisa = new ControllerPesquisa();
		this.controllerPesquisador = new ControllerPesquisador();
		this.controllerPO = new ControllerPO();	
		this.controllerBusca = new ControllerBusca();
		this.validacao = new Validacao();
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
	
	public ControllerBusca getControllerBusca() {
		return controllerBusca;
	}
	//-------------------------------------------------PROBLEMAS E OBJETIVOS---------------------------------------------------------//
	public boolean associaProblema(String idPesquisa, String idProblema) {
	    validacao.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
	    validacao.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
	    if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    
	    Problema novoProblema = this.controllerPO.getProblemas().get(idProblema);
	    return this.controllerPesquisa.associaProblema(idPesquisa, novoProblema, idProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa) {
        validacao.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");

	    return this.controllerPesquisa.desassociaProblema(idPesquisa);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
        validacao.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validacao.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
	    
	    Objetivo novoObjetivo = this.controllerPO.getObjetivos().get(idObjetivo);
	    return this.controllerPesquisa.associaObjetivo(idPesquisa, idObjetivo, novoObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
        validacao.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validacao.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
        
	    return this.controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	//----------------------------------------------------------ATIVIDADE----------------------------------------------//
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validacao.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(controllerAtividade.verificaExisteAtividade(codigoAtividade)==false)throw new IllegalArgumentException("Atividade nao encontrada");
		return controllerPesquisa.associaAtividade(codigoPesquisa, codigoAtividade,controllerAtividade);
	}
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validacao.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(controllerAtividade.verificaExisteAtividade(codigoAtividade)==false)throw new IllegalArgumentException("Atividade nao encontrada");
		return controllerPesquisa.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}
	public void executaAtividade(String codigoAtividade, Integer item, Integer duracao) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validacao.validaInteiro(item, "Item nao pode ser nulo ou negativo.");
		validacao.validaInteiro(duracao, "Duracao nao pode ser nula ou negativa.");
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}
	public Integer cadastraResultado(String codigoAtividade, String resultado) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validacao.validaString(resultado, "Resultado nao pode ser nulo ou vazio.");
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}
	public boolean removeResultado(String codigoAtividade, Integer numeroResultado) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validacao.validaInteiro(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}
	public String listaResultados(String codigoAtividade) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.listaResultados(codigoAtividade);
	}
	public Integer getDuracao(String codigoAtividade) {
		validacao.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.getDuracao(codigoAtividade);
	}
	public void configuraEstrategia(String estrategia) {
		validacao.validaString(estrategia, "Estrategia nao pode ser nula ou vazia.");
		validacao.validaEstrategia(estrategia, "Valor invalido da estrategia");
		controllerPesquisa.setEstrategia(estrategia);
	}
	public String proximaAtividade(String codigoPesquisa) {
		validacao.validaString(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		return controllerPesquisa.proximaAtividade(codigoPesquisa);
	}
	//-------------------------------------------------------SALVA------------------------------------------------//
	public void salva() {
	    try {
	        Object controllers[] = {
	                this.controllerAtividade,
	                this.controllerPesquisa,
	                this.controllerPesquisador,
	                this.controllerPO
	        };
	        String fileNames[] = {
	                "persistencia/atividades.ser",
	                "persistencia/pesquisas.ser",
	                "persistencia/pesquisadores.ser",
	                "persistencia/problemasEobjetivos.ser"
	        };

	        FileOutputStream fout;
	        ObjectOutputStream oos;
	        for(int i=0;i<4;i++) {
	            fout = new FileOutputStream(fileNames[i]);
	            oos = new ObjectOutputStream(fout);
	            oos.writeObject(controllers[i]);
	            oos.close();
	        }
	    }
	    catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void carrega() {
	    String dir = "persistencia";
	    try {
	        Object controllers[] = {
	                this.controllerAtividade,
                    this.controllerPesquisa,
                    this.controllerPesquisador,
                    this.controllerPO   
	        };
	        String fileNames[] = {
                    "persistencia/atividades.ser",
                    "persistencia/pesquisas.ser",
                    "persistencia/pesquisadores.ser",
                    "persistencia/problemasEobjetivos.ser"
            };

	        FileInputStream fin;
	        ObjectInputStream ois;
	        for(int i=0;i<4;i++) {
	            fin = new FileInputStream(fileNames[i]);
	            ois = new ObjectInputStream(fin);
	            controllers[i] = ois.readObject();
	            ois.close();
	        }
	    }
	    catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}

	
}
