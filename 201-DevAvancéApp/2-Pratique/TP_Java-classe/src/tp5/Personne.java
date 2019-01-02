package tp5;

import javax.swing.JOptionPane;

public class Personne {
	private String nom; //jamais mettre une variable en public, c'est dégueulasse...
						//Utiliser protected à la place et étendre la classe sur l'autre classe.
						//Exemple: public class Employe extends Personne{}
	private String prenom;
	private int RN;
	
	public Personne(String nom, String prenom, int RN){
		this.nom = nom;
		this.prenom = prenom;
		this.RN = RN;
	}
	
	public Personne(){
		this.nom = JOptionPane.showInputDialog("Entrez le nom !");
		this.prenom = JOptionPane.showInputDialog("Entrez le prénom !");
		this.RN = Integer.parseInt(JOptionPane.showInputDialog("Entrez le numéro de registre national !"));
	}
	
	@Override
	public String toString() {
		return ("Nom: "+this.nom+"\nPrénom: "+this.prenom+"\nRN: "+this.RN);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + RN;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (RN != other.RN)
			return false;
		return true;
	}
}
