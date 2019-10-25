import java.lang.reflect.*;

public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		Class c = obj.getClass();
		inspectClass(c, obj, recursive, 0);
	}

	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("CLASS NAME");
		printTabs(depth);
		System.out.println(c.getSimpleName());
		//printTabs(depth);
		//System.out.println("SUPERCLASS NAME");
		//System.out.println(c.getSuperclass().getSimpleName());

		
		inspectInterfaces(c, obj, recursive, depth);
		inspectConstructors(c, obj, recursive, depth);
		inspectMethods(c, obj, recursive, depth);
		inspectFields(c, obj, recursive, depth);
		
		if (c.getSuperclass() != null) {
			depth = depth + 2;
			System.out.println("============================");
			System.out.println("SUPERCLASS NAME" + "\n" + c.getSuperclass().getSimpleName());
			inspectClass(c.getSuperclass(), obj, recursive, depth);
		}

	}
	
	private void printTabs(int depth){
		for(int i = 0; i < depth; i++){
		System.out.print("\t");
		}
	}

	private void inspectInterfaces(Class c, Object obj, boolean recursive, int depth) {
		Class[] interfaces = c.getInterfaces();
		int length = interfaces.length;
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("INTERFACES");

		if (length == 0) {
			printTabs(depth);
			System.out.println("None");
		} else {
			for (int i = 0; i < length; i++) {
				printTabs(depth);
				System.out.println(interfaces[i].getSimpleName());
			}
		}
	}

	private void inspectConstructors(Class c, Object obj, boolean recursive, int depth) {
		Constructor[] constructors = c.getDeclaredConstructors();
		int constructorLength = constructors.length;
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("CONSTRUCTORS");

		if (constructorLength == 0) {
			System.out.println("None");
		} else {
			for (int i = 0; i < constructorLength; i++) {
				Constructor x = constructors[i];
				printTabs(depth);
				System.out.println("===============");
				printTabs(depth);
				System.out.println(x.getName());
				Class[] parameters = x.getParameterTypes();
				int parametersLength = parameters.length;
				printTabs(depth);
				System.out.println("PARAMETER TYPES");

				if (parametersLength == 0) {
					printTabs(depth);
					System.out.println("None");
				} else {
					for (int j = 0; j < parametersLength; j++) {
						printTabs(depth);
						System.out.println(parameters[j].getSimpleName());
					}
				}

				printTabs(depth);
				System.out.println("MODIFIERS");
				int modifierInt = x.getModifiers();
				printTabs(depth);
				System.out.println(Modifier.toString(modifierInt));

			}
		}

	}

	private void inspectMethods(Class c, Object obj, boolean recursive, int depth) {
		Method[] methods = c.getDeclaredMethods();
		int methodsLength = methods.length;
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("METHODS");

		if (methodsLength == 0) {
			printTabs(depth);
			System.out.println("None");
		} else {
			for (int i = 0; i < methodsLength; i++) {
				Method x = methods[i];
				printTabs(depth);
				System.out.println("===============");
				printTabs(depth);
				System.out.println(x.getName());

				printTabs(depth);
				System.out.println("EXCEPTIONS");
				Class[] exceptions = x.getExceptionTypes();
				int exceptionsLength = exceptions.length;
				if (exceptionsLength == 0) {
					printTabs(depth);
					System.out.println("None");
				} else {
					for (int j = 0; j < exceptionsLength; j++) {
						printTabs(depth);
						System.out.println(exceptions[j]);
					}
				}

				printTabs(depth);
				System.out.println("PARAMETER TYPES");
				Class[] parameters = x.getParameterTypes();
				int parametersLength = parameters.length;
				if (parametersLength == 0) {
					printTabs(depth);
					System.out.println("None");
				} else {
					for (int k = 0; k < parametersLength; k++) {
						printTabs(depth);
						System.out.println(parameters[k].getSimpleName());
					}
				}

				printTabs(depth);
				System.out.println("RETURN TYPE");
				Class returnType = x.getReturnType();
				printTabs(depth);
				System.out.println(returnType.getSimpleName());

				printTabs(depth);
				System.out.println("MODIFIERS");
				int modifierInt = x.getModifiers();
				printTabs(depth);
				System.out.println(Modifier.toString(modifierInt));

			}
		}
	}

	private void inspectFields(Class c, Object obj, boolean recursive, int depth) {
		Field[] fields = c.getDeclaredFields();
		int fieldsLength = fields.length;
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("FIELDS");

		if (fieldsLength == 0) {
			printTabs(depth);
			System.out.println("None");
		} else {
			for (int i = 0; i < fieldsLength; i++) {
				Field x = fields[i];
				printTabs(depth);
				System.out.println("===============");
				printTabs(depth);
				System.out.println(x.getName());

				if (!Modifier.isPublic(x.getModifiers())) {
					x.setAccessible(true);
				}

				printTabs(depth);
				System.out.println("TYPES");
				printTabs(depth);
				System.out.println(x.getType().getSimpleName());

				printTabs(depth);
				System.out.println("MODIFIERS");
				int modifierInt = x.getModifiers();
				printTabs(depth);
				System.out.println(Modifier.toString(modifierInt));

				System.out.println("CURRENT VALUE");
				try {
					Object value = x.get(obj);
					printTabs(depth);
					System.out.println(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
