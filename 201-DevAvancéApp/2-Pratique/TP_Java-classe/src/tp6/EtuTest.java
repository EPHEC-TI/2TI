package tp6;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class EtuTest {
	Etu etu;
	LocalDate dN;
	LocalDate dA;

	@Before
	public void setUp() throws DateArriveeInvalideException {
		etu = new Etu();
		etu.setNom("Dewulf");
		etu.setPrenom("Arnaud");
		dN = LocalDate.of(2005, 1, 1);
		dA = LocalDate.of(2015, 1, 1);
		etu.setDateArrivee(dA);
		etu.setDateNaissance(dN);
	}
	
	@Test
	public void testMatricule() {
		assertEquals("DA2015", etu.matricule());
	}
	
	@Test
	public void testAge() {
		assertEquals(dN.until(LocalDate.now()).getYears(), etu.age());
	}
	
	@Test(expected=DateArriveeInvalideException.class)
	public void testException() throws DateArriveeInvalideException{
		etu.setDateArrivee(LocalDate.of(1990, 1, 1));
	}

}
