package fr.pizzeria.model;

import fr.pizzeria.exception.CategoriePizzaException;

public enum CategoriePizza {

	VIANDE ("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");

	public String type;

	CategoriePizza (String type) {
		this.type = type;
	}
	// ajouter une catégorie de pizza avec une réponse numérique grâce à la méthode getCategoriePizza
	public static CategoriePizza getCategoriePizza(int userChoice) throws CategoriePizzaException {


			switch (userChoice) {
			case 1:
				return CategoriePizza.VIANDE;
			case 2: 
				return CategoriePizza.POISSON;
			case 3:
				return CategoriePizza.SANS_VIANDE;
			default:
				throw new CategoriePizzaException("Vous n'avez pas indiqué de catégorie");
			}

	}
	public String getType() {
		return this.type;
	}

}