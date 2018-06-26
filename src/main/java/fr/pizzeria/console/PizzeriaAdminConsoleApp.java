package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.service.MenuServiceFactory;
import fr.pizzeria.service.MenuService;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJdbcDao;
import fr.pizzeria.dao.PizzaJpaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {

		// creation liste pizza

		//IPizzaDao pizzaDao = new PizzaMemDao();
		IPizzaDao pizzaDao = new PizzaJpaDao();

		// saisie utilisateur
		Scanner questionUser = new Scanner(System.in);
		int userChoice = 0;

		do {
			// display menu
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("5. Initialiser la base de données des pizzas");
			System.out.println("99. Sortir");
			userChoice = questionUser.nextInt();

			// le if sert à indiquer que il applique la factory si le userChoice
			// est entre 1 et 5
			if (userChoice > 0 && userChoice < 6) {
				MenuService service = MenuServiceFactory.getMenuService(userChoice, pizzaDao);
				try {
					service.executeUC(questionUser);
				} catch (StockageException e) {
					System.out.println(e.getMessage());
				}
			}

		} while (userChoice != 99);
		System.out.println("Au revoir");
		questionUser.close();
	}

}
