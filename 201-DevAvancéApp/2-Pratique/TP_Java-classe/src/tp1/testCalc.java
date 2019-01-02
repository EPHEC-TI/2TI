package tp1;

/**
 * This class is used to test the calculator
 * @author HE201464
 */
public class testCalc {

	/**
	 * @param args represents the arguments passed to the main method
	 */
	public static void main(String[] args) {
		//Calculatrice() est une méthode portant le même nom que la classe qu'on souhaite utiliser.
		//Il s'agit d'un constructeur.
		Calculatrice maCalc = new Calculatrice();
		
		System.out.println(maCalc.valeurCourante);
		maCalc.ajoute(5);
		System.out.println(maCalc.valeurCourante);
		maCalc.soustrait(2);
		System.out.println(maCalc.valeurCourante);
	}
}