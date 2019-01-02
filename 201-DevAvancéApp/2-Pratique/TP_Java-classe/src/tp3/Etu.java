package tp3;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Etu {
	private String nom;
	private String prenom;
	private String matricule;
	private Date ddn;
	private int [] results;
	
	public Etu() {
		this.nom = JOptionPane.showInputDialog("Entrez le nom");
		this.prenom = JOptionPane.showInputDialog("Entrez le prenom");
		this.matricule = JOptionPane.showInputDialog("Entrez le matricule");
		this.ddn = new Date(1,1,2000);
		this.results = new int [5]; //new int [Integer.parseInt(JOptionPane.showInputDialog("Entrez le nbre de cotes"))];
	}
	
	public Etu(String nom, String prenom, String matricule, Date ddn, int [] results) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.ddn = ddn;
		this.results = results;
	}
	
	//getters et setters
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

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Date getDdn() {
		return ddn;
	}

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public int[] getResults() {
		return results;
	}

	public void setResults(int[] results) {
		this.results = results;
	}

	public String toString() {
		//this.ddn ou this.ddn.toString, c'est la même chose.
		return("nom ="+this.nom+", prenom="+this.prenom+", matricule="+this.matricule+
				", dateNaissance="+this.ddn+", resultats="+Arrays.toString(results));
	}
	
	public double moyenne() {
		double somme = 0.0;
		for(int r : results) {
			somme += r;
		}
		return(somme/(double)results.length);
	}
}
