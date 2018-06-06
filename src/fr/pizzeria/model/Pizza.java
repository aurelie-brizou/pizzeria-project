package fr.pizzeria.model;

import java.lang.reflect.Field;

import fr.pizzeria.console.SuperLogger;
import fr.pizzeria.utils.ToString;

public class Pizza implements Comparable<Pizza>{
	// attributs
	public int id;
	@ToString(upperCase=true)
	public String code;
	@ToString
	public String libelle;
	public double prix;
	public static int currentId =+ 1;
	
	public CategoriePizza categoriePizza;
	
	public Pizza (String code, String libelle, double prix, CategoriePizza categoriePizza) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		Pizza.currentId =+ 1;
		this.categoriePizza = categoriePizza;
		
	}

	public Pizza (int id, String code, String libelle, double prix) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
	
	@Override
	public String toString(){
		//return code + " -> " + libelle + ", " + prix +" € "+ categoriePizza;
		return SuperLogger.getStringToDisplay(this);
	}


	// tri des éléments on implémente la classe Comparable 
	public int compareTo(Pizza o) {
			
		// TODO Auto-generated method stub
		return this.code.compareTo(o.code);
	}
}
