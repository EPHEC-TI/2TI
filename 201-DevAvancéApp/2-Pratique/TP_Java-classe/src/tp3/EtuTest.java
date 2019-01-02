package tp3;

import static org.junit.Assert.*;
import org.junit.Test;

public class EtuTest {

	@Test
	public void testEtu() {
		Etu etu = new Etu();
		assertNotNull("Les variables d'instance de type de String ne sont pas initialisées", etu.getNom());
		assertNotNull("Les variables d'instance de type de String ne sont pas initialisées", etu.getPrenom());
		assertNotNull("Les variables d'instance de type de String ne sont pas initialisées", etu.getMatricule());
	}
	
	@Test
	public void testMoyenne() {
		Etu toto = new Etu("Dewulf", "Arnaud", "HE123456", new Date(1,1,2001), new int [] {1,2,3,4,5});
		
		assertEquals(3.0, toto.moyenne(), 0.0);
		
		toto = new Etu("Dewulf", "Arnaud", "HE123456", new Date(1,1,2001), new int [] {1,1,1,1,1});
		assertEquals(1.0, toto.moyenne(), 0.0);
	}
}
