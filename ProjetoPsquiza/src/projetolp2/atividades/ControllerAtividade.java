package projetolp2.atividades;
import java.util.ArrayList;
import java.util.HashMap;

import projetolp2.busca.Pair;

/**
 * Representação da Controladora de uma Atividade. Toda Controladora possui um gerador de Id, para as atividades e um HashMap com todas as atividades;
 * @author caiom
 */
public class ControllerAtividade {
    
    /**
     * Gerador do numero que irá compôr o ID da atividade;
     */
    private int ativIdGerador = 1;
    
    /**
     * Mapa com as atividades;
     */
    private HashMap<String,Atividade> atividades = new HashMap<String,Atividade>();
    
    /**
     * metódo que realiza o cadastro de cada Atividade;
     * @param Descricao
     * @param nivelRisco
     * @param descricaoRisco
     * @return
     */
    public String cadastrarAtividadePesquisa(String Descricao,String nivelRisco,String descricaoRisco) {
        if(Descricao == null || Descricao.trim().isEmpty()) throw new IllegalArgumentException("Campo Descricao nao pode ser nulo ou vazio.");
        if(nivelRisco == null || nivelRisco.trim().isEmpty()) throw new IllegalArgumentException("Campo nivelRisco nao pode ser nulo ou vazio.");
        if(descricaoRisco == null || descricaoRisco.trim().isEmpty()) throw new IllegalArgumentException("Campo descricaoRisco nao pode ser nulo ou vazio.");
        if(!nivelRisco.toUpperCase().equals("BAIXO") && !nivelRisco.toUpperCase().equals("MEDIO") && !nivelRisco.toUpperCase().equals("ALTO")) throw new IllegalArgumentException("Valor invalido do nivel do risco.");
        
        String iD = String.format("A%d", ativIdGerador);
        ativIdGerador = ativIdGerador + 1;
        
        Atividade atividade = new Atividade(Descricao,nivelRisco,descricaoRisco,iD);
        atividades.put(iD, atividade);
        return iD;
    }
    
    /**
     * Muda o status de um Item de Pendente para Realizado;
     * @param codigo
     * @param item
     */
    public void finalizaStatusItem(String codigo,String item) {
        atividades.get(codigo).alteraStatusItem(item);
    }
    
    /**
     * Método que irá apgar uma atividade cadastrada;
     * @param codigo
     */
    public void apagarAtividade(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Campo codigo nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigo)) throw new IllegalArgumentException("Atividade nao encontrada");
        
        atividades.remove(codigo);
    }
    
    /**
     * Método que realiza o cadastrao de um item em um atividae específica;
     * @param codigo
     * @param item
     */
    public void cadastrarItem(String codigo,String item) {
        if(codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Campo codigo nao pode ser nulo ou vazio.");
        if(item == null || item.trim().isEmpty()) throw new IllegalArgumentException("Item nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigo)) throw new IllegalArgumentException("Atividade nao encontrada");
        
        atividades.get(codigo).cadastroItem(item);
    }
    
    /**
     * Metódo que exibir(listar) uma atividade, com suas informações e os status dos itens daquela atividades;
     * @param codigo
     * @return
     */
    public String exibirAtividade(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Campo codigo nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigo)) throw new IllegalArgumentException("Atividade nao encontrada");
        return atividades.get(codigo).toString(); 
    }
    
    /**
     * Metódo para contar todos os itens Pendentes em uma atividaes específica;
     * @param codigo
     * @return
     */
    public int contarItensPendentes(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Campo codigo nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigo)) throw new IllegalArgumentException("Atividade nao encontrada");
        return atividades.get(codigo).totalItemsPendentes();
    }
    
    /**
     * Metódo para contar todos os itens Realizados em uma atividaes específica;
     * @param codigo
     * @return
     */
    public int contarItensRealizados(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Campo codigo nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigo)) throw new IllegalArgumentException("Atividade nao encontrada");
        return atividades.get(codigo).totalItemsRealizados();
    }
    //-------------------------------------------US7------------------------------------------------------//
    public boolean verificaExisteAtividade(String codigo) {
    	return atividades.containsKey(codigo);
    }
    public void executaAtividade(String codigoAtividade, Integer item, Integer duracao) {
		atividades.get(codigoAtividade).setStatusItem(item);
		atividades.get(codigoAtividade).setDuracao(duracao);
	}
	public Integer cadastraResultado(String codigoAtividade, String resultado) {
		return atividades.get(codigoAtividade).cadastraResultado(resultado);		
	}
	public boolean removeResultado(String codigoAtividade, Integer numeroResultado) {
		if(!atividades.containsKey(codigoAtividade)) throw new IllegalArgumentException("Atividade nao encontrada");
		return atividades.get(codigoAtividade).removeResultado(numeroResultado);
	}
	public String listaResultados(String codigoAtividade) {
		if(!atividades.containsKey(codigoAtividade)) throw new IllegalArgumentException("Atividade nao encontrada");
		return atividades.get(codigoAtividade).listaResultados();
	}
	public Integer getDuracao(String codigoAtividade) {
		if(!atividades.containsKey(codigoAtividade)) throw new IllegalArgumentException("Atividade nao encontrada");
		return atividades.get(codigoAtividade).getDuracao();
	}
	//--------------------------------------------US8----------------------------------------------------------//
	public ArrayList<Pair> retornaBuscaGeralAtividadesEItems(String termo) {
        String procurarPor = termo;
        ArrayList<Pair> pares = new ArrayList<Pair>();
        
        for(String key: atividades.keySet()) {
            
            if(atividades.get(key).getDescricaoAtividade().toLowerCase().contains(procurarPor.toLowerCase())) {
                Pair par = new Pair(key,atividades.get(key).getDescricaoAtividade());
                pares.add(par);
            }
            
            if(atividades.get(key).getDescricaoRisco().toLowerCase().contains(procurarPor.toLowerCase())) {
                Pair par = new Pair(key,atividades.get(key).getDescricaoRisco());
                pares.add(par);
            }
            
            pares.addAll(atividades.get(key).buscaTermoNoItem(termo)); //Adiciona todos os elementos da lista de pares de Item, na lista geral;
        }
        
        return pares;
    }
        
    public int contaResultadoBusca(String termo) {
        String procurarPor = termo;
        int count = 0;
        
        for(String key: atividades.keySet()) {
            
            if(atividades.get(key).getDescricaoAtividade().toLowerCase().contains(procurarPor.toLowerCase())) {
                count = count + 1; 
            }
            
            if(atividades.get(key).getDescricaoRisco().toLowerCase().contains(procurarPor.toLowerCase())) {
                count = count + 1;
            }  
            
            count = count + atividades.get(key).contaTermoNoItem(termo);
        }
        return count;
    }

    public Atividade getAtividade(String codigoAtividade) {
		return this.atividades.get(codigoAtividade);
	}
	
}