package projetolp2.atividades;

import java.io.Serializable;

/**
 * Representação de um Item. Todo Item possui uma descrição e um status, que é
 * definido como "PENDENTE" inicialmente;
 * 
 * @author caiom
 *
 */
public class Item implements Serializable{

    /**
     * Descrição do item;
     */
    private String descricao;
    /**
     * Status do item;
     */
    private String status;

    private Integer duracao;
    /**
     * COnstrói um Item a partir da sua descrição. Define o atributo status para
     * "PENDENTE";
     *
     * @param descricao
     */
    public Item(String descricao) {
        this.descricao = descricao;
        this.status = "PENDENTE";
        this.duracao = 0;
    }

    /**
     * Retorna a descrição do Item;
     * 
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o status do Item;
     * 
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status do Item;
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getDuracao() {
        return duracao;
    }
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
}