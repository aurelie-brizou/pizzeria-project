package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

public class SupprimerPizzaService extends MenuService {

	public SupprimerPizzaService(IPizzaDao pizzaDao) {
		super(pizzaDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeUC(Scanner scanner) {
		System.out.println("Suppression d’une pizza");
		
		try {
		System.out.println("Veuillez choisir le code de la pizza à supprimer");
		scanner.nextLine();
		String userChoiceDelete = scanner.nextLine();
		pizzaDao.deletePizza(userChoiceDelete);
		
		} catch (DeletePizzaException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
