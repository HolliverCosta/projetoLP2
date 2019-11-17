package projetolp2.atividades;

import java.util.Comparator;

public class ComparatorDuracao implements Comparator<Atividade> {
	/**
	 * metodo para comparar a duracao de duas atividades
	 * 
	 * @param duracao
	 * @param outraDuracao
	 * 
	 * @return um inteiro
	 */
	@Override
	public int compare(Atividade duracao, Atividade outraDuracao) {
		Integer duracao1 = duracao.getDuracao();
		Integer duracao2 = outraDuracao.getDuracao();
		return duracao2.compareTo(duracao1);
	}
}
