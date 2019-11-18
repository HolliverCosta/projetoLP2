package projetolp2.po;

import java.io.Serializable;

/**
 * Representação da entidade objetivo do sistema Psquiza. O objetivo é identificado unicamente pelo seu código.
 * 
 * @author Danilo César Ribeiro Garcia de Medeiros
 *
 */
public class Objetivo implements Serializable{
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
    
	public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getAderencia() {
        return aderencia;
    }

    public int getViabilidade() {
        return viabilidade;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        return this.id + " - " + this.tipo + " - " + this.descricao + " - " + valor;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + aderencia;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + viabilidade;
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
        Objetivo other = (Objetivo) obj;
        if (aderencia != other.aderencia)
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        if (viabilidade != other.viabilidade)
            return false;
        return true;
    }
	
	
}
