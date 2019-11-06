package projetolp2.busca;

import java.util.ArrayList;
import java.util.Collections;
import projetolp2.Psquiza.Psquiza;

public class ControllerBusca {
    
    public String buscarGeral(String termo, Psquiza controller/*ControllerPesquisa cPa,ControllerPesquisador cPr,ControllerAtividade cA, ControllerPO cPO*/) {
        if(termo == null || termo.trim().isEmpty()) throw new IllegalArgumentException("Campo termo nao pode ser nulo ou vazio.");

        ArrayList<Pair> Pesquisas = new ArrayList<Pair>();
        ArrayList<Pair> Pesquisadores = new ArrayList<Pair>();
        ArrayList<Pair> Problemas = new ArrayList<Pair>();
        ArrayList<Pair> Objetivos = new ArrayList<Pair>();
        ArrayList<Pair> Atividades = new ArrayList<Pair>();

        Pesquisas.addAll(controller.getControllerPesquisa().retornaBuscaGeralPesquisa(termo));
        Collections.sort(Pesquisas);
        Pesquisadores.addAll(controller.getControllerPesquisador().retornaBuscaGeralPesquisador(termo));
        Collections.sort(Pesquisadores);
        Problemas.addAll(controller.getControllerPO().retornaBuscaGeralProblemas(termo));
        Collections.sort(Problemas);
        Objetivos.addAll(controller.getControllerPO().retornaBuscaGeralObjetivos(termo));
        Collections.sort(Objetivos);
        Atividades.addAll(controller.getControllerAtividade().retornaBuscaGeralAtividadesEItems(termo));
        Collections.sort(Atividades);
       
        String conc = "";
        for(Pair p: Pesquisas) {
            conc = conc + p.getCodigo() + ": " + p.getCampo() + " | "; // Concatenando todos os elementos da lista de pares;
        }
        for(Pair p: Pesquisadores) {
            conc = conc + p.getCodigo() + ": " + p.getCampo() + " | "; // Concatenando todos os elementos da lista de pares;
        }
        for(Pair p: Problemas) {
            conc = conc + p.getCodigo() + ": " + p.getCampo() + " | "; // Concatenando todos os elementos da lista de pares;
        }
        for(Pair p: Objetivos) {
            conc = conc + p.getCodigo() + ": " + p.getCampo() + " | "; // Concatenando todos os elementos da lista de pares;
        }
        for(Pair p: Atividades) {
            conc = conc + p.getCodigo() + ": " + p.getCampo() + " | "; // Concatenando todos os elementos da lista de pares;
        }
       
        if (conc.length() > 0) {
            conc = conc.substring (0, conc.length() - 3);  //Apagar os 3 ultimos termos da string ( | );
        }
        
        return conc;
    }
    
    public String buscarEspecífico(String termo, int numeroDoResultado, Psquiza controller) {
        if(termo == null || termo.trim().isEmpty()) throw new IllegalArgumentException("Campo termo nao pode ser nulo ou vazio.");
        if(numeroDoResultado < 0) throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
        
        ArrayList<Pair> Pesquisas = new ArrayList<Pair>();
        ArrayList<Pair> Pesquisadores = new ArrayList<Pair>();
        ArrayList<Pair> Problemas = new ArrayList<Pair>();
        ArrayList<Pair> Objetivos = new ArrayList<Pair>();
        ArrayList<Pair> Atividades = new ArrayList<Pair>();
        
        Pesquisas.addAll(controller.getControllerPesquisa().retornaBuscaGeralPesquisa(termo));
        Collections.sort(Pesquisas);
        Pesquisadores.addAll(controller.getControllerPesquisador().retornaBuscaGeralPesquisador(termo));
        Collections.sort(Pesquisadores);
        Problemas.addAll(controller.getControllerPO().retornaBuscaGeralProblemas(termo));
        Collections.sort(Problemas);
        Objetivos.addAll(controller.getControllerPO().retornaBuscaGeralObjetivos(termo));
        Collections.sort(Objetivos);
        Atividades.addAll(controller.getControllerAtividade().retornaBuscaGeralAtividadesEItems(termo));
        Collections.sort(Atividades);
        
        ArrayList<Pair> paresGeral = new ArrayList<Pair>();
        paresGeral.addAll(Pesquisas);
        paresGeral.addAll(Pesquisadores);
        paresGeral.addAll(Problemas);
        paresGeral.addAll(Objetivos);
        paresGeral.addAll(Atividades);
        
        int check = 0;
        for(int i = 0; i < paresGeral.size(); i++) {
            if(i == numeroDoResultado-1) {
                check = 1;
                return paresGeral.get(i).getCodigo() + ": " + paresGeral.get(i).getCampo();
            }
        }
        if(check == 0) throw new IllegalArgumentException("Entidade nao encontrada.");
        return null;
    }
    
    public int contarResultado(String termo, Psquiza controller) {
        if(termo == null || termo.trim().isEmpty()) throw new IllegalArgumentException("Campo termo nao pode ser nulo ou vazio.");
        
        int count = 0;
        
        count = count + controller.getControllerPesquisa().contaResultadoBusca(termo);
        count = count + controller.getControllerPesquisador().contaResultadoBusca(termo);
        count = count + controller.getControllerAtividade().contaResultadoBusca(termo);
        count = count + controller.getControllerPO().contaResultadoBusca(termo);
        
        if(count == 0) throw new IllegalArgumentException("Nenhum resultado encontrado");
        
        return count;
    }   
}