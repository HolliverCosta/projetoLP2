package projetolp2.Psquiza;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import projetolp2.atividades.ControllerAtividade;
import projetolp2.busca.ControllerBusca;
import projetolp2.misc.ValidaCampos;
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
	       ValidaCampos.validaCamposString(new String[] {idPesquisa, idProblema},
	                new String[] {"idPesquisa", "idProblema"});
	    if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    Problema novoProblema = this.controllerPO.getProblemas().get(idProblema);
	    return this.controllerPesquisa.associaProblema(idPesquisa, novoProblema, idProblema);
	}
	
	public boolean desassociaProblema(String idPesquisa) {
	       ValidaCampos.validaCamposString(new String[] {idPesquisa},
	                new String[] {"idPesquisa"});
	    //if(!this.controllerPO.existe(idProblema)) throw new IllegalArgumentException("Problema nao encontrado");
	    return this.controllerPesquisa.desassociaProblema(idPesquisa);
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
	    ValidaCampos.validaCamposString(new String[] {idPesquisa, idObjetivo},
                new String[] {"idPesquisa", "idObjetivo"});
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
	    Objetivo novoObjetivo = this.controllerPO.getObjetivos().get(idObjetivo);
	    return this.controllerPesquisa.associaObjetivo(idPesquisa, idObjetivo, novoObjetivo);
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
	    ValidaCampos.validaCamposString(new String[] {idPesquisa, idObjetivo},
                new String[] {"idPesquisa", "idObjetivo"});
	    if(!this.controllerPO.existe(idObjetivo)) throw new IllegalArgumentException("Objetivo nao encontrado");
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
	    String dir = "persistencia";
	    try {
	        FileOutputStream fout1 = new FileOutputStream(dir + "/atividades.ser"); 
	        FileOutputStream fout2 = new FileOutputStream(dir + "/pesquisas.ser"); 
	        FileOutputStream fout3 = new FileOutputStream(dir + "/pesquisadores.ser"); 
	        FileOutputStream fout4 = new FileOutputStream(dir + "/problemasEobjetivos.ser"); 
	        
	        ObjectOutputStream oos1 = new ObjectOutputStream(fout1);
	        ObjectOutputStream oos2 = new ObjectOutputStream(fout2);
	        ObjectOutputStream oos3 = new ObjectOutputStream(fout3);
	        ObjectOutputStream oos4 = new ObjectOutputStream(fout4);
	        
	        oos1.writeObject(this.controllerAtividade);
	        oos2.writeObject(this.controllerPesquisa);
	        oos3.writeObject(this.controllerPesquisador);
	        oos4.writeObject(this.controllerPO);
	        
	        oos1.close();
	        oos2.close();
	        oos3.close();
	        oos4.close();
	        
	    }
	    catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void carrega() {
	    String dir = "persistencia";
	    try {
	        FileInputStream fin1 = new FileInputStream(dir + "/atividades.ser");
	        FileInputStream fin2 = new FileInputStream(dir + "/pesquisas.ser");
	        FileInputStream fin3 = new FileInputStream(dir + "/pesquisadores.ser");
	        FileInputStream fin4 = new FileInputStream(dir + "/problemasEobjetivos.ser");
	        
	        ObjectInputStream ois1 = new ObjectInputStream(fin1);
	        ObjectInputStream ois2 = new ObjectInputStream(fin2);
	        ObjectInputStream ois3 = new ObjectInputStream(fin3);
	        ObjectInputStream ois4 = new ObjectInputStream(fin4);
	        
	        this.controllerAtividade = (ControllerAtividade) ois1.readObject();
	        this.controllerPesquisa = (ControllerPesquisa) ois2.readObject();
	        this.controllerPesquisador = (ControllerPesquisador) ois3.readObject();
	        this.controllerPO = (ControllerPO) ois4.readObject();
	        
	        ois1.close();
	        ois2.close();
	        ois3.close();
	        ois4.close();
	    }
	    catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}

	
}
