package projetolp2.po;
/**
 * Representação da entidade problema do sistema Psquiza. O problema é identificado unicamente pelo seu código.
 * 
 * @author Danilo César Ribeiro Garcia de Medeiros
 *
 */
public class Problema {
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
    
    @Override
    public String toString() {
        return this.id + " - " + this.descricao + " - " + this.viabilidade;
    }
}   
