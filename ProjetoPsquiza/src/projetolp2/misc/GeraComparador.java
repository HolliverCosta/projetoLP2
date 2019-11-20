package projetolp2.misc;

import java.util.Comparator;

import projetolp2.pesquisa.Pesquisa;

public class GeraComparador {

    public static Comparator<Pesquisa> geraComparador(String ordem){
        Comparator<Pesquisa> comparador;
        if(ordem.equals("PROBLEMA")) {
            comparador = new Comparator<Pesquisa>() {
                @Override
                public int compare(Pesquisa p0, Pesquisa p1) {
                    return p1.getProblema().compareTo(p0.getProblema());
                }
            };
        }
        else if(ordem.equals("OBJETIVOS")) {
            comparador = new Comparator<Pesquisa>() {
                @Override
                public int compare(Pesquisa p0, Pesquisa p1) {
                    if(p0.getObjetivos().size() == p1.getObjetivos().size()) {
                        return 1;
                    }else if(p0.getObjetivos().size() > p1.getObjetivos().size()) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            };
        }
        else {
            comparador = new Comparator<Pesquisa>() {
                @Override
                public int compare(Pesquisa p0, Pesquisa p1) {
                   return p1.getCodigo().compareTo(p0.getCodigo());
                }
            };
        }
        return comparador;
    }
}
