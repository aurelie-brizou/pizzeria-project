package fr.pizzeria.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {

	PizzaMemDao mem = new PizzaMemDao();

	// Test Save sans l'exception
	@Test
	public void testSaveNewPizza() {
		try {
			mem.saveNewPizza(new Pizza("MEX", "LaMexicaine", 15.8, CategoriePizza.SANS_VIANDE));
		}
		catch (SavePizzaException e) {
			fail("Le test n'aurait pas du lever une exception");
		}
	}

	// Test Save avec l'exception
	@Test (expected=SavePizzaException.class)
	public void testSavePizzaExceptionExists() throws SavePizzaException {
		mem.saveNewPizza(new Pizza("PEP", "test", 14.4, CategoriePizza.VIANDE));
	}

	// Test Update sans l'exception
	@Test
	public void testUpdatePizza() {
		try {
			mem.updatePizza("PEP", new Pizza("PEP", "test", 14.4, CategoriePizza.VIANDE));
		}
		catch (UpdatePizzaException e) {
			fail("Le test n'aurait pas du lever une exception");
		}
	}

	// Test Update avec l'exception
	@Test (expected=UpdatePizzaException.class)
	public void testUpdatePizzaNotExists() throws UpdatePizzaException {
		mem.updatePizza ("Mex", new Pizza("Mex", "LaMexicaine", 15.8, CategoriePizza.SANS_VIANDE));
	}

	// Test Delete sans l'exception
	@Test
	public void testDeletePizza() {
		try {
			mem.deletePizza("PEP");
		}
		catch (DeletePizzaException e) {
			fail("Le test n'aurait pas du lever une exception");
		}
	}

	// Test Delete avec l'exception
	@Test (expected=DeletePizzaException.class)
	public void testDeletePizzaNotExists() throws DeletePizzaException {
		mem.deletePizza ("Mex");
	}

}
