package tp1;

/**
 * This class is used to test the Etu
 * @author HE201464
 */
public class testEtu {
	public static void main(String[] args){
		Etu etu1 = new Etu("Campos", "Melvin", 201464);
		Etu etu2 = new Etu("Van De Walle", "Hubert", 201496);
		Etu etu3 = new Etu(); //Dewulf, Arnaud, 1234
		
		etu1.setNom("Campos Casares");
		//System.out.println(etu1.getNom());
		
		System.out.println(etu1);
		System.out.println(etu2);
		System.out.println(etu3);
	}
}
