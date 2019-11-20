package projetolp2.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import projetolp2.busca.Pair;
import projetolp2.misc.Validacao;
/**
 * Representação de um gerenciador de problemas e objetivos do sistema Psquiza.
 * 
 * @author Danilo César Ribeiro Garcia de Medeiros
 *
 */
public class ControllerPO implements Serializable{
    /**
     * Mapa de problemas.
     */
    private HashMap<String, Problema> problemas;
    /**
     * Mapa de objetivos.
     */
    private HashMap<String, Objetivo> objetivos;
    /**
     * Contador de identificadores para os problemas.
     */
    private int idProb;
    /**
     * Contador de identificadores para os objetivos.
     */
    private int idObj;
    /**
     * Constrói um novo controlador inicializando os mapas e os contadores em 1.
     */
    private Validacao valida;
    
    public ControllerPO() {
        this.problemas = new HashMap<String,Problema>();
        this.objetivos = new HashMap<String,Objetivo>();
        this.idObj = 1;
        this.idProb = 1;
        this.valida = new Validacao();
    }
    /**
     * Cadastra um novo problema no sistema a partir de sua descrição e viabilidade. Seu código é gerado automaticamente
     * a partir de "P" + valor do contador de problemas.
     * 
     * @param descricao descricao do problema
     * @param viabilidade viabilidade do problema(>0&<=5)
     * @return
     * @throws IllegalArgumentException Exceção lançada caso o campo descrição seja uma string vazia ou a viabilidade seja uma valor invalido.
     * @throws NullPointerException Exceção lançada caso o campo descrição seja nulo
     */
    public String cadastraProblema(String descricao, int viabilidade) throws IllegalArgumentException, NullPointerException{
        valida.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        if(viabilidade > 5 || viabilidade < 1) throw new IllegalArgumentException("Valor invalido de viabilidade.");
        
        String tempId = "P" + this.idProb++;
        this.problemas.put(tempId, new Problema(descricao, viabilidade, tempId));
        return tempId;
    }
    /**
     * Cadastra um novo objetivo no sistema a partir de setu tipo, sua descrição, sua aderencia e viabilidade. Seu código é gerado automaticamente
     * a partir de "O" + valor do contador de objetivos.
     * 
     * @param tipo tipo do objetivo("GERAL"||"ESPECIFICO")
     * @param descricao descricao do objetivo
     * @param aderencia aderencia do objetivo(>0&<=5)
     * @param viabilidade viabilidade do objetivo(>0&<=5)
     * @return
     * @throws IllegalArgumentException Exceção lançada caso os campos tipo e/ou descrição sejam Strings vazias, ou aderencia e/ou viabilidade sejam um valor invalido.
     * @throws NullPointerException Exceção lançada caso o campo tipo e/ou descrição sejam Strings nulas.
     */
    public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) throws IllegalArgumentException, NullPointerException{
        valida.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        valida.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
        if(!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) throw new IllegalArgumentException("Valor invalido de tipo.");
        if(aderencia > 5 || aderencia < 1) throw new IllegalArgumentException("Valor invalido de aderencia");
        if(viabilidade > 5 || viabilidade < 1) throw new IllegalArgumentException("Valor invalido de viabilidade.");
        
        String tempId = "O" + this.idObj++;
        this.objetivos.put(tempId, new Objetivo(tipo, descricao, aderencia, viabilidade, tempId));
        return tempId;
    }
    /**
     * Retorna o toString do objeto Problema identificado pelo código passado como parâmetro.
     *  
     * @param codigo código do problema
     * @return
     * @throws IllegalArgumentException Exceção lançada caso o campo codigo seja uma string vazia ou o código nao exista no mapa de problemas.
     * @throws NullPointerException Exceção lançadaca caso o campo código seja uma String nula.
     */
    public String exibeProblema(String codigo) throws IllegalArgumentException, NullPointerException{
        valida.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
        
        if(!this.problemas.containsKey(codigo)) throw new IllegalArgumentException("Problema nao encontrado");
        return this.problemas.get(codigo).toString();
    }
    /**
     * Retorna o toString do objeto Objetivo identificado pelo código passado como parâmetro.
     *  
     * @param codigo código do objetivo
     * @return
     * @throws IllegalArgumentException Exceção lançada caso o campo codigo seja uma string vazia ou o código nao exista no mapa de objetivos.
     * @throws NullPointerException Exceção lançadaca caso o campo código seja uma String nula.
     */
    public String exibeObjetivo(String codigo) throws IllegalArgumentException, NullPointerException {
        valida.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
        
        if(!this.objetivos.containsKey(codigo)) throw new IllegalArgumentException("Objetivo nao encontrado");
        return this.objetivos.get(codigo).toString();
    }
    /**
     * Remove um problema identificado pelo código passado como parãmetro.
     * 
     * @param codigo codigo do problema
     * @throws IllegalArgumentException Exceção lançada caso o campo codigo seja uma string vazia ou o código nao exista no mapa de problemas.
     * @throws NullPointerException Exceção lançadaca caso o campo código seja uma String nula.
     */
    public void apagarProblema(String codigo) throws IllegalArgumentException, NullPointerException {
        valida.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
        if(!this.problemas.containsKey(codigo)) throw new IllegalArgumentException("Problema nao encontrado");
        this.problemas.remove(codigo);
    }
    /**
     * Remove um objetivo identificado pelo código passado como parãmetro.
     * 
     * @param codigo codigo do objetivo
     * @throws IllegalArgumentException Exceção lançada caso o campo codigo seja uma string vazia ou o código nao exista no mapa de objetivos.
     * @throws NullPointerException Exceção lançadaca caso o campo código seja uma String nula.
     */
    public void apagarObjetivo(String codigo) throws IllegalArgumentException, NullPointerException {
        valida.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
        if(!this.objetivos.containsKey(codigo)) throw new IllegalArgumentException("Objetivo nao encontrado");
        this.objetivos.remove(codigo);
    }
    /**
     * 
     * @param id
     * @return
     */
    public boolean existe(String id) {
        return (this.problemas.containsKey(id) || this.objetivos.containsKey(id));
    }
    //-----------------------------------------------------US8---------------------------------------------------//
    public ArrayList<Pair> retornaBuscaGeralProblemas(String termo) {
        String procurarPor = termo;
        ArrayList<Pair> pares = new ArrayList<Pair>();
        
        for(String key: problemas.keySet()) {
            if(problemas.get(key).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
                Pair par = new Pair(key,problemas.get(key).getDescricao());
                pares.add(par);
            }
        }
        return pares;
    }
    
    public ArrayList<Pair> retornaBuscaGeralObjetivos(String termo) {
        String procurarPor = termo;
        ArrayList<Pair> pares = new ArrayList<Pair>();
        
        for(String key: objetivos.keySet()) {
            if(objetivos.get(key).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
                Pair par = new Pair(key,objetivos.get(key).getDescricao());
                pares.add(par);
            }
        }
        return pares;
    }
    
    public int contaResultadoBusca(String termo) {
        String procurarPor = termo;
        int count = 0;
        
        for(String key: problemas.keySet()) {
            if(problemas.get(key).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
                count = count + 1;
            }
        }
        
        for(String key: objetivos.keySet()) {
            if(objetivos.get(key).getDescricao().toLowerCase().contains(procurarPor.toLowerCase())) {
                count = count + 1;
            }
        }
        return count;
    }
	public HashMap<String, Problema> getProblemas() {
		return problemas;
	}
	public HashMap<String, Objetivo> getObjetivos() {
		return objetivos;
	}
    
}
