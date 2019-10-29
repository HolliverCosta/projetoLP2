package projetolp2.usecase3;
/**
 * 
 * @author danilocrgm
 *
 */
public class Objetivo {
    /**
     * 
     */
    private String tipo;
    /**
     * 
     */
    private String descricao;
    /**
     * 
     */
    private int aderencia;
    /**
     * 
     */
    private int viabilidade;
    /**
     * 
     */
    private String id;
    /**
     * 
     * @param tipo
     * @param descricao
     * @param aderencia
     * @param viabilidade
     * @param id
     * @throws IllegalArgumentException
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

    @Override
    public String toString() {
        int valor = this.aderencia + this.viabilidade;
        return this.id + " - " + this.tipo + " - " + this.descricao + " - " + valor;
    }

    public String getTipo() {
        return tipo;
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
}
