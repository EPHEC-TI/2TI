package tp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeTest {

	@Test
	public void test() {
		Employe e = new Employe("Dewulf", "Arnaud", 1234, 1000, "Ephec");
		assertEquals("Nom: Dewulf\nPrénom: Arnaud\nRN: 1234\nSalaire: 1000\nEmployeur: Ephec", e.toString());
	}
	

}
