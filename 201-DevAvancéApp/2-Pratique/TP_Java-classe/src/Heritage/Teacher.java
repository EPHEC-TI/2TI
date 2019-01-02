package Heritage;

import java.time.LocalDate;

/**
 * This class represents a teacher.
 * 
 * @author Melvin Campos Casares (HE201464)
 *
 */
public class Teacher extends Person {

	/**
	 * @param name        the name of the teacher.
	 * @param firstname   the firstname of the teacher.
	 * @param birthDate   the date of birth of the teacher.
	 * @param arrivalDate the arrival date of the teacher.
	 */
	public Teacher(String name, String firstname, LocalDate birthDate, LocalDate arrivalDate) {
		super(name, firstname, birthDate, arrivalDate);
	}

	/**
	 * This method returns the number of year since when the teacher started to
	 * teach at school. If the teacher arrived when he was younger than 23, we don't
	 * take into account his first years as teacher until he is 23.
	 */
	@Override
	public int seniority() {
		LocalDate dateAtTwentyThree = birthDate.plusYears(23);
		boolean isBeforeArrivalDate = dateAtTwentyThree.isBefore(arrivalDate);

		// getYear from LocalDate for better accuracy ?
		int jeanMi = isBeforeArrivalDate ? arrivalDate.until(LocalDate.now()).getYears()
				: dateAtTwentyThree.until(LocalDate.now()).getYears();
		return jeanMi; // Easter Egg variable name, whatever.. d-: ();
	}
}
