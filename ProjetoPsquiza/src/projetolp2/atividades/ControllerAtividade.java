package projetolp2.atividades;


import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.LowerCase;

import projetolp2.busca.Pair;

/**
 * Representação da Controladora de uma Atividade. Toda Controladora possui um gerador de Id, para as atividades e um HashMap com todas as atividades;
 * @author caiom
 */
public class ControllerAtividade implements Serializable {
    
    /**
     * Gerador do numero que irá compôr o ID da atividade;
     */
    private int ativIdGerador = 1;
    
    /**
     * Mapa com as atividades;
     */
    private HashMap<String,Atividade> atividades = new HashMap<String,Atividade>();
    //-------------------------------------------------ATIVIDADE--------------------------------------------------------------//
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
     * Método que irá apgar uma atividade cadastrada;
     * @param codigo
     */
    public void apagarAtividade(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Campo codigo nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigo)) throw new IllegalArgumentException("Atividade nao encontrada");
        
        atividades.remove(codigo);
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
     * Metódo para executar uma atividade
     * @param codigoAtividade
     * @param item
     * @param duracao
     * @return
     */
    public void executaAtividade(String codigoAtividade, Integer item, Integer duracao) {
    	if(atividades.get(codigoAtividade).isAssociado()==false)throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
        atividades.get(codigoAtividade).setStatusItem(item);
        atividades.get(codigoAtividade).setDuracao(item,duracao);
    }
    /**
     * Metódo para verificar se uma atividade existe no mapa de atividades
     * @param codigo
     * @return um boolean
     */
    public boolean verificaExisteAtividade(String codigo) {
    	return atividades.containsKey(codigo);
    }
    /**
     * Metódo para pegar a duracao de um resultado
     * @param codigoAtividade
     * @return a duracao do resultado em inteiro
     */
	public Integer getDuracao(String codigoAtividade) {
		if(!atividades.containsKey(codigoAtividade)) throw new IllegalArgumentException("Atividade nao encontrada");
		return atividades.get(codigoAtividade).getDuracao();
	}
	 /**
     * metodo para pegar uma atividade
     * @param codigoAtividade
     * @return um objeto do tipo atividade
     */
    public Atividade getAtividade(String codigoAtividade) {
		return this.atividades.get(codigoAtividade);
	}
    /**
     * Verifica se a inserção do idSubsequente irá gerar uma Loop na sequência das atividades;
     * @param idPrecedente
     * @param idSubsequente
     * @return
     */
    private boolean checaLoop(String idPrecedente,String idSubsequente) {
  		String id = idSubsequente;
  		while (atividades.get(id).checaSubsequente() == true) {
  			id = atividades.get(id).getIdSubsequente();
  		}
  		if(id.equals(idPrecedente)) {
  			return true;
  		}
  		return false;	
  	}
    
    /**
     * Define a proxima a atividade de outra atividade;
     * @param idPrecedente
     * @param idSubsequente
     */
    public void definirProximaAtividade(String idPrecedente,String idSubsequente) {
  		if(idPrecedente == null || idPrecedente.trim().isEmpty()) throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
  		if(idSubsequente == null || idSubsequente.trim().isEmpty()) throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
  		if(!atividades.containsKey(idPrecedente)) throw new IllegalArgumentException("Atividade nao encontrada." );
  		if(!atividades.containsKey(idSubsequente)) throw new IllegalArgumentException("Atividade nao encontrada." );
  		if(atividades.get(idPrecedente).checaSubsequente() == true) throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
  		if(checaLoop(idPrecedente,idSubsequente)) throw new IllegalArgumentException("Criacao de loops negada.");

  		this.atividades.get(idPrecedente).setIdSubsequente(idSubsequente);
  		this.atividades.get(idSubsequente).getIdPrecedentes().add(idPrecedente);
  	}
    
    /**
     * Conta quantas atividades vêm depois do idPrecedente;
     * @param idPrecedente
     * @return
     */
    public int contarProximas(String idPrecedente) {
    	if(idPrecedente == null || idPrecedente.trim().isEmpty()) throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
    	if(!atividades.containsKey(idPrecedente)) throw new IllegalArgumentException("Atividade nao encontrada.");   	
    	int cont = 0;
    	String id = idPrecedente;
  		while (atividades.get(id).checaSubsequente() == true) {
  			cont = cont + 1;
  			id = atividades.get(id).getIdSubsequente();
  		}    	
    	return cont;
    }
 	
    /**
     * Remove a atividade seguinte ao IdPrecedente, gerando duas novas sequências de Atividades;
     * @param idPrecedente
     */
    public void tirarProximaAtividade(String idPrecedente) {
    	if(idPrecedente == null || idPrecedente.trim().isEmpty()) throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
    	if(!atividades.containsKey(idPrecedente)) throw new IllegalArgumentException("Atividade nao encontrada.");
    	
    	String id = atividades.get(idPrecedente).getIdSubsequente();
    	if(id == null) {
    		atividades.get(idPrecedente).setIdSubsequente(null);
    	} else {
    		atividades.get(idPrecedente).setIdSubsequente(null);
    		atividades.get(id).removePrecedente(idPrecedente);  
    	}
    }
    
    /**
     * Pega uma atividade numa posição específica em uma sequência de atividades;
     * @param idAtividade
     * @param enesimaAtividade
     * @return
     */
    public String pegarEnesimaProxima(String idAtividade,int enesimaAtividade) {
    	if(idAtividade == null || idAtividade.trim().isEmpty()) throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
    	if(!atividades.containsKey(idAtividade)) throw new IllegalArgumentException("Atividade não encontrada.");
    	if(enesimaAtividade <= 0) throw new IllegalArgumentException("EnesimaAtividade nao pode ser negativa ou zero.");
    	if(!atividades.get(idAtividade).checaSubsequente()) throw new IllegalArgumentException("Atividade inexistente.");
    	
    	ArrayList<String> lista = new ArrayList<String>(); //Lista que receber a sequência das Atividades;
    	String nextID = atividades.get(idAtividade).getIdSubsequente();
    	
    	if(atividades.get(nextID).checaSubsequente()) {
    		while (atividades.get(nextID).checaSubsequente()) {
      			lista.add(nextID);
      			nextID = atividades.get(nextID).getIdSubsequente();
      		}
    		lista.add(nextID);
    	} else if(enesimaAtividade > 1) {
    		throw new IllegalArgumentException("Atividade inexistente.");
    	} else {
    		return nextID;
    	}
    	
    	String id = "";
    	for(int i = 0; i < lista.size(); i++) {
    		if(i == enesimaAtividade-1) {
    			id = lista.get(i);
    		}
    	}
    	return id;
    }
    
    /**
     * Pega a atividade que possuir o maior risco. Primeiro irá buscar a última atividade de risco "ALTO"; caso não haja, procura a última de nível "MEDIO";
     * Por fim, irá retornar a última de níivel "BAIXO", caso não exista nem alto nem médio.
     * @param idAtividade
     * @return
     */
    public String pegarMaiorRiscoAtividades(String idAtividade) {
    	if(idAtividade == null || idAtividade.trim().isEmpty()) throw new IllegalArgumentException("Atividade nao pode ser nulo ou vazio.");
    	if(!atividades.containsKey(idAtividade)) throw new IllegalArgumentException("Atividade nao encontrada.");
    	if(!atividades.get(idAtividade).checaSubsequente()) throw new IllegalArgumentException("Nao existe proxima atividade.");
    	
    	ArrayList<String> lista = new ArrayList<String>(); //Lista que receber a sequência das Atividades;
    	String nextID = atividades.get(idAtividade).getIdSubsequente();
    	
    	if(atividades.get(nextID).checaSubsequente()) {
    		while (atividades.get(nextID).checaSubsequente()) {
      			lista.add(nextID);
      			nextID = atividades.get(nextID).getIdSubsequente();
      		}
    	} else {
    		return nextID; //Se o próximo do IdAtividade não tiver próximo, ele será o mais alto risco;
    	}
  		
  		String maior = "";
  		int achaAlto = 0;
  		int achaMedio = 0;
  		
  		for(String s: lista) {
  			if(atividades.get(s).getNivelRisco().equals("ALTO")) {
  				achaAlto = 1;
  				maior = s;
  			}
  		} 		
  		
  		if(achaAlto == 0) {
  			for(String s: lista) {
  	  			if(atividades.get(s).getNivelRisco().equals("MEDIO")) {
  	  				achaMedio = 1;
  	  				maior = s;
  	  			}
  	  		}
  		}
  		if(achaMedio == 0 && achaAlto == 0) {
  			for(String s: lista) {
  	  			if(atividades.get(s).getNivelRisco().equals("BAIXO")) {
  	  				maior = s;
  	  			}
  	  		}
  		}
  		return maior;
    }
	//---------------------------------------------------------ITEM-------------------------------------------------------//
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
     * Muda o status de um Item de Pendente para Realizado;
     * @param codigo
     * @param item
     */
    public void finalizaStatusItem(String codigo,String item) {
        atividades.get(codigo).alteraStatusItem(item);
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
    //-------------------------------------------RESULTADO------------------------------------------------------//
    /**
     * Metódo para cadastrar um resultado
     * @param codigoAtividade
     * @param resultado
     * @return o id do resultado
     */
	public Integer cadastraResultado(String codigoAtividade, String resultado) {
		return atividades.get(codigoAtividade).cadastraResultado(resultado);		
	}
	 /**
     * Metódo para remover um resultado
     * @param codigoAtividade
     * @param resultado
     * @return um boolean
     */
	public boolean removeResultado(String codigoAtividade, Integer numeroResultado) {
		if(!atividades.containsKey(codigoAtividade)) throw new IllegalArgumentException("Atividade nao encontrada");
		return atividades.get(codigoAtividade).removeResultado(numeroResultado);
	}
	/**
     * Metódo para lista um resultado
     * @param codigoAtividade
     * @return representacao textual de um resultado
     */
	public String listaResultados(String codigoAtividade) {
		if(!atividades.containsKey(codigoAtividade)) throw new IllegalArgumentException("Atividade nao encontrada");
		return atividades.get(codigoAtividade).listaResultados();
	}
	
	//--------------------------------------------BUSCA----------------------------------------------------------//
	/**
	 * Retorna o termo na busca em Atividades e Items;
	 * @param termo
	 * @return
	 */
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
        
    /**
     * Conta quantos casos do termo foram encontrados em Atividades e na descrição do risco;
     * @param termo
     * @return
     */
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
 
}