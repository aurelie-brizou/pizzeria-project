package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.CategoriePizzaException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {

	public AjouterPizzaService(IPizzaDao pizzaDao) {
		super(pizzaDao);
		// TODO Auto-generated constructor stub
	}
	public void executeUC(Scanner scanner) {
		System.out.println("Ajout d’une nouvelle pizza");

		try {
			System.out.println("Veuillez saisir le code :");
			scanner.nextLine();
			String newCode = scanner.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String newLibelle = scanner.nextLine();
			System.out.println("Veuillez saisir le prix :");
			// conversion en double de la réponse string du Scanner
			double newPrix = Double.parseDouble(scanner.nextLine());

			// ajouter une catégorie de pizza avec une réponse numérique
			try  {
				System.out.println("Veuillez saisir la catégorie de la pizza 1: viande 2: poisson 3: sans viande");
				int userChoice = scanner.nextInt();

				CategoriePizza newCategoriePizza = CategoriePizza.getCategoriePizza(userChoice);
				// ajout d'une nouvelle pizza dans le menu
				pizzaDao.saveNewPizza(new Pizza(newCode, newLibelle, newPrix, newCategoriePizza));
				
			} catch (CategoriePizzaException e) {
				System.out.println(e.getMessage());
			}

			// autre utilisation du switch
			/*switch (userChoice) {
			case 1:
				newCategoriePizza = CategoriePizza.VIANDE;
				break;
			case 2: 
				newCategoriePizza = CategoriePizza.POISSON;
				break;
			case 3:
				newCategoriePizza = CategoriePizza.SANS_VIANDE;
				break;
			default:
				System.out.println("Vous n'avez pas indiqué de catégorie");
				return;
			}*/





		} catch (SavePizzaException e) {
			System.out.println(e.getMessage());
		}
	}

}
