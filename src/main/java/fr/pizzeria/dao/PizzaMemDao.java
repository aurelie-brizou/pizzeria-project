package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

// implémente l'interface
public class PizzaMemDao implements IPizzaDao {

	// la seule classe à initialiser le tableau de pizzas
	private List<Pizza> pizzaList= new ArrayList<Pizza>();

	public PizzaMemDao() {
		// creation liste pizza

		pizzaList.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza("REIN", "La reine", 11.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.SANS_VIANDE));
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return pizzaList;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		if (pizzaExists(pizza.code) == true) {
			throw new SavePizzaException();
		}
		pizzaList.add(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {

		if (pizzaExists(codePizza) == false) {
			throw new UpdatePizzaException();
		}


		for (Pizza pizza1: pizzaList) {
			if (pizza1.code.equals(codePizza)) {
				// on écrase l'ancienne pizza = pizza1 par la nouvelle en paramètre = pizza et chacun de ses attributs
				pizza1.code = pizza.code;
				pizza1.id = pizza.id;
				pizza1.libelle = pizza.libelle;
				pizza1.prix = pizza.prix;
				pizza1.categoriePizza = pizza.categoriePizza;
			}
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {

		if (pizzaExists(codePizza) == false) {
			throw new DeletePizzaException();
		}
		// trouver la pizza avec code
		Pizza pizzaASupprimer = findPizzaByCode(codePizza);

		// supprimer la pizza de la liste
		pizzaList.remove(pizzaASupprimer);
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		for (Pizza pizza: pizzaList) {
			if (pizza.code.equals(codePizza)) {
				return pizza;
			}
		}
		return null;
	}

	public boolean pizzaExists(String codePizza) {
		for (Pizza pizza: pizzaList) {
			if (pizza.code.equals(codePizza)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void initializeDb() {
		
	}



}
