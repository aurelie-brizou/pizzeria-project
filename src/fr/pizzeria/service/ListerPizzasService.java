package fr.pizzeria.service;

import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {
	
	public ListerPizzasService(IPizzaDao pizzaDao) {
		super(pizzaDao);
		
	}

	@Override
	public void executeUC(Scanner scanner) {
		System.out.println("Liste des pizzas");
		// boucle pour lire la liste des pizzas
		List<Pizza> pizzaList = pizzaDao.findAllPizzas();
		for (Object object: pizzaList) {
			Pizza pizza = (Pizza)object;
			System.out.println(pizza);
		}
		
	}

}
