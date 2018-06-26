package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.pizzeria.console.SuperLogger;
import fr.pizzeria.utils.ToString;

@Entity
@Table(name="PIZZAS")
public class Pizza implements Comparable<Pizza>{
	// attributs
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="CODE")
	@ToString(upperCase=true, suffixe=" -> ")
	public String code;
	
	@Column(name="LIBELLE")
	@ToString(suffixe=", ")
	public String libelle;
	
	@Column(name="PRIX")
	@ToString(suffixe="€")
	public double prix;
	
	public static int currentId =+ 1;
	
	@Column(name="CATE_PIZZA")
	public CategoriePizza categoriePizza;
	
	
	public Pizza() {
		super();
	}

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
