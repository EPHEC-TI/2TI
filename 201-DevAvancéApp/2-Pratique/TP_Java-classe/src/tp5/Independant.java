package tp5;

import javax.swing.JOptionPane;

public class Independant extends Personne {
	private String TVA;
	
	public Independant() {
		super();
		this.TVA = JOptionPane.showInputDialog("Entrez le numéro de TVA");
	}
	
	public Independant(String nom, String prenom, int RN, String TVA) {
		super(nom, prenom, RN);
		this.TVA = TVA;
	}
	
	@Override
	public String toString() {
		return (super.toString()+"\nTVA: "+this.TVA);
	}
}
