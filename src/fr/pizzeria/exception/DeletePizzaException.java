package fr.pizzeria.exception;

public class DeletePizzaException extends StockageException {
	
	public DeletePizzaException () {
		super("La pizza n'existe pas, elle ne peut pas �tre supprim�e");
	}
	

}
