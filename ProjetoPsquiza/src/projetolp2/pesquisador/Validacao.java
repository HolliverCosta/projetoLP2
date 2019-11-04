package projetolp2.pesquisador;

public class Validacao {
	public void validaString(String string, String msg) {
		if (string == null)
			throw new NullPointerException(msg);

		else if (string.trim().isEmpty())
			throw new IllegalArgumentException(msg);
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
}
