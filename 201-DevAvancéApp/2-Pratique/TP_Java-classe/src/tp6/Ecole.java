package tp6;

import java.time.LocalDate;

public class Ecole {

	// throws DateArriveeInvalideException : possible de faire mais � �viter.
	public static void main(String[] args) {
		Personne toto = new Etu();
		toto.setNom("Hazard");
		toto.setPrenom("Eden");
		toto.setDateNaissance(LocalDate.of(2000, 10, 10));
		System.out.println(toto);
		try {
			toto.setDateArrivee(LocalDate.of(2001, 1, 1));
		} catch (DateArriveeInvalideException e) {
			// Le minimum � faire quand on g�re une exception.
			e.printStackTrace();
		}

	}
}
