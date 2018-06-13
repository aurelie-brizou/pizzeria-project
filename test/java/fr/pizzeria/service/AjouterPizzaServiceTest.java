package fr.pizzeria.service;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.util.List;
import java.util.Scanner;

public class AjouterPizzaServiceTest {

	/**
	 * Création d'une "Rule" qui va permettre de substituer le System.in utilisé
	 * par le Scanner par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	// création d'un scanner qui va prendre la saisie du mock
	private Scanner scanner = new Scanner(System.in);

	@Test
	public void testExecuteUC() {
		
		IPizzaDao dao = new PizzaMemDao();
		
		// J'alimente le mock avec la valeur
		systemInMock.provideLines("MEX", "LaMexicaine", "12.50", "VIANDE");
		AjouterPizzaService service = new AjouterPizzaService(dao);
		try {
			service.executeUC(scanner);
		} catch (SavePizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// verifier que la dao contient 8 pizzas
		// Appel de la méthode findAllPizzas pour laquelle on retourne une variable List<Pizza>
		List<Pizza> list = dao.findAllPizzas();
		// création d'une variable qui mémorise le résultat du calcul de list.size() taille de list
		int taille = list.size();
		// on compare taille de list avec l'ajout automatique et la méthode qui retourne toutes les pizzas
		assertEquals(taille, dao.findAllPizzas());
		
	}


}
