package tp8bis;

import java.util.Hashtable;

public class School {
	Hashtable<String, ClassGroup> mesClasses;

	public School() {
		mesClasses = new Hashtable<String, ClassGroup>();
	}

	public void addClassGroup(String name, ClassGroup c) {
		mesClasses.put(name, c);
	}

	public ClassGroup getClassGroup(String name) {
		return mesClasses.get(name);
	}

	public String getClassesNames() {
		String result = "";
		for (String name : mesClasses.keySet()) {
			result += (name + "\n");
		}
		return result;
	}

	public String toString() {
		String result = "";
		for(String name : mesClasses.keySet()) {
			result += (name + "\n");
			result += mesClasses.get(name).toString();
		}
		return result;
	}

	public static void main(String[] args) {
		School ephec = new School();
		ClassGroup ti1 = new ClassGroup(new Prof("Delvigne"));
		ClassGroup ti2 = new ClassGroup(new Prof("VVDS"));
		ClassGroup ti3 = new ClassGroup(new Prof("Dewulf"));
		ti1.addStudent(new Etu("Mirica"));
		ti2.addStudent(new Etu("Henry"));
		ti3.addStudent(new Etu("Bilongo"));

		ephec.addClassGroup("1T", ti1);
		ephec.addClassGroup("2T", ti2);
		ephec.addClassGroup("3T", ti3);

		System.out.println(ephec);
	}
}
