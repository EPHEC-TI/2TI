package tp1;

/**
 * A little calculator made in JAVA which uses parts of Math library
 * @author HE201464
 */
public class Calculatrice {
	double valeurCourante = 0.0;
	
	/**
	 * Method which calculates a value plus an other
	 * @param n represents the arguments passed to the main method
	 */
	public void ajoute(double n){
		valeurCourante += n;
	}
	
	/**
	 * Method which calculates a value minus an other
	 * @param n represents the arguments passed to the main method
	 */
	public void soustrait(double n){
		valeurCourante -= n;
	}
	
	/**
	 * Method which calculates the power of 2 of a value
	 */
	public void carre(){
		valeurCourante = Math.pow(valeurCourante, 2);
	}
}