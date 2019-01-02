package tp8bis;

import java.time.LocalDate;

public abstract class Personne {
	private String nom;
	private String prenom;
	private LocalDate ddn;
	private LocalDate dateArrivee;

	public Personne() {

	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the ddn
	 */
	public LocalDate getDdn() {
		return ddn;
	}

	/**
	 * @param ddn the ddn to set
	 */
	public void setDdn(LocalDate ddn) {
		this.ddn = ddn;
	}
	
	/**
	 * @return the dateArrivee
	 */
	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	/**
	 * @param dateArrivee the dateArrivee to set
	 */
	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public int age() {
		return ddn.until(LocalDate.now()).getYears();
	}
	
	public abstract int anciennete();
	
	public String toString() {
		return prenom + " " + nom + " " + age() + " " + anciennete();
	}
}
