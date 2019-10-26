import java.lang.reflect.*;

public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		Class c = obj.getClass();
		inspectClass(c, obj, recursive, 0);
	}

	//inspecting class (runs recursion on classes)
	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("CLASS NAME");
		printTabs(depth);
		System.out.println(c.getSimpleName());
		

		if ((c.getSuperclass() != null) && (recursive == true)){
			printTabs(depth);
			System.out.println("============================");
			printTabs(depth);
			System.out.println("SUPERCLASS NAME");
			printTabs(depth);
			System.out.println(c.getSuperclass().getSimpleName());
			inspectClass(c.getSuperclass(), obj, recursive, depth + 2);
		}

		inspectInterfaces(c, obj, recursive, depth);
		inspectConstructors(c, obj, recursive, depth);
		inspectMethods(c, obj, recursive, depth);
		inspectFields(c, obj, recursive, depth);
		
	}
	
	//method for adding tabs to print statements
	private void printTabs(int depth){
		for(int i = 0; i < depth; i++){
		System.out.print("\t");
		}
	}
	
	//inspects interfaces (runs recursion on interfaces)
	private void inspectInterfaces(Class c, Object obj, boolean recursive, int depth) {
		Class[] interfaces = c.getInterfaces();
		int interfaceLength = interfaces.length;
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("INTERFACES");

		if (interfaceLength == 0) {
			printTabs(depth);
			System.out.println("None");
		} else {
			for (int i = 0; i < interfaceLength; i++) {
				printTabs(depth);
				System.out.println(interfaces[i].getSimpleName());
				
				inspectClass(interfaces[i], obj, recursive, depth + 2);
			}
		}
	}

	//inspects constructors 
	private void inspectConstructors(Class c, Object obj, boolean recursive, int depth) {
		Constructor[] constructors = c.getDeclaredConstructors();
		int constructorLength = constructors.length;
		printTabs(depth);
		System.out.println("============================");
		printTabs(depth);
		System.out.println("CONSTRUCTORS");

		if (constructorLength == 0) {
			printTabs(depth);
			System.out.println("None");
		} else {
			for (int i = 0; i < constructorLength; i++) {
				Constructor currentCon = constructors[i];
				printTabs(depth);
				System.out.println("===============");
				printTabs(depth);
				System.out.println(currentCon.getName());
				Class[] parameters = currentCon.getParameterTypes();
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
				int modifierInt = currentCon.getModifiers();
				printTabs(depth);
				System.out.println(Modifier.toString(modifierInt));

			}
		}

	}

	//inspects methods
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
				Method currentMethod = methods[i];
				printTabs(depth);
				System.out.println("===============");
				printTabs(depth);
				System.out.println(currentMethod.getName());

				printTabs(depth);
				System.out.println("EXCEPTIONS");
				Class[] exceptions = currentMethod.getExceptionTypes();
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
				Class[] parameters = currentMethod.getParameterTypes();
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
				Class returnType = currentMethod.getReturnType();
				printTabs(depth);
				System.out.println(returnType.getSimpleName());

				printTabs(depth);
				System.out.println("MODIFIERS");
				int modifierInt = currentMethod.getModifiers();
				printTabs(depth);
				System.out.println(Modifier.toString(modifierInt));

			}
		}
	}

	//inspects fields
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
				Field currentField = fields[i];
				printTabs(depth);
				System.out.println("===============");
				printTabs(depth);
				System.out.println(currentField.getName());

				if (!Modifier.isPublic(currentField.getModifiers())) {
					currentField.setAccessible(true);
				}

				printTabs(depth);
				System.out.println("TYPES");
				printTabs(depth);
				System.out.println(currentField.getType().getSimpleName());

				printTabs(depth);
				System.out.println("MODIFIERS");
				int modifierInt = currentField.getModifiers();
				printTabs(depth);
				System.out.println(Modifier.toString(modifierInt));

				printTabs(depth);
				System.out.println("CURRENT VALUE");
				try {
					Object value = currentField.get(obj);
					printTabs(depth);
					System.out.println(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
