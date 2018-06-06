package fr.pizzeria.model;

import fr.pizzeria.exception.CategoriePizzaException;

public enum CategoriePizza {

	VIANDE ("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");

	public String type;

	CategoriePizza (String type) {
		this.type = type;
	}
	// ajouter une cat�gorie de pizza avec une r�ponse num�rique gr�ce � la m�thode getCategoriePizza
	public static CategoriePizza getCategoriePizza(int userChoice) throws CategoriePizzaException {


			switch (userChoice) {
			case 1:
				return CategoriePizza.VIANDE;
			case 2: 
				return CategoriePizza.POISSON;
			case 3:
				return CategoriePizza.SANS_VIANDE;
			default:
				throw new CategoriePizzaException("Vous n'avez pas indiqu� de cat�gorie");
			}

	}
	public String getType() {
		return this.type;
	}

}