package projetolp2;

public class Validacao {
	public void validaString(String string, String msg) {
		if (string == null)
			throw new NullPointerException(msg);
		else if (string.trim().isEmpty())
			throw new IllegalArgumentException(msg);
		
	}
}
