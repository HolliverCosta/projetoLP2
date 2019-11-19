package projetolp2.comparator;

import java.util.Comparator;

import projetolp2.atividades.Atividade;

public class ComparatorRisco implements Comparator<Atividade> {
	/**
	 * metodo para comparar o nivel de risco de duas atividades
	 * 
	 * @param risco
	 * @param outroRisco
	 * 
	 * @return um inteiro
	 */
	@Override
	public int compare(Atividade risco, Atividade outroRisco) {
		Integer risco1 = risco.totalItemsPendentes();
		Integer risco2 = outroRisco.totalItemsPendentes();
		return risco2.compareTo(risco1);
	}

}
