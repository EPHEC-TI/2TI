package tp4;

import javax.swing.JOptionPane;
/**
 * 
 * @author HE201464
 * This class represents a student with an ID and some results obtained during different tests.
 */
public class Etu {
	private String nom;
	private String prenom;
	private int matricule;
	private static int nbEtu = 0;
	private int [] resultats;
	/**
	 * This constructor creates a student from scratch.
	 */
	public Etu() {
		this.nom = JOptionPane.showInputDialog("Entre le nom de l'étudiant");
		this.prenom = JOptionPane.showInputDialog("Entre le prénom de l'étudiant");
		//this.matricule = Integer.parseInt(JOptionPane.showInputDialog("Entre le numéro ID de l'étudiant"));
		nbEtu++;
		this.matricule = nbEtu;
	}
	/**
	 * This constructor creates a student using the arguments name, forename and ID.
	 * @param nom
	 * @param prenom
	 * @param matricule
	 */
	public Etu(String nom, String prenom/*, int matricule*/) {
		this.nom = nom;
		this.prenom = prenom;
		//this.matricule = matricule;
		nbEtu++;
		this.matricule = nbEtu;
	}
	/**
	 * @return the results of the student
     */
	public int[] getResultats() {
		return resultats;
	}
	/**
	 * This getter allows the user to set or change the results of the student.
     * @param matricule the results of the student
     */
	public void setResultats(int[] resultats) {
		this.resultats = resultats;
	}
	
	//getters setters
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
	/**
	 * This method make an average on the results of the student.
	 * @return the average of the student.
	 */
	public double moyenne() {
		if(this.resultats == null) return 0;
		double somme = 0;
		for(int i:resultats) {
			somme += i;
		}
		return somme/resultats.length;
	}
    /**
     * This method generate a textual representation of the student.
     */
	public String toString() {
		return("Nom: "+this.nom+"\nPrénom: "+this.prenom+"\nMatricule: "+this.matricule);
	}
	/**
	 * This method checks the equality between 2 students.
	 * @equals true if both students have the same ID, false otherwise.
	 */
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		Etu other = (Etu)obj;
		if(this.matricule != other.matricule) return false;
		return true;
	}
	/**
	 * Compare the current student with the one passed in parameter
	 * @param etu the student to make the comparison with
	 * @return a positive int if the current student is greater then the parameter,
	 *  0 if the object is the same as the argument, a negative int otherwise.
	 */
	public int compareTo(Etu etu) {
		if(this.equals(etu)) return 0;
		return this.matricule < etu.matricule ? -1 : 1;
	}
	
	public static int getNbEtu() {
		return nbEtu;
	}
}
