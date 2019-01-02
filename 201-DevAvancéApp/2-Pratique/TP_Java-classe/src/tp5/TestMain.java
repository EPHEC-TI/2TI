package tp5;

public class TestMain {
	public static void main(String[] args) {
		Personne [] tabPers = new Personne[3];
		tabPers[0] = new Personne("Dewulf", "Arnaud", 1234);
		tabPers[1] = new Employe("Hazard", "Eden", 1235, 250000, "Chelsea");
		tabPers[2] = new Independant("Dupont", "Marc", 1236, "BE123456");
		
		for(int i = 0; i<tabPers.length; i++) {
			System.out.println(tabPers[i]);
		}
	}
}
