package facade;

import easyaccept.EasyAccept;
import projetolp2.usecase1.ControllerPesquisa;
import projetolp2.usecase4.ControllerAtividade;

public class Psquiza {
    
    ControllerPesquisa controllerPesquisa;
    ControllerAtividade controllerAtividade;
    
    public static void main(String[] args) {
        args = new String[] {"facade.Psquiza", "TestesDeAceitacao/use_case_1.txt","TestesDeAceitacao/use_case_4.txt"};
        EasyAccept.main(args);
    }
    
    public Psquiza() {
        controllerPesquisa = new ControllerPesquisa();
        controllerAtividade = new ControllerAtividade();
        
    }
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
    	System.out.println(controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse));
        return controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
    }

//  public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
//
//  }
//
    public void encerraPesquisa(String codigo, String motivo) {
	  controllerPesquisa.encerraPesquisa(codigo, motivo);
    }
//  public void ativaPesquisa(String codigo) {
//
//  }
//  public String exibePesquisa(String codigo) {
//
//  }
//  public boolean ehAtiva(String codigo) {
//
//  }
//-----------------------------------------ATIVIDADES METODOLÓGICAS-----------------------------------------------//
    /**
     * Solicita o cadastro de uma Atividade;
     * @param Descricao
     * @param nivelRisco
     * @param descricaoRisco
     * @return
     */
    public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
        return controllerAtividade.cadastrarAtividadePesquisa(Descricao, nivelRisco, descricaoRisco);
    }
    /**
     * Solicita a remoção de uma Atividade;
     * @param codigo
     */
    public void apagaAtividade(String codigo) {
        controllerAtividade.apagarAtividade(codigo);
    }
    /**
     * Solicita o cadastro de um Item em uma Atividade, especificada pelo seu código;
     * @param codigo
     * @param item
     */
    public void cadastraItem(String codigo, String item) {
        controllerAtividade.cadastrarItem(codigo, item);
    }
    /**
     * Solicita a exibição de uma Atividade, especificada pelo seu código;
     * @param codigo
     * @return
     */
    public String exibeAtividade(String codigo) {
        return controllerAtividade.exibirAtividade(codigo);
    }
    /**
     * Solicita o total de Itens Pendentes, em um Atividade especificada pelo seu código;
     * @param codigo
     * @return
     */
    public int contaItensPendentes(String codigo) {
        return controllerAtividade.contarItensPendentes(codigo);
    }
    /**
     * Solicita o total de Itens Realizados, em um Atividade especificada pelo seu código;
     * @param codigo
     * @return
     */
    public int contaItensRealizados(String codigo) {
        return controllerAtividade.contarItensRealizados(codigo);
    }
}
