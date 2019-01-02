package tp5;

import javax.swing.JOptionPane;

public class Employe extends Personne{
	private int salaire;
	private String employeur;
	
	/*public void affiche() {
	 *	System.out.println("salaire: "+this.salaire+"\nNom: "+nom);
	 *}
	 */
	
	public Employe (String nom, String prenom, int RN, int salaire, String employeur) {
		super(nom, prenom, RN);
		this.salaire = salaire;
		this.employeur = employeur;
	}
	
	public Employe() {
		super(); //Le super doit impérativement être la première ligne d'un constructeur
		this.salaire = Integer.parseInt(JOptionPane.showInputDialog("Entrez le salaire"));
		this.employeur = JOptionPane.showInputDialog("Entrez l'employeur");
	}
	
	public String toString() {
		return (super.toString()+"\nSalaire: "+this.salaire+"\nEmployeur: "+this.employeur);
	}
}
