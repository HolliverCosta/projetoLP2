package projetolp2.atividades;

import java.util.Comparator;

public class ComparatorPendencias implements Comparator<Atividade> {
	@Override
	public int compare(Atividade pendencia, Atividade outraPendencia) {
		Integer atividade1 = pendencia.totalItemsPendentes();
		Integer atividade2 = outraPendencia.totalItemsPendentes();
		return atividade2.compareTo(atividade1);
	}

}
