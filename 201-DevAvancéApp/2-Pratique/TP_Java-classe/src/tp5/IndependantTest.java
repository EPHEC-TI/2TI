package tp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndependantTest {

	@Test
	public void testToString() {
		Independant i = new Independant("Dewulf", "Arnaud", 1234, "BE123456");
		assertEquals("Nom: Dewulf\nPrénom: Arnaud\nRN: 1234\nTVA: BE123456", i.toString());
	}

}
