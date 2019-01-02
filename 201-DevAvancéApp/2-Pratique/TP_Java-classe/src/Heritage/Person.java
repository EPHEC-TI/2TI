package Heritage;

import java.time.LocalDate;

/**
 * This abstract class is the parent of Teacher and Student classes.
 * 
 * @author Melvin Campos Casares (HE201464)
 *
 */
public abstract class Person {
	protected String name;
	protected String firstname;
	protected LocalDate birthDate;
	protected LocalDate arrivalDate;

	/**
	 * @param name        the name of the student or the teacher.
	 * @param firstname   the firstname of the student or the teacher.
	 * @param birthDate   the date of birth of the student or the teacher.
	 * @param arrivalDate the date of arrival of the student or the teacher.
	 */
	public Person(String name, String firstname, LocalDate birthDate, LocalDate arrivalDate) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.birthDate = birthDate;
		this.arrivalDate = arrivalDate;
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the firstname.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the date of birth.
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @return the arrival date.
	 */
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * Abstract method used by teacher and student child class.
	 * 
	 * @return nothing. The children returns a number of year based on specs.
	 */
	public abstract int seniority();

}
