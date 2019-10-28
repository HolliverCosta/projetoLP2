package projetolp2.usecase3;
/**
 * 
 * @author danilocrgm
 *
 */
public class Problema {
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String descricao;
    /**
     * 
     */
    private int viabilidade;
    /**
     * 
     * @param desc
     * @param viabilidade
     * @param id
     * @throws IllegalArgumentException
     */
    public Problema(String desc, int viabilidade, String id) throws IllegalArgumentException{
        if(viabilidade > 5 || viabilidade < 1) throw new IllegalArgumentException("Valor invalido de viabilidade.");
        
        this.descricao = desc;
        this.viabilidade = viabilidade;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.descricao + " - " + this.viabilidade;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getViabilidade() {
        return viabilidade;
    }
}   
