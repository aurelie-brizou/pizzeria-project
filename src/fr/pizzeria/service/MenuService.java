package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;

public abstract class MenuService {
	protected IPizzaDao pizzaDao;
	
	public MenuService(IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
	

	public abstract void executeUC (Scanner scanner);
	
	
}
