package fr.pizzeria.exception;

public class SavePizzaException extends StockageException {
	
	public SavePizzaException () {
		super("La pizza que vous souhaitez sauvegarder existe d�j�");
	}
	
	public SavePizzaException(String str) {
		super(str);
	}
	

}
