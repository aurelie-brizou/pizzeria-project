package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.service.MenuServiceFactory;
import fr.pizzeria.service.MenuService;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws SavePizzaException {

		// creation liste pizza
		
		IPizzaDao pizzaDao = new PizzaMemDao();
		pizzaDao.saveNewPizza(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzaDao.saveNewPizza(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzaDao.saveNewPizza(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzaDao.saveNewPizza(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzaDao.saveNewPizza(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.SANS_VIANDE));
		pizzaDao.saveNewPizza(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.SANS_VIANDE));
		pizzaDao.saveNewPizza(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.SANS_VIANDE));
		
		
		// saisie utilisateur
		Scanner questionUser = new Scanner(System.in) ;
		int userChoice = 0;

		do {
			// display menu
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			userChoice = questionUser.nextInt() ;

			// le if sert à indiquer que il applique la factory si le userChoice est entre 1 et 4
			if (userChoice > 0 && userChoice < 5) {
			MenuService service = MenuServiceFactory.getMenuService(userChoice, pizzaDao);
			service.executeUC(questionUser);
			}

		} while (userChoice != 99);
		System.out.println("Au revoir");
		questionUser.close();
	}

}


