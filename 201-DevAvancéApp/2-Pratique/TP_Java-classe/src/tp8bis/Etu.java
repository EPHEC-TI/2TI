package tp8bis;

import java.time.LocalDate;

public class Etu extends Personne {
	public Etu(String nom) {
		this.setNom(nom);
	}

	public Etu() {
		super();
	}

	public String matricule() {
		return (getNom().charAt(0) + "" + getDateArrivee().getYear());
	}

	@Override
	public int anciennete() {
		return getDateArrivee().until(LocalDate.now()).getYears();
	}

	public String toString() {
		return super.toString() + " " + matricule();
	}
}
