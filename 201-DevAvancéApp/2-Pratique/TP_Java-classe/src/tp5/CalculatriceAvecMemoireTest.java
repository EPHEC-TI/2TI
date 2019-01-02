package tp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatriceAvecMemoireTest {

	@Test
	public void testSaveAndReload() {
		CalculatriceAvecMemoire calc = new CalculatriceAvecMemoire();
		calc.setValeurCourante(5);
		assertEquals(5.0, calc.getValeurCourante(), 0.0);
		calc.save();
		calc.setValeurCourante(10);
		assertEquals(10.0, calc.getValeurCourante(), 0.0);
		calc.load();
		assertEquals(5.0, calc.getValeurCourante(), 0.0);
	}

}
