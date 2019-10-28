package projetolp2.usecase3;

public class ValidaCampos {

    public static void validaCamposString(String[] valores, String[] nomes) {
       for(int i=0;i<valores.length;i++) {
           if(valores[i] == null) throw new NullPointerException("Campo " + nomes[i] + " nao pode ser vazio ou nulo.");
           else if(valores[i].trim().isEmpty()) throw new IllegalArgumentException("Campo " + nomes[i] + " nao pode ser nulo ou vazio.");      
       }
    }
}
