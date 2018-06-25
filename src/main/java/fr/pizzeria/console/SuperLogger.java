package fr.pizzeria.console;

import java.lang.reflect.Field;

import fr.pizzeria.utils.ToString;

public class SuperLogger {

	static public void displayInConsole(Object o) {

		System.out.println(getStringToDisplay(o));
	}

	static public String getStringToDisplay(Object o) {
		String toReturn = "";
		try {
			for (Field field : o.getClass().getFields()){
				ToString annot = field.getAnnotation(ToString.class);
				if (annot == null) {
					continue;
				} else if (annot.upperCase()) {

					toReturn += annot.prefixe().toUpperCase()+field.get(o).toString().toUpperCase()+annot.suffixe().toUpperCase();

				} else {
					toReturn += annot.prefixe()+field.get(o).toString()+annot.suffixe();
				}
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toReturn;
	}
}
