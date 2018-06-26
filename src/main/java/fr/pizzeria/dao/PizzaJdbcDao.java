package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Aurelie-B
 * Représente une implémentation JDBC de IPizzaDao pour stocker les pizzas en BDD 
 */
public class PizzaJdbcDao implements IPizzaDao {


	/** 
	 * Trouver les pizzas de la BDD
	 * @param String codePizza, Pizza pizza
	 * @return liste de pizza
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc"); // pour accéder jdbc.properties (paramètres d'accès à la base de données)

		Connection myConnection = null;
		Statement statement = null;
		ResultSet result = null;

		List<Pizza> pizzaList = new ArrayList<Pizza>();

		try {
			// instruction Class.forName charge l’implémentation mariaDB 
			Class.forName("org.mariadb.jdbc.Driver");

			// créer une connexion
			myConnection = DriverManager.getConnection(jdbcProperties.getString("URL"),
					jdbcProperties.getString("USER"), jdbcProperties.getString("PASSWORD"));
			myConnection.setAutoCommit(false);

			statement = myConnection.createStatement();
			result = statement.executeQuery("SELECT* FROM pizzas");

			while (result.next()) {
				Integer id = result.getInt("ID");
				String code = result.getString("CODE");
				String libelle = result.getString("LIBELLE");
				Double prix = result.getDouble("PRIX");
				String categorie = result.getString("CATEGORIE");

				pizzaList.add(new Pizza(code, libelle, prix, null));

			}

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("driver introuvable.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

		} finally { // utilisation du bloc finally pour fermer les ressources.
			try {
				if (result != null) {
					result.close(); // ResultSet doit être fermé après usage
				}
				if (statement != null) {
					statement.close(); // Statement doit être fermé après usage
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (myConnection != null) {
						myConnection.commit(); // Connection doit être fermée après usage.
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return pizzaList;
	}

	/** 
	 * Sauvegarder des pizzas dans la BDD
	 * @param String codePizza, Pizza pizza
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");

		Connection myConnection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			myConnection = DriverManager.getConnection(jdbcProperties.getString("URL"),
					jdbcProperties.getString("USER"), jdbcProperties.getString("PASSWORD"));
			myConnection.setAutoCommit(false);

			statement = myConnection
					.prepareStatement("INSERT INTO pizzas (CODE, LIBELLE, PRIX, CATEGORIE) VALUES(?, ?, ? , ?)");
			statement.setString(1, pizza.code);
			statement.setString(2, pizza.libelle);
			statement.setDouble(3, pizza.prix);
			statement.setString(4, pizza.categoriePizza.name());
			statement.executeUpdate();

			if (pizzaExists(pizza.code) == true) {
				throw new SavePizzaException();
			}

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("driver introuvable.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (myConnection != null) {
						myConnection.commit();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

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

		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");

		Connection myConnection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			myConnection = DriverManager.getConnection(jdbcProperties.getString("URL"),
					jdbcProperties.getString("USER"), jdbcProperties.getString("PASSWORD"));

			myConnection.setAutoCommit(false);

			statement = myConnection
					.prepareStatement("UPDATE pizzas SET CODE=?, LIBELLE=?, PRIX=?, CATEGORIE=? WHERE CODE=?");
			statement.setString(1, pizza.code);
			statement.setString(2, pizza.libelle);
			statement.setDouble(3, pizza.prix);
			statement.setString(4, pizza.categoriePizza.name());
			statement.setString(5, codePizza);
			statement.executeUpdate();

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("driver introuvable.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (myConnection != null) {
						myConnection.commit();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

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

		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");

		Connection myConnection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			myConnection = DriverManager.getConnection(jdbcProperties.getString("URL"),
					jdbcProperties.getString("USER"), jdbcProperties.getString("PASSWORD"));

			myConnection.setAutoCommit(false);

			statement = myConnection.prepareStatement("DELETE FROM PIZZAS WHERE CODE=?");
			statement.setString(1, codePizza);
			statement.executeUpdate();

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("driver introuvable.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (myConnection != null) {
						myConnection.commit();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/** 
	 * Trouver une pizza dans la BDD grâce à son code
	 * @param String codePizza
	 */
	@Override
	public Pizza findPizzaByCode(String codePizza) {
		ResourceBundle jdbcProperties = ResourceBundle.getBundle("jdbc");

		Connection myConnection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			myConnection = DriverManager.getConnection(jdbcProperties.getString("URL"),
					jdbcProperties.getString("USER"), jdbcProperties.getString("PASSWORD"));

			myConnection.setAutoCommit(false);

			statement = myConnection.prepareStatement("SELECT * FROM PIZZAS WHERE CODE=?");
			statement.setString(1, codePizza);
			result = statement.executeQuery();

			if (result.next()) {
				Integer id = result.getInt("ID");
				String code = result.getString("CODE");
				String libelle = result.getString("LIBELLE");
				Double prix = result.getDouble("PRIX");
				String categorie = result.getString("CATEGORIE");

				Pizza p = new Pizza(code, libelle, prix, CategoriePizza.valueOf(categorie));
				return p;

			}

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("driver introuvable.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				myConnection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (myConnection != null) {
						myConnection.commit();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}
}
