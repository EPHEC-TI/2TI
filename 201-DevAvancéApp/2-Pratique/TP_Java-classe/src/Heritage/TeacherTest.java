package Heritage;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class TeacherTest {
	Teacher teacher, teacher2;

	@Before
	public void setUp() {
		teacher = new Teacher("Dewulf", "Arnaud", LocalDate.of(1980, 6, 23), LocalDate.of(2011, 3, 8));
		teacher2 = new Teacher("Delvigne", "Yves", LocalDate.of(1943, 11, 13), LocalDate.of(1963, 9, 1));
	}

	@Test
	public void seniorityTest() {
		assertEquals(7, teacher.seniority());
		assertEquals(51, teacher2.seniority());
	}

}
