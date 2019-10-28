package projetolp2.usecase4;

public class Item {

    private String descricao;
    private String status;

    public Item(String descricao) {
        this.descricao = descricao;
        this.status = "PENDENTE";
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}