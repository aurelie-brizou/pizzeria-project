package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJdbcDao;
import fr.pizzeria.exception.StockageException;

public class InitialiseBdd extends MenuService {

	public InitialiseBdd (IPizzaDao pizzaDao) {
		super(pizzaDao);
	}


	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		pizzaDao.initializeDb();

	}


}
