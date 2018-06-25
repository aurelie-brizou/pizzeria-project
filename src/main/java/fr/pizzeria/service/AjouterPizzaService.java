package fr.pizzeria.service;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.CategoriePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {

	public AjouterPizzaService(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}
	public void executeUC(Scanner scanner) throws SavePizzaException {
		System.out.println("Ajout d�une nouvelle pizza");

		try {
			System.out.println("Veuillez saisir le code :");
			scanner.nextLine();
			String newCode = scanner.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String newLibelle = scanner.nextLine();
			System.out.println("Veuillez saisir le prix :");
			
			// utilisation de la librairie NumberUtils pour remplacer par 0 si l'utilisateur n'a rien saisi ou si ce n'est pas un chiffre
			String prixStr = scanner.nextLine();
			if (!NumberUtils.isCreatable(prixStr)) {
				throw new SavePizzaException("LE PRIX DOIT ETRE un nombre.");
			}
			// conversion en double de la r�ponse string du Scanner
			double newPrix = Double.parseDouble(prixStr);

			// ajouter une cat�gorie de pizza avec une r�ponse num�rique
			try  {
				System.out.println("Veuillez saisir la cat�gorie de la pizza 1: viande 2: poisson 3: sans viande");
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
				System.out.println("Vous n'avez pas indiqu� de cat�gorie");
				return;
			}*/





		} catch (SavePizzaException e) {
			System.out.println(e.getMessage());
		}
	}

}
