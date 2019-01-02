package tp4;

public class MainEtu {
	public static void main(String[] args) {
		Etu paul = new Etu("Nom1", "Paul");
		Etu marc = new Etu("Nom2", "Marc");
		paul.setResultats(new int[] {10,10,10,10,10});
		marc.setResultats(new int[] {12,12,12,12,12});
		System.out.println(paul);
		System.out.println(marc);
		System.out.println("Moyenne de Paul: "+paul.moyenne());
		System.out.println("Moyenne de Marc: "+marc.moyenne());
		System.out.println("Marc et Paul sont-ils égaux ? "+marc.equals(paul));
		Etu clonePaul = new Etu();
		clonePaul.setMatricule(paul.getMatricule());
		System.out.println("clonePaul et Paul sont-ils égaux ? "+clonePaul.equals(paul));
		System.out.println("Nous avons créé "+Etu.getNbEtu()+" étudiants.");
	}
}
