package projetolp2.busca;

import java.text.Collator;

public class Pair implements Comparable<Pair>{
    
    private String codigo;
    private String campo;
    
    public Pair(String codigo, String campo) {
        this.codigo = codigo;
        this.campo = campo;
    }
        
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    @Override
    public int compareTo(Pair outroPar) {

        int result = Collator.getInstance().compare(this.codigo, outroPar.getCodigo());

        if (result > 0) {
            return -1;
        } else if (result < 0) {
            return 1;
        } else {
            return 0;
        }
    }
}