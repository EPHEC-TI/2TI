package tp8bis;

import java.util.ArrayList;

public class ClassGroup {
	private Prof titulaire;
	private ArrayList<Etu> students;

	public ClassGroup(Prof titulaire) {
		this.titulaire = titulaire;
		students = new ArrayList<Etu>();
	}

	public void addStudent(Etu e) {
		this.students.add(e);
	}

	public void removeStudent(Etu e) {
		this.students.remove(e);
	}

	public int getNumStudent() {
		return this.students.size();
	}

	public String toString() {
		String result = "Titulaire: " + titulaire.getNom() + "\n";
		result += "Liste des étudiants: \n";
		for(Etu e : this.students) {
			result += (e.getNom() + "\n");
		}
		return result;
	}
	
	public static void main(String[] args) {
		ClassGroup maClasse = new ClassGroup(new Prof("Bouterfa"));
		maClasse.addStudent(new Etu("Henry"));
		maClasse.addStudent(new Etu("Mirica"));
		maClasse.addStudent(new Etu("Bilongo"));
		maClasse.addStudent(new Etu("Ndamtang"));
		
		Etu toto = new Etu("Dewulf");
		maClasse.addStudent(toto);
		System.out.println(maClasse);
		maClasse.removeStudent(toto);
		System.out.println(maClasse);
	}
}
