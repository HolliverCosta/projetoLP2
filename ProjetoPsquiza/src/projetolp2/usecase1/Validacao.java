package projetolp2.usecase1;

public class Validacao {
	public void validaString(String string, String msg) throws NullPointerException, IllegalArgumentException{
		if (string == null)
			throw new NullPointerException(msg);
		else if (string.trim().isEmpty())
			throw new IllegalArgumentException(msg);

	}
	public void validaCampoInteresse(String string, String msg) {
		String campos[] = string.split(",");
		if(campos.length > 4)
			throw new IllegalArgumentException(msg);
		if(string.contains("."))
			throw new IllegalArgumentException(msg);
		for(String s : campos) {
			if(s.trim().isEmpty()) 
				throw new IllegalArgumentException(msg);
			if(s.length()<3)
				throw new IllegalArgumentException(msg);
		}
		if(string.length()>255)
			throw new IllegalArgumentException(msg);
	}
}
