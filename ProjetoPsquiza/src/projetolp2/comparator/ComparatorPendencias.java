package projetolp2.comparator;

import java.util.Comparator;

import projetolp2.atividades.Atividade;

public class ComparatorPendencias implements Comparator<Atividade> {
	/**
	 * metodo para comparar a quantidade de pendencias de duas atividades
	 * 
	 * @param pendencia
	 * @param outrapendencia
	 * 
	 * @return  um inteiro
	 */
	@Override
	public int compare(Atividade pendencia, Atividade outraPendencia) {
		Integer atividade1 = pendencia.totalItemsPendentes();
		Integer atividade2 = outraPendencia.totalItemsPendentes();
		return atividade2.compareTo(atividade1);
	}

}
