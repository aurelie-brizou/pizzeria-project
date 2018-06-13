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
	 * Cr�ation d'une "Rule" qui va permettre de substituer le System.in utilis�
	 * par le Scanner par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	// cr�ation d'un scanner qui va prendre la saisie du mock
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
		// Appel de la m�thode findAllPizzas pour laquelle on retourne une variable List<Pizza>
		List<Pizza> list = dao.findAllPizzas();
		// cr�ation d'une variable qui m�morise le r�sultat du calcul de list.size() taille de list
		int taille = list.size();
		// on compare taille de list avec l'ajout automatique et la m�thode qui retourne toutes les pizzas
		assertEquals(taille, dao.findAllPizzas());
		
	}


}
