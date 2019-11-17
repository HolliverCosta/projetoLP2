package projetolp2.pesquisa;

public class Validacao {
	/**
	 * verifica se a string e vazia ou nula
	 * 
	 * 
	 * @param string 
	 * @param msg  
	 * @return retorna um exception
	 */
	public void validaString(String string, String msg)  {
		if (string == null)
			throw new NullPointerException(msg);
		else if (string.trim().isEmpty())
			throw new IllegalArgumentException(msg);

	}
	/**
	 * verifica o campo de interesse 
	 *
	 * @param string
	 * @param msg
	 * @return retorna um exception
	 */
	public void validaCampoInteresse(String string, String msg) {
		String campos[] = string.split(",");
		if (campos.length > 4)
			throw new IllegalArgumentException(msg);

		if (string.contains("."))
			throw new IllegalArgumentException(msg);

		for (String s : campos) {

			if (s.trim().isEmpty())
				throw new IllegalArgumentException(msg);

			if (s.length() < 3)
				throw new IllegalArgumentException(msg);

		}

		if (string.length() > 255)
			throw new IllegalArgumentException(msg);
	}
	public void validaInteiro(Integer inteiro, String msg) {
		if (inteiro == null)
			throw new NullPointerException(msg);
		else if(inteiro<0) 
			throw new IllegalArgumentException(msg);
		
			
	}
	public void validaEstrategia(String string, String msg) {
		if(!(string.equals("MAIS_ANTIGA") || string.equals("MENOS_PENDENCIAS") || string.equals("MAIOR_RISCO") || string.equals("MAIOR_DURACAO"))) {
			throw new IllegalArgumentException(msg);
		}
	}
}
