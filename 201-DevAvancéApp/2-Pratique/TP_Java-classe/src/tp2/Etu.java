package tp2;

import javax.swing.JOptionPane;

public class Etu {
  /**
   * The name of the student
   */
  private String nom;
  /**
   * The forename of the student
   */
  private String prenom;
  /**
   * The ID of the student
   */
  private int matricule;
  
  /**
   * This constructor allows the user to create a student from scratch.
   */
  public Etu() {
    this.nom = JOptionPane.showInputDialog("Entrez le nom");
    this.prenom = JOptionPane.showInputDialog("Entrez le prénom");
    this.matricule = Integer.parseInt(JOptionPane.showInputDialog("Entrez le matricule"));
  }
  
  /**
   * This constructor creates a student using the name, forename and ID passed.
   * @param nom the name of the student
   * @param prenom the forename of the student
   * @param matricule the id of the student
   */
  public Etu(String nom, String prenom, int matricule) {
    this.nom = nom;
    this.prenom = prenom;
    this.matricule = matricule;
  }
  
  /**
   * This constructor creates a student and ask the user the values for the student.
   */
  public Etu (String nom, String prenom, String matricule) {
    this.nom = nom;
    this.prenom = prenom;
    this.matricule = Integer.parseInt(matricule);
  }
  
  /**
   * This constructor creates a student with only the name and forename.
   * The ID has to be set by the user.
   * @param nom the name
   * @param prenom the forename
   */
  public Etu(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
    this.matricule = Integer.parseInt(JOptionPane.showInputDialog("Entrez le matricule"));
  }
  
  /**
   * This getter allows the user to set or change the name of the student.
   * @param nom the new name of the student
   */
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  /**
   * @return the name of the student
   */
  public String getNom() {
	//this pas du tout obligatoire dans ce get
	//car aucune autre instance nom présent dedans.
    return this.nom;
  }

  /**
   * @return the forename of the student
   */
  public String getPrenom() {
	return prenom;
  }

  /**
   * This getter allows the user to set or change the forename of the student.
   * @param prenom the forename of the student
   */
  public void setPrenom(String prenom) {
	this.prenom = prenom;
  }
  
  /**
   * @return the ID of the student
   */
  public int getMatricule() {
	return matricule;
  }
  
  /**
   * This getter allows the user to set or change the ID of the student.
   * @param matricule the ID of the student
   */
  public void setMatricule(int matricule) {
	this.matricule = matricule;
  }
}