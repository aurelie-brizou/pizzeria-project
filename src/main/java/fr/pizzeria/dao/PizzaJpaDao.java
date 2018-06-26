package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Aurelie-B 
 * Représente une implémentation JPA de IPizzaDao pour stocker les pizzas en BDD
 */
public class PizzaJpaDao implements IPizzaDao {

	static {
		EmfProvider.getInstance();
	}
	/** 
	 * Trouver les pizzas de la BDD
	 * @param String codePizza, Pizza pizza
	 * @return liste de pizzas
	 */
	@Override public List<Pizza> findAllPizzas() {
		
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class); 
		List<Pizza> pizzas = query.getResultList();

		em.close();
		return pizzas;
	}

	/** 
	 * Sauvegarder des pizzas dans la BDD
	 * @param String codePizza, Pizza pizza
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		
		if (pizzaExists(pizza.code) == true) {
			throw new SavePizzaException();
		}
			
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();	
		em.persist(pizza);
		et.commit();
		em.close();
	}
	
	/** 
	 * Modifier une pizza dans la BDD
	 * @param String codePizza, Pizza pizza
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		if (pizzaExists(codePizza) == false) {
			throw new UpdatePizzaException();
		}

		EntityManager em = EmfProvider.getInstance().createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();

		// modifier une instance
		Query query = em.createQuery("UPDATE Pizza p SET p.code=?, p.libelle=?, p.prix=?, p.categorie=? WHERE p.code=?");
		query.setParameter(1, pizza.code);
		query.setParameter(2, pizza.libelle);
		query.setParameter(3, pizza.prix);
		query.setParameter(4, pizza.categoriePizza.name());
		query.setParameter(5, codePizza);
		query.executeUpdate();

		et.commit();

		em.close();

	}

	/** 
	 * Supprimer une pizza dans la BDD
	 * @param String codePizza
	 */
	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		if (pizzaExists(codePizza) == false) {
			throw new DeletePizzaException();
		}

		EntityManager em = EmfProvider.getInstance().createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();	

		// réaliser une suppression
		Query query = em.createQuery("DELETE FROM Pizza p WHERE p.code=?");
		query.setParameter(1, codePizza);
		query.executeUpdate();

		et.commit();
		em.close();
	}

	/** 
	 * Trouver une pizza dans la BDD grâce à son code
	 * @param String codePizza
	 */
	@Override
	public Pizza findPizzaByCode(String codePizza) {

		EntityManager em = EmfProvider.getInstance().createEntityManager();

		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code=?", Pizza.class); 
		query.setParameter(1, codePizza);
		List<Pizza> liste = query.getResultList();

		em.close();

		if (!liste.isEmpty())
			return liste.get(0);
		return null;
	}

	/** 
	 * Chercher si une pizza existe dans la BDD
	 * @param String codePizza
	 */
	@Override
	public boolean pizzaExists(String codePizza) {
		Pizza p = findPizzaByCode(codePizza);
		return p != null;
	}

	/** 
	 * Initialise la BDD du tableau de pizzas
	 */
	@Override
	public void initializeDb() {

		List<Pizza> pizzaList= new ArrayList<Pizza>();

		pizzaList.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza("REIN", "La reine", 11.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.SANS_VIANDE));

		for (Pizza p : pizzaList) {
			try {
				saveNewPizza(p);
			} catch (SavePizzaException e) {
				e.printStackTrace();
			}
		}

	}

}
