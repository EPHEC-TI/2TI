package tp3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExerciceTDDTest {
	ExerciceTDD obj;
	
	@Before
	public void setUp() {
		obj = new ExerciceTDD();
	}
	
	@Test
	public void testEstPair() {
		assertEquals(true, obj.estPair(0)); //méthode 1
		assertTrue(obj.estPair(0)); //méthode 2
		assertFalse(obj.estPair(15));
		assertTrue(obj.estPair(272));
	}
	
	@Test
	public void testPerimCarre() {
		assertEquals(4.0, obj.perimCarre(1), 0.0);
		assertEquals(8.0, obj.perimCarre(2.0), 0.0);
		assertEquals(10.0, obj.perimCarre(2.5), 0.0);
		assertEquals(4.8, obj.perimCarre(1.2), 0.0);
	}
	
	@Test
	public void testContientCar() {
		assertFalse(obj.contientCar('a', "bonjour"));
		assertTrue(obj.contientCar('a', "au revoir"));
		assertTrue(obj.contientCar('a', "abracadabra"));
		assertFalse(obj.contientCar('a', ""));
		assertTrue(obj.contientCar(' ', " "));
		assertTrue(obj.contientCar(' ', "au revoir"));
		assertTrue(obj.contientCar('\n', "au\nrevoir"));
	}
}
