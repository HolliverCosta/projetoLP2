package projetolp2.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validacao {
	/**
	 * verifica se a string e vazia ou nula
	 * 
	 * 
	 * @param string
	 * @param msg
	 * @return retorna um exception
	 */
	public void validaString(String string, String msg) {
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
	/**
	 * verifica se um inteiro e nulo ou negativo
	 * @param inteiro
	 * @param msg
	 */
	public void validaInteiro(Integer inteiro, String msg) {
		if (inteiro == null)
			throw new NullPointerException(msg);
		else if (inteiro < 0)
			throw new IllegalArgumentException(msg);

	}
	/**
	 * verifica se a estrategia e diferente da que deveria ser passada
	 * @param string
	 * @param msg
	 */
	public void validaEstrategia(String string, String msg) {
		if (!(string.equals("MAIS_ANTIGA") || string.equals("MENOS_PENDENCIAS") || string.equals("MAIOR_RISCO")
				|| string.equals("MAIOR_DURACAO"))) {
			throw new IllegalArgumentException(msg);
		}
	}

	public void validaEmail(String String, String msg) {
		if (String.contains("@")) {
			String email[] = new String[2];
			email = String.split("@");
			if (email.length == 1) {
				throw new IllegalArgumentException(msg);
			} else {
				if (email[0].trim().isEmpty() || email[1].trim().isEmpty())
					throw new IllegalArgumentException(msg);

			}

		} else
			throw new IllegalArgumentException(msg);

	}

	public void validaFotoURL(String url, String msg) {
		if (!(url.contains("http://") || url.contains("https://"))) {
			throw new IllegalArgumentException(msg);
		}
	}

	public void validaData(String data, String msg) {
		if (data.length() == 10) {
			Date date = null;
			String dataTexto = new String(data);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				format.setLenient(false);
				date = format.parse(dataTexto);
			} catch (ParseException e) {
				throw new IllegalArgumentException(msg);
			}
		} else {
			throw new IllegalArgumentException(msg);
		}

	}

	public void validaDouble(Double doble, String msg) {
		if (doble == null)
			throw new NullPointerException(msg);
		else if (doble < 0)
			throw new IllegalArgumentException(msg);

	}
}
