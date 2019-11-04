package projetolp2.pesquisa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Controladora de pesquisas
 * @author HolliverCosta
 *
 */
public class ControllerPesquisa {
    private HashMap<String,Boolean> objetivosAssociados;
	/*
	 * Mapa de pesquisas onde a chave e as 3 primeiras letras do campoDeInterrese e um valor inteiro  e o valor e o objeto Pesquisa
	 */
	private HashMap<String, Pesquisa> pesquisas;
	/*
	 * mapa que server como contador para gerar o id de pesquisas 
	 */
	private HashMap<String, Integer> idPesquisas;
	/**
	 * Chamando a classe validacao para tratar sobe atributos vazios ou nulos
	 */
	private Validacao validacao;
	/**
	 * Contrutor da classe
	 */
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.validacao = new Validacao();
		this.idPesquisas = new HashMap<String, Integer>();
		this.objetivosAssociados = new HashMap<String,Boolean>();
	}
	
	 /**
     * metodo para cadastrar uma pesquisa
     * @param descricao
     * @param campoDeInteresse
     * 
     * @return o codigo da pesquisa
     */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {

		this.validacao.validaString(campoDeInteresse, "Formato do campo de interesse invalido.");
		this.validacao.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
		validacao.validaCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		
		String codigo = campoDeInteresse.substring(0, 3);
		
		if (!idPesquisas.containsKey(codigo)) 
			idPesquisas.put(codigo, 1);
		else 
			idPesquisas.put(codigo, idPesquisas.get(codigo) + 1);

		String codigoKey = (codigo + idPesquisas.get(codigo)).toUpperCase();

		Pesquisa pesquisa = new Pesquisa(descricao, campoDeInteresse, codigoKey);

		if (!pesquisas.containsKey(codigoKey)) 
			pesquisas.put(codigoKey.toUpperCase(), pesquisa);
		
		return codigoKey;
	}
	/**
	 * metodo que altera uma pesquisa, so pode alterar descricao e campo de interesse
	 * 
	 * @param codigo
     * @param conteudoASerAlterado
     * @param novoConteudo
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if(verificaExistePesquisa(codigo)==false)throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigo)==false) throw new IllegalArgumentException("Pesquisa desativada.");
		
		
		if (conteudoASerAlterado.equals("DESCRICAO")) {
			validacao.validaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			this.pesquisas.get(codigo).setDescricao(novoConteudo);
		}
		else if (conteudoASerAlterado.equals("CAMPO")) {
			validacao.validaString(novoConteudo, "Formato do campo de interesse invalido.");
			this.pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		}
		else 
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");

		
	}
	/**
	 * metodo que muda os status da pesquisa para desativada
	 * @param codigo
	 * @param motivo
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		validacao.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if(verificaExistePesquisa(codigo)==false)throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigo)==false) throw new IllegalArgumentException("Pesquisa desativada.");
		
		pesquisas.get(codigo).setStatus("desativada");
	
	}
	/**
	 * metodo que muda os status da pesquisa para ativada
	 * @param codigo
	 */
	public void ativaPesquisa(String codigo) {
		if(verificaExistePesquisa(codigo)==false)throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (ehAtiva(codigo)==true)throw new IllegalArgumentException("Pesquisa ja ativada.");
		
		pesquisas.get(codigo).setStatus("ativada");
	
	}
	/**
	 * metodo para exibir uma pesquisa
	 * @param codigo
	 * @return  representacao de uma pesquisa
	 */
	public String exibePesquisa(String codigo) {
		if(verificaExistePesquisa(codigo)==false)throw new IllegalArgumentException("Pesquisa nao encontrada.");
			
		return pesquisas.get(codigo).toString();
	}
	/**
	 * metodo para verificar se a pesquisa esta ativada
	 * @param codigo
	 * @return true para ativada e false para desativada
	 */
	public boolean ehAtiva(String codigo) {
		validacao.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if(verificaExistePesquisa(codigo)==false)throw new IllegalArgumentException("Pesquisa nao encontrada.");
		boolean msg = false;
		if (pesquisas.get(codigo).getStatus().equals("ativada"))
			msg = true;
		else if(pesquisas.get(codigo).getStatus().equals("desativada"))
			msg = false;
		
		return msg;
	}
	/**
	 * metodo para verificar se existe uma pesquisa cadastrada
	 * @param codigo
	 * @return true se existir
	 */
	private boolean verificaExistePesquisa(String codigo) {
		return pesquisas.containsKey(codigo);
	}
	
	public boolean associaProblema(String idPesquisa, String idProblema) throws IllegalArgumentException{
	    if(!verificaExistePesquisa(idPesquisa))throw new IllegalArgumentException("Pesquisa nao encontrada.");
	    if(!ehAtiva(idPesquisa)) throw new IllegalArgumentException("Pesquisa desativada.");
	    
	    Pesquisa temp = this.pesquisas.get(idPesquisa);
	    if(temp.getProblema().equals(idProblema)) return false;
	    if(!temp.getProblema().isEmpty()) throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
	    
	    temp.setProblema(idProblema);
	    return true;    
	}
	
	public boolean desassociaProblema(String idPesquisa, String idProblema) throws IllegalArgumentException{
	    if(!verificaExistePesquisa(idPesquisa))throw new IllegalArgumentException("Pesquisa nao encontrada.");
        if(!ehAtiva(idPesquisa)) throw new IllegalArgumentException("Pesquisa desativada.");
        
        Pesquisa temp = this.pesquisas.get(idPesquisa);
        if(temp.getProblema().isEmpty()) return false;
        temp.setProblema("");
        return true;
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
	    if(!verificaExistePesquisa(idPesquisa))throw new IllegalArgumentException("Pesquisa nao encontrada.");
        if(!ehAtiva(idPesquisa)) throw new IllegalArgumentException("Pesquisa desativada.");
	    
        Pesquisa temp = this.pesquisas.get(idPesquisa);
	    if(temp.getObjetivos().contains(idObjetivo)) return false;
	    if(this.objetivosAssociados.containsKey(idObjetivo) && this.objetivosAssociados.get(idObjetivo)) throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
	    this.objetivosAssociados.put(idObjetivo, true);
	    temp.addObjetivo(idObjetivo);
        return true;
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
	    if(!verificaExistePesquisa(idPesquisa))throw new IllegalArgumentException("Pesquisa nao encontrada.");
        if(!ehAtiva(idPesquisa)) throw new IllegalArgumentException("Pesquisa desativada.");
        
        Pesquisa temp = this.pesquisas.get(idPesquisa);
        if(!temp.getObjetivos().contains(idObjetivo)) return false;
        temp.getObjetivos().remove(idObjetivo);
        this.objetivosAssociados.replace(idObjetivo, false);
	    return true;
	}
	
	public String geraStringOrdenada(List<Pesquisa> l1, List<Pesquisa> l2, Comparator<Pesquisa> comparador) {
	    String output = "";
	    Collections.sort(l1,comparador);
	    Collections.sort(l2,new Comparator<Pesquisa>() {
            @Override
            public int compare(Pesquisa p0, Pesquisa p1) {
                return p1.getCodigo().compareTo(p0.getCodigo());
            }});
	    l1.addAll(l2);
	    for(Pesquisa p: l1) {
	        if(l1.indexOf(p) == l1.size() - 1)
	            output += p.toString();
	        else
	            output += p.toString() + " | ";
 	    }
	    return output;
	}
	public String listaPesquisas(String ordem) {
	    if(!ordem.equals("PROBLEMA") && !ordem.equals("OBJETIVOS") && !ordem.equals("PESQUISA")) throw new IllegalAccessError("Valor invalido da ordem");
	    
	    String output = "";
	    List<Pesquisa> comCriterio = new ArrayList<Pesquisa>();
	    List<Pesquisa> semCriterio = new ArrayList<Pesquisa>();
	    Comparator<Pesquisa> comparador = GeraComparador.geraComparador(ordem);
	    if(ordem.equals("PROBLEMA")) {
	        comCriterio.addAll(this.pesquisas.values().stream().
	            filter(x -> !x.getProblema().isEmpty()).
	            collect(Collectors.toList()));
	        semCriterio.addAll(this.pesquisas.values().stream().
                filter(x -> x.getProblema().isEmpty()).
                collect(Collectors.toList()));
	    }
	    else if(ordem.equals("OBJETIVOS")) {
	        comCriterio.addAll(this.pesquisas.values().stream().
	                filter(x -> !x.getObjetivos().isEmpty()).
	                collect(Collectors.toList()));
	        semCriterio.addAll(this.pesquisas.values().stream().
	                filter(x -> x.getObjetivos().isEmpty()).
	                collect(Collectors.toList()));
	    }
	    else {
	        comCriterio.addAll(this.pesquisas.values());
	    }
	    output = geraStringOrdenada(comCriterio, semCriterio, comparador);
	    return output;
	}
}