package telran.reflect.SchemaProperties;

import java.lang.reflect.Field;

import telran.reflect.ID;
import telran.reflect.Index;

public class SchemaProperties {
	public static void displayFieldProperties(Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		String idFieldName = null;
		boolean idFound = false;
		for (Field field : fields) {
			if (field.isAnnotationPresent(ID.class)) {
				if (idFound) {
					throw new IllegalStateException("Field Id must be one");
				}
				idFieldName = field.getName();
				idFound = true;
			}
		}
		if (!idFound) {
			throw new IllegalStateException("No field Id found");
		}
		System.out.println("Field annotated by @Id: " + idFieldName);
		System.out.println("Value of field annotated by @Id: " + getField(obj, idFieldName));
		System.out.print("Fields annotated by @Index: ");
		boolean indexFound = false;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Index.class)) {
				System.out.println(field.getName() + " = " + getField(obj, field.getName()) + " ");
				indexFound = true;
			}
		}
		if (!indexFound) {
			System.out.print("None");
		}
	}
	private static Object getField(Object obj, String fieldName) throws Exception {
		Field field = obj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(obj);
	}
}