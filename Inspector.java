import java.lang.reflect.*;

public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		Class c = obj.getClass();
		inspectClass(c, obj, recursive, 0);
	}

	// inspecting class (runs recursion on classes)
	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		printing("============================", depth);
		printing("CLASS NAME", depth);
		printing(c.getSimpleName(), depth);

		if ((c.getSuperclass() != null) && (recursive == true)) {
			printing("============================", depth);
			printing("SUPERCLASS NAME", depth);
			printing(c.getSuperclass().getSimpleName(), depth);
			inspectClass(c.getSuperclass(), obj, recursive, depth + 2);
		}

		inspectInterfaces(c, obj, recursive, depth);
		inspectConstructors(c, obj, recursive, depth);
		inspectMethods(c, obj, recursive, depth);
		inspectFields(c, obj, recursive, depth);

	}

	// method for adding tabs to print statements
	private void printTabs(int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
	}

	// method for simplifying printing
	private void printing(String input, int depth) {
		printTabs(depth);
		System.out.println(input);
	}

	// inspects interfaces (runs recursion on interfaces)
	private void inspectInterfaces(Class c, Object obj, boolean recursive, int depth) {
		printing("============================", depth);
		printing("INTERFACES", depth);

		Class[] interfaces = c.getInterfaces();
		int interfaceLength = interfaces.length;

		if (interfaceLength == 0) {
			printing("None", depth);
		} else {
			for (int i = 0; i < interfaceLength; i++) {
				Class currentInterface = interfaces[i];
				printing(currentInterface.getSimpleName(), depth);
				inspectClass(currentInterface, obj, recursive, depth + 2);
			}
		}
	}

	// inspects constructors
	private void inspectConstructors(Class c, Object obj, boolean recursive, int depth) {
		printing("============================", depth);
		printing("CONSTRUCTORS", depth);

		Constructor[] constructors = c.getDeclaredConstructors();
		int constructorLength = constructors.length;

		if (constructorLength == 0) {
			printing("None", depth);
		} else {
			for (int i = 0; i < constructorLength; i++) {
				printing("===============", depth);
				printing("NAME", depth);
		
				Constructor currentCon = constructors[i];
				printing(currentCon.getName(), depth);
				Class[] parameters = currentCon.getParameterTypes();
				int parametersLength = parameters.length;

				printing("PARAMETER TYPES", depth);

				if (parametersLength == 0) {
					printing("None", depth);
				} else {
					for (int j = 0; j < parametersLength; j++) {
						printing(parameters[j].getSimpleName(), depth);
					}
				}

				printing("MODIFIERS", depth);
				int modifierInt = currentCon.getModifiers();
				printing(Modifier.toString(modifierInt), depth);

			}
		}

	}

	// inspects methods
	private void inspectMethods(Class c, Object obj, boolean recursive, int depth) {
		printing("============================", depth);
		printing("METHODS", depth);

		Method[] methods = c.getDeclaredMethods();
		int methodsLength = methods.length;
		
		if (methodsLength == 0) {
			printing("None", depth);
		} else {
			for (int i = 0; i < methodsLength; i++) {
				printing("===============", depth);
				printing("NAME", depth);
				
				Method currentMethod = methods[i];
				printing(currentMethod.getName(), depth);

				printTabs(depth);
				System.out.println("EXCEPTIONS");
				Class[] exceptions = currentMethod.getExceptionTypes();
				int exceptionsLength = exceptions.length;
				if (exceptionsLength == 0) {
					printing("None", depth);
				} else {
					for (int j = 0; j < exceptionsLength; j++) {
						printing(exceptions[j].getSimpleName(), depth);
					}
				}

				printTabs(depth);
				System.out.println("PARAMETER TYPES");
				Class[] parameters = currentMethod.getParameterTypes();
				int parametersLength = parameters.length;
				if (parametersLength == 0) {
					printing("None", depth);
				} else {
					for (int k = 0; k < parametersLength; k++) {
						printing(parameters[k].getSimpleName(), depth);
					}
				}


				printing("RETURN TYPE", depth);
				
				Class returnType = currentMethod.getReturnType();
				printing(returnType.getSimpleName(), depth);

				printing("MODIFIERS", depth);
				
				int modifierInt = currentMethod.getModifiers();
				printing(Modifier.toString(modifierInt), depth);

			}
		}
	}

	// inspects fields
	private void inspectFields(Class c, Object obj, boolean recursive, int depth) {
		printing("============================", depth);
		printing("FIELDS", depth);
		
		Field[] fields = c.getDeclaredFields();
		int fieldsLength = fields.length;

		if (fieldsLength == 0) {
			printing("None", depth);
		} else {
			for (int i = 0; i < fieldsLength; i++) {
				printing("===============", depth);
				printing("NAME", depth);
				
				Field currentField = fields[i];
				printing(currentField.getName(), depth);

				if (!Modifier.isPublic(currentField.getModifiers())) {
					currentField.setAccessible(true);
				}

				printing("TYPES", depth);
				printing(currentField.getType().getSimpleName(), depth);

				printing("MODIFIERS", depth);
				int modifierInt = currentField.getModifiers();
				printing(Modifier.toString(modifierInt), depth);

				printing("CURRENT VALUE", depth);
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
