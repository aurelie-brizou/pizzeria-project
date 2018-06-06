package fr.pizzeria.service;

import fr.pizzeria.dao.IPizzaDao;

public class MenuServiceFactory {

	static public MenuService getMenuService(int menuChoice, IPizzaDao pizzaDao) {
		switch (menuChoice) {
		case 1:
			return new ListerPizzasService(pizzaDao);
		case 2: 
			return new AjouterPizzaService(pizzaDao);
		case 3:
			return new ModifierPizzaService(pizzaDao);
		case 4:
			return new SupprimerPizzaService(pizzaDao);
		}
		return null;
	}
}
