package projetolp2.po;

import java.io.Serializable;

/**
 * Representação da entidade problema do sistema Psquiza. O problema é identificado unicamente pelo seu código.
 * 
 * @author Danilo César Ribeiro Garcia de Medeiros
 *
 */
public class Problema implements Serializable{
    /**
     * ID do problema.
     */
    private String id;
    /**
     * Descrição do problema.
     */
    private String descricao;
    /**
     * Viabilidade do problema
     */
    private int viabilidade;
    /**
     * Constrói um novo Problema a partir da descrição, da viabilidade e do seu id.
     * 
     * @param desc descricao do problema
     * @param viabilidade viabilidade do problema(>0&<=5)
     * @param id código do problema
     * @throws IllegalArgumentException Exceção lançada caso o valor da viabilidade seja invalido.
     */
    public Problema(String desc, int viabilidade, String id) throws IllegalArgumentException{
        if(viabilidade > 5 || viabilidade < 1) throw new IllegalArgumentException("Valor invalido de viabilidade.");
        
        this.descricao = desc;
        this.viabilidade = viabilidade;
        this.id = id;
    }
    /**
     * retorna a descriçao do problema
     * 
     * @return descricao
     */
    public String getDescricao() {
		return descricao;
	}

	@Override
    public String toString() {
        return this.id + " - " + this.descricao + " - " + this.viabilidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Problema other = (Problema) obj;
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
        if (viabilidade != other.viabilidade)
            return false;
        return true;
    }
	
}   
