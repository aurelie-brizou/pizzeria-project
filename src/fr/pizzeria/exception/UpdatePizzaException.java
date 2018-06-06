package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {

	public UpdatePizzaException () {
		super("La pizza n'existe pas, elle ne peut pas être mis à jour");
	}
}
