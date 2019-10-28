package projetolp2.usecase3;

import java.security.InvalidKeyException;
import java.util.HashMap;
/**
 * 
 * @author danilocrgm
 *
 */
public class POController {
    /**
     * 
     */
    private HashMap<String, Problema> problemas;
    /**
     * 
     */
    private HashMap<String, Objetivo> objetivos;
    /**
     * 
     */
    private int idProb;
    /**
     * 
     */
    private int idObj;
    /**
     * 
     */
    public POController() {
        this.problemas = new HashMap<String,Problema>();
        this.objetivos = new HashMap<String,Objetivo>();
        this.idObj = 1;
        this.idProb = 1;
    }
    /**
     * 
     * @param descricao
     * @param viabilidade
     * @return
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String cadastraProblema(String descricao, int viabilidade) throws IllegalArgumentException, NullPointerException{
        ValidaCampos.validaCamposString(new String[]{descricao, String.valueOf(viabilidade)},
                new String[] {"descricao", "viabilidade"});
        String tempId = "P" + this.idProb++;
        this.problemas.put(tempId, new Problema(descricao, viabilidade, tempId));
        return "";
    }
    /**
     * 
     * @param tipo
     * @param descricao
     * @param aderencia
     * @param viabilidade
     * @return
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) throws IllegalArgumentException, NullPointerException{
        ValidaCampos.validaCamposString(new String[] {tipo, descricao, String.valueOf(aderencia), String.valueOf(viabilidade)},
                new String[] {"tipo", "descricao", "aderencia", "viabilidade"});
        String tempId = "O" + this.idObj++;
        this.objetivos.put(tempId, new Objetivo(tipo, descricao, aderencia, viabilidade, tempId));
        return "";
    }
    /**
     * 
     * @param codigo
     * @return
     * @throws InvalidKeyException
     */
    public String exibeProblema(String codigo) throws InvalidKeyException {
        ValidaCampos.validaCamposString(new String[] {codigo},
                new String[] {"codigo"});
        if(!this.problemas.containsKey(codigo)) throw new InvalidKeyException("Problema nao encontrado");
        return this.problemas.get(codigo).toString();
    }
    /**
     * 
     * @param codigo
     * @return
     * @throws InvalidKeyException
     */
    public String exibeObjetivo(String codigo) throws InvalidKeyException {
        ValidaCampos.validaCamposString(new String[] {codigo},
                new String[] {"codigo"});
        if(!this.objetivos.containsKey(codigo)) throw new InvalidKeyException("Objetivo nao encontrado");
        return this.objetivos.get(codigo).toString();
    }
    /**
     * 
     * @param codigo
     * @throws InvalidKeyException
     */
    public void apagarProblema(String codigo) throws InvalidKeyException {
        ValidaCampos.validaCamposString(new String[] {codigo},
                new String[] {"codigo"});
        if(!this.problemas.containsKey(codigo)) throw new InvalidKeyException("Problema nao encontrado");
        this.problemas.remove(codigo);
    }
    /**
     * 
     * @param codigo
     * @throws InvalidKeyException
     */
    public void apagarObjetivo(String codigo) throws InvalidKeyException {
        ValidaCampos.validaCamposString(new String[] {codigo},
                new String[] {"codigo"});
        if(!this.objetivos.containsKey(codigo)) throw new InvalidKeyException("Objetivo nao encontrado");
        this.objetivos.remove(codigo);
    }
}
