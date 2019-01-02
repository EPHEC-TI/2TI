package Heritage;

import java.time.LocalDate;

/**
 * This class represents a student.
 * 
 * @author Melvin Campos Casares (HE201464)
 *
 */
public class Student extends Person {
	private static int count = 1;
	private int orderNumber = count++;

	/**
	 * @param name the name of the student.
	 * @param firstname the firstname of the student.
	 * @param birthDate the date of birth of the student.
	 * @param arrivalDate the arrival date of the student.
	 */
	public Student(String name, String firstname, LocalDate birthDate, LocalDate arrivalDate) {
		super(name, firstname, birthDate, arrivalDate);
	}

	/**
	 * This method creates a registration number based on the year of arrival,
	 * the first letter of the name and the firstname of the student.
	 * @return the registration number of the student.
	 */
	public String registrationNumber() {
		return getArrivalDate().getYear() + "-" + getName().charAt(0) + getFirstname().charAt(0) + "-" + orderNumber;
	}

	/**
	 * This method returns the number of year since when the student registrates at
	 * school.
	 */
	@Override
	public int seniority() {
		return super.getArrivalDate().until(LocalDate.now()).getYears();
	}
}
