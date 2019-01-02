package tp6;

import java.time.LocalDate;

public abstract class Personne {
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private LocalDate dateArrivee;

	public Personne() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDate dateArrivee) throws DateArriveeInvalideException {
		this.dateArrivee = dateArrivee;
	}

	@Override
	public String toString() {
		return ("Nom: " + this.nom + "Prénom: " + this.prenom + "\nAge: " + this.age());
	}

	public int age() {
		return this.dateNaissance.until(LocalDate.now()).getYears();
	}

	public abstract int anciennete();
}
