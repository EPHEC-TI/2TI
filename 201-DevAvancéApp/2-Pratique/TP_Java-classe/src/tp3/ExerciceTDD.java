package tp3;

public class ExerciceTDD {
	/**
	 * This method checks if the argument number is even.
	 * @param n a number.
	 * @return true is n is even, fals if n is odd.
	 */
	public boolean estPair(int n) {
		return (n%2 == 0);
	}
	/**
	 * This method computes the perimeter of a square.
	 * @param n the length of one side of the square.
	 * @return the parameter of the square.
	 */
	public double perimCarre(double n) {
		return (4*n);
	}
	/**
	 * This method checks if a character is inside a String.
	 * @param a the character to find in the String.
	 * @param s a non null string which might contain the character.
	 * @return true if a is found in s, false otherwise.
	 */
	public boolean contientCar(char a, String s) {
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == a) return true;
		}
		return false;
	}
}
