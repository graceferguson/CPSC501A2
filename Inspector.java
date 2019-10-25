import java.lang.reflect.*;

public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		Class c = obj.getClass();
		inspectClass(c, obj, recursive, 0);
	}

	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		System.out.println("============================");
		System.out.println("CLASS NAME" + "\n" + c.getName());
		System.out.println("SUPERCLASS NAME" + "\n" + c.getSuperclass().getName());
		inspectInterfaces(c, obj, recursive, depth);
		inspectConstructors(c, obj, recursive, depth);
		inspectMethods(c, obj, recursive, depth);
		inspectFields(c, obj, recursive, depth);

	}

	private void inspectInterfaces(Class c, Object obj, boolean recursive, int depth) {
		Class[] interfaces = c.getInterfaces();
		int length = interfaces.length;
		System.out.println("============================");
		System.out.println("INTERFACES");

		if (length == 0) {
			System.out.println("None");
		} else {
			for (int i = 0; i < length; i++) {
				System.out.println(interfaces[i].getSimpleName());
			}
		}
	}

	private void inspectConstructors(Class c, Object obj, boolean recursive, int depth) {
		Constructor[] constructors = c.getDeclaredConstructors();
		int constructorLength = constructors.length;
		System.out.println("============================");
		System.out.println("CONSTRUCTORS");

		if (constructorLength == 0) {
			System.out.println("None");
		} else {
			for (int i = 0; i < constructorLength; i++) {
				Constructor x = constructors[i];
				System.out.println(x.getName());
				Class[] parameters = x.getParameterTypes();
				int parametersLength = parameters.length;
				System.out.println("PARAMETER TYPES");

				if (parametersLength == 0) {
					System.out.println("None");
				} else {
					for (int j = 0; j < parametersLength; j++) {
						System.out.println(parameters[j].getSimpleName());
					}
				}

				System.out.println("MODIFIERS");
				int modifierInt = x.getModifiers();
				System.out.println(Modifier.toString(modifierInt));

			}
		}

	}

	private void inspectMethods(Class c, Object obj, boolean recursive, int depth) {
		Method[] methods = c.getDeclaredMethods();
		int methodsLength = methods.length;
		System.out.println("============================");
		System.out.println("METHODS");

		if (methodsLength == 0) {
			System.out.println("None");
		} else {
			for (int i = 0; i < methodsLength; i++) {
				Method x = methods[i];
				System.out.println("===============");
				System.out.println(x.getName());

				System.out.println("EXCEPTIONS");
				Class[] exceptions = x.getExceptionTypes();
				int exceptionsLength = exceptions.length;
				if (exceptionsLength == 0) {
					System.out.println("None");
				} else {
					for (int j = 0; j < exceptionsLength; j++) {
						System.out.println(exceptions[j]);
					}
				}

				System.out.println("PARAMETER TYPES");
				Class[] parameters = x.getParameterTypes();
				int parametersLength = parameters.length;
				if (parametersLength == 0) {
					System.out.println("None");
				} else {
					for (int k = 0; k < parametersLength; k++) {
						System.out.println(parameters[k].getSimpleName());
					}
				}
				
				System.out.println("RETURN TYPE");
				Class returnType = x.getReturnType();
				System.out.println(returnType.getSimpleName());
				
				
				System.out.println("MODIFIERS");
				int modifierInt = x.getModifiers();
				System.out.println(Modifier.toString(modifierInt));

			}
		}
	}
	
	
	private void inspectFields(Class c, Object obj, boolean recursive, int depth) {
		Field[] fields = c.getDeclaredFields();
		int fieldsLength = fields.length;
		System.out.println("============================");
		System.out.println("FIELDS");
		
		if (fieldsLength == 0){
			System.out.println("None");
		} else {
			for (int i = 0; i < fieldsLength; i++) {
				Field x = fields[i];
				System.out.println("===============");
				System.out.println(x.getName());
				
				if(!Modifier.isPublic(x.getModifiers())){
					x.setAccessible(true);
				}
				
				System.out.println("TYPES");
				System.out.println(x.getType().getSimpleName());
				
				System.out.println("MODIFIERS");
				
				System.out.println("CURRENT VALUE");
			}
		}
	}
	
	
	
	
	
	
	

}
