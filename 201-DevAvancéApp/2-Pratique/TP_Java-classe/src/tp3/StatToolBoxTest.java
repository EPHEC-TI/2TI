package tp3;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatToolBoxTest {

	@Test
	public void testMoyenne() {
		int [] values = {1,1,1,2,2,2};
		StatToolBox stb = new StatToolBox(values);
		assertEquals(1.5,stb.moyenne(),0.0);
	}

}
