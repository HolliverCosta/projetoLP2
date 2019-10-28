package projetolp2.usecase4;

import java.time.Period;
import java.util.ArrayList;

public class Atividade {

    private String idAtividade;
    private String descricaoAtividade;
    private String nivelRisco;
    private String descricaoRisco;
    private Period duracao;

    private ArrayList<Item> resultadosEsperados = new ArrayList<Item>();

    public Atividade(String descricaoAtividade, String nivelRisco, String descricaoRisco, String iD) {
        this.descricaoAtividade = descricaoAtividade;
        this.nivelRisco = nivelRisco;
        this.descricaoRisco = descricaoRisco;
        this.idAtividade = iD;
        this.duracao = Period.ofDays(8);
    }

    public void cadastroItem(String descricaoItem) {
        Item item = new Item(descricaoItem);
        resultadosEsperados.add(item);
    }
    
    public void alteraStatusItem(String descricaoItem) {
        for(Item item: resultadosEsperados) {
            if(item.getDescricao().equals(descricaoItem)) {
                item.setStatus("REALIZADO");
            }
        }
    }
    
    public int totalItemsPendentes() {
        int count = 0;
        for (int i = 0; i < resultadosEsperados.size(); i++) {
            if (resultadosEsperados.get(i).getStatus().equals("PENDENTE")) {
                count = count + 1;
            }
        }
        return count;
    }
    
    public int totalItemsRealizados() {
        int count = 0;
        for (int i = 0; i < resultadosEsperados.size(); i++) {
            if (resultadosEsperados.get(i).getStatus().equals("REALIZADO")) {
                count = count + 1;
            }
        }
        return count;
    }
    
    private String getStringRealizados() {
        String realizados = "";
        for (int i = 0; i < resultadosEsperados.size(); i++) {
            if (resultadosEsperados.get(i).getStatus().equals("REALIZADO")) {
                if (i >= resultadosEsperados.size() - 1) {
                    realizados = realizados + "REALIZADO" + " - " +  resultadosEsperados.get(i).getDescricao();
                } else {
                    realizados = realizados + "REALIZADO" + " - " + resultadosEsperados.get(i).getDescricao() + " | ";
                }
            }
        }
        return realizados;
    }
    
    private String getStringPendentes() {
        String pendentes = "";
        for (int i = 0; i < resultadosEsperados.size(); i++) {
            if (resultadosEsperados.get(i).getStatus().equals("PENDENTE")) {
                if (i >= resultadosEsperados.size() - 1) {
                    pendentes = pendentes + "PENDENTE" + " - " +  resultadosEsperados.get(i).getDescricao();
                } else {
                    pendentes = pendentes + "PENDENTE" + " - " + resultadosEsperados.get(i).getDescricao() + " | ";
                }
            }
        }
        return pendentes;
    }
    

    @Override
    public String toString() {
        String conc = "";
        if (resultadosEsperados.isEmpty()) {
            conc = this.descricaoAtividade + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")";

        } else {
            conc = this.descricaoAtividade + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")" + " | " + getStringRealizados() + getStringPendentes();
        }
        return conc;
    }
}