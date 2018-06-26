package fr.pizzeria.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfProvider {
	
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getInstance() {
		if (emf==null) {
			emf = Persistence.createEntityManagerFactory("pizzeria");
		}
		return emf;
	}

}
