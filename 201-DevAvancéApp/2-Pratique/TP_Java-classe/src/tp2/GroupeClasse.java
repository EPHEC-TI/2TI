package tp2;

import javax.swing.JOptionPane;

/**
 * @author HE201464
 *
 */
public class GroupeClasse {
  private Prof titulaire;
  private Etu [] listeEtu;
  
  public GroupeClasse(Prof titulaire, Etu [] listeEtu) {
	  this.titulaire = titulaire;
	  this.listeEtu = listeEtu;
  }
  
  public GroupeClasse() {
	  this.titulaire = new Prof();
	  this.listeEtu = new Etu[Integer.parseInt(JOptionPane.showInputDialog("Entrez le nombre d'étudiants"))];
  }
  
  public GroupeClasse(String nom, String prenom, String specialite, Etu [] listeEtu) {
	  this.titulaire = new Prof(nom, prenom, specialite);
	  this.listeEtu = listeEtu;
  }
  
  public Prof getTitulaire() {
	return titulaire;
  }

  public void setTitulaire(Prof titulaire) {
	this.titulaire = titulaire;
  }

  public Etu[] getListeEtu() {
	return listeEtu;
  }

  public void setListeEtu(Etu[] listeEtu) {
	this.listeEtu = listeEtu;
  }

  public void remplirTableau() {
	// A nouveau, "this" pas nécessaire. Présent par soucis de lisibilité.
	for(int i = 0; i < this.listeEtu.length; i++) {
		listeEtu[i] = new Etu("etuNom"+(i+1), "etuPrenom"+(i+1), (i+1));
	}
  }
  
  public String toString() {
	String temp = "";
	for(int i = 0; i < this.listeEtu.length; i++){
		temp += listeEtu[i] + "\n";
	}
	return ("Titulaire: "+this.titulaire+"\nListe des étudiants: \n"+temp);
  }
}
