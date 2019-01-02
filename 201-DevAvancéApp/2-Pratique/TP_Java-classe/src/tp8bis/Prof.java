package tp8bis;

import java.time.LocalDate;

public class Prof extends Personne {
	private static final int AGE_ANCIENNETE = 25;

	public Prof() {
		super();
	}

	public Prof(String nom) {
		setNom(nom);
	}

	@Override
	public int anciennete() {
		int numSince25 = (getDdn().plusYears(AGE_ANCIENNETE)).until(LocalDate.now()).getYears();
		int numSinceArrival = getDateArrivee().until(LocalDate.now()).getYears();
		return Math.max(0, Math.min(numSince25, numSinceArrival));
	}
}
