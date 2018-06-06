package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.CategoriePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	public ModifierPizzaService(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}

	@Override
	public void executeUC(Scanner scanner) {

		System.out.println("Mise à jour d’une pizza");

		try {
			System.out.println("Veuillez choisir le code de la pizza à modifier");
			scanner.nextLine();
			String userChoiceEdit = scanner.nextLine();

			System.out.println("Veuillez saisir le nouveau code :");
			String editCode = scanner.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String editLibelle = scanner.nextLine();
			System.out.println("Veuillez saisir le prix :");
			double editPrix = Double.parseDouble(scanner.nextLine());

			try  {
				System.out.println("Veuillez saisir la catégorie de la pizza 1: viande 2: poisson 3: sans viande");
				int userChoice = scanner.nextInt();
				CategoriePizza editCategoriePizza = CategoriePizza.getCategoriePizza(userChoice);

				CategoriePizza newCategoriePizza = CategoriePizza.getCategoriePizza(userChoice);
				Pizza newPizza = new Pizza(editCode, editLibelle, editPrix, editCategoriePizza);
				pizzaDao.updatePizza(userChoiceEdit, newPizza);

			} catch (CategoriePizzaException e) {
				System.out.println(e.getMessage());
			}




		} catch (UpdatePizzaException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Le prix doit être un chifre sans le caratère euro");
		}
	}
}



