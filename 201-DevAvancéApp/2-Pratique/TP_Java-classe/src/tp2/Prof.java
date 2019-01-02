package tp2;

import javax.swing.JOptionPane;
/**
 * This class represents a teacher.
 * @author HE201464
 *
 */
public class Prof {
  /**
   * The name of the teacher
   */
  private String nom;
  /**
   * The forename of the teacher
   */
  private String prenom;
  /**
   * The specialty of the teacher
   */
  private String specialite;
  
  /**
   * This constructor allows the user to create a teacher from scratch.
   */
  public Prof() {
	  this.nom = JOptionPane.showInputDialog("Entrez le nom");
	  this.prenom = JOptionPane.showInputDialog("Entrez le prénom");
	  this.specialite = JOptionPane.showInputDialog("Entrez la spécialité");
  }
  
  /**
   * This constructor allows the user to create a teacher knowing his :
   * name, forename and specialty.
   * @param nom the name of the teacher
   * @param prenom the forename of the teacher
   * @param specialite the specialty of the teacher
   */
  public Prof(String nom, String prenom, String specialite) {
	  this.nom = nom;
	  this.prenom = prenom;
	  this.specialite = specialite;
  }
  
  /**
   * @return the name of the teacher
   */
  public String getNom() {
	return nom;
  }

	/**
	 * This getter allows the user to set or change the name of the teacher.
	 * @param nom the new name of the teacher
	 */
  public void setNom(String nom) {
	this.nom = nom;
  }

  /**
   * @return the forename of the teacher
   */
  public String getPrenom() {
	return prenom;
  }

	/**
	 * This getter allows the user to set or change the forename of the teacher.
	 * @param prenom the new forename of the teacher
	 */
  public void setPrenom(String prenom) {
	this.prenom = prenom;
  }

  /**
   * @return the specialty of the teacher
   */
  public String getSpecialite() {
	return specialite;
  }

	/**
	 * This getter allows the user to set/change the specialty of the teacher.
	 * @param specialite the new specialty of the teacher
	 */
  public void setSpecialite(String specialite) {
	this.specialite = specialite;
  }

  /**
   * This method gives a textual representation of the teacher.
   */
  public String toString() {
	  return ("Prénom: "+this.prenom+"\nNom: "+this.nom+"\nSpécialité: "+this.specialite);
  }
}
