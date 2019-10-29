package projetolp2.usecase2;

public class Validacao {
	public void validaString(String string, String msg) {
		if (string == null)
			throw new NullPointerException(msg);

		else if (string.trim().isEmpty())
			throw new IllegalArgumentException(msg);
	}

	public void validaEmail(String String, String msg) {
		if (String.contains("@")) {
			String email[] = String.split("@");
			String parte1 = email[0];
			String parte2 = email[1];

			if (!((parte1.matches("[0-9]+") || parte1.contains("^[a-Z]"))
					&& (parte2.matches("[0-9]+") || parte2.contains("^[a-Z]")))) {
				throw new IllegalArgumentException(msg);
			}
		} else {
			throw new IllegalArgumentException(msg);
		}

	}
	public void validaFotoURL(String url, String msg) {
		if(!(url.contains("http://") || url.contains("https://"))) {
			throw new IllegalArgumentException(msg);
		}
	}
}
