package tp4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EtuTest {
	Etu marc, paul, clonePaul;
	@Before
	public void setUp() {
		marc = new Etu("NomMarc", "Marc");
		paul = new Etu("NomPaul", "Paul");
		clonePaul = new Etu("NomPaul", "Paul");
	}
	@Test
	public void testMoyenne() {
		marc.setResultats(new int[] {10,10,10,10,10});
		assertEquals(10.0, marc.moyenne(), 0.0);
		marc.setResultats(new int[] {20,20,20,20,20});
		assertEquals(20.0, marc.moyenne(), 0.0);
		marc.setResultats(new int[] {0,0,0,0,0});
		assertEquals(0.0, marc.moyenne(), 0.0);
		marc.setResultats(new int[] {0,20,20,10,5});
		assertEquals(11.0, marc.moyenne(), 0.0);
	}

	@Test
	public void testEqualsObject() {
		assertFalse(marc.equals(null));
		assertFalse(marc.equals(paul));
		assertTrue(marc.equals(marc));
		assertTrue(paul.equals(clonePaul));
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(paul.compareTo(paul) == 0);
		assertTrue(paul.compareTo(marc) > 0);
		assertTrue(marc.compareTo(paul) < 0);
	}
}
