package tp3;

import javax.swing.JOptionPane;

public class Date {
	private int jour;
	private int mois;
	private int annee;
	
	public Date(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	
	public Date() {
		this.jour = Integer.parseInt(JOptionPane.showInputDialog("Entrez le jour"));
		this.mois = Integer.parseInt(JOptionPane.showInputDialog("Entrez le mois"));
		this.annee = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'année"));
	}
	
	//getters et setters
	
	public String toString() {
		return (jour+"/"+mois+"/"+annee);
	}
}
