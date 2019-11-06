package projetolp2.po;
/**
 * Representação da entidade objetivo do sistema Psquiza. O objetivo é identificado unicamente pelo seu código.
 * 
 * @author Danilo César Ribeiro Garcia de Medeiros
 *
 */
public class Objetivo {
    /**
     * Tipo do objetivo.
     */
    private String tipo;
    /**
     * Descrição do objetivo.
     */
    private String descricao;
    /**
     * Aderencia do objetivo.
     */
    private int aderencia;
    /**
     * Viabilidade do objetivo.
     */
    private int viabilidade;
    /**
     * ID do objetivo.
     */
    private String id;
    /**
     * Constrói um novo Objetivo a partir do seu tipo, descrição, aderencia e viabilidade.
     * 
     * @param tipo tipo do objetivo("GERAL"||"ESPECIFICO")
     * @param descricao descricao do objetivo
     * @param aderencia aderenciado objetivo(>0&<=5)
     * @param viabilidade viabilidade do objetivo(>0&<=5)
     * @param id codigo do objetivo
     * @throws IllegalArgumentException Exceção lançada caso a aderencia e/ou viabilidade sejam valores invalidos, e/ou o valor do tipo seja invalido.
     */
    public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String id) throws IllegalArgumentException{
        if(!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) throw new IllegalArgumentException("Valor invalido de tipo.");
        if(aderencia > 5 || aderencia < 1) throw new IllegalArgumentException("Valor invalido de aderencia");
        if(viabilidade > 5 || viabilidade < 1) throw new IllegalArgumentException("Valor invalido de viabilidade.");
        
        this.tipo = tipo;
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
        this.id = id;
    }
    
    public String getDescricao() {
		return descricao;
	}

	@Override
    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        return this.id + " - " + this.tipo + " - " + this.descricao + " - " + valor;
    }
}
