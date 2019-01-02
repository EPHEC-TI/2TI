package tp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonneTest {

	@Test
	public void testToString() {
		Personne p = new Personne("Dewulf", "Arnaud", 1234);
		assertEquals("Nom: Dewulf\nPrénom: Arnaud\nRN: 1234", p.toString());
	}

	@Test
	public void testEquals() {
		Personne p1 = new Personne("Dupont", "Jules", 1234);
		Personne p2 = new Personne("Dupont", "Jules", 1234);
		Personne p3 = new Personne("Dupont", "Marc", 1234);
		Personne p4 = new Personne("Dupond", "Jules", 1234);
		Personne p5 = new Personne("Dupont", "Jules", 4321);
		
		assertTrue(p1.equals(p1));
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(null));
		assertFalse(p1.equals("Bonjour"));
		assertTrue(p1.equals(p3));
		assertTrue(p1.equals(p4));
		assertFalse(p1.equals(p5));
	}
}
