package Heritage;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {
	Student student, student2, student3, student4;

	@Before
	public void setUp() {
		student = new Student("Shell", "Jean-Mi", LocalDate.of(1998, 6, 23), LocalDate.of(2017, 8, 26));
		student2 = new Student("Campos Casares", "Melvin", LocalDate.of(1995, 11, 11), LocalDate.of(2016, 8, 16));
		student3 = new Student("Lewinston Coon's", "D'Kazan", LocalDate.of(2008, 5, 31), LocalDate.of(2018, 9, 1));
		student4 = new Student("Damoiseaux", "Victoria", LocalDate.of(1994, 6, 22), LocalDate.of(2118, 12, 23));
	}

	@Test
	public void registrationNumberTest() {
		assertEquals("2017-SJ-1", student.registrationNumber());
		assertEquals("2016-CM-2", student2.registrationNumber());
		assertEquals("2016-CM-2", student2.registrationNumber());
		assertEquals("2017-SJ-1", student.registrationNumber());
		assertEquals("2018-LD-3", student3.registrationNumber());
		assertEquals("2118-DV-4", student4.registrationNumber());
	}

	@Test
	public void seniorityTest() {
		assertEquals(1, student.seniority());
		assertEquals(2, student2.seniority());
		assertEquals(0, student3.seniority());
		assertEquals(-100, student4.seniority());
	}
}
