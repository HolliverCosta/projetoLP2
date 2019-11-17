package projetolp2.atividades;

import java.util.Comparator;

public class ComparatorRisco implements Comparator<Atividade> {
	@Override
	public int compare(Atividade risco, Atividade outroRisco) {
		Integer risco1 = risco.totalItemsPendentes();
		Integer risco2 = outroRisco.totalItemsPendentes();
		return risco2.compareTo(risco1);
	}

}
