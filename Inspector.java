
public class Inspector {

	public void inspect(Object obj, boolean recursive) {
		Class c = obj.getClass();
		inspectClass(c, obj, recursive, 0);
	}

	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		System.out.println("Class name: " + c.getName());
		System.out.println("Superclass name: " + c.getSuperclass().getName());
		
		inspectInterfaces(c, obj, recursive, depth);
		//inspectConstructor(c, obj, recursive, depth);
		
	}
	
	private void inspectInterfaces(Class c, Object obj, boolean recursive, int depth) {
		Class[] interfaces = c.getInterfaces();
		int length = interfaces.length;
		System.out.println("Interfaces: ");
		
		for (int i = 0; i < length; i++){
			System.out.println(interfaces[i]);
		}
	}


}