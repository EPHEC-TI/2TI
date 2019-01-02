package tp3;

public class MainEtu {
	public static void main(String[] args) {
		int [] results = {5,5,5,5,7};
		Etu toto = new Etu("Dewulf", "Arnaud", "HE123456", new Date(1,1,2001), results);
		System.out.println(toto);
		System.out.println("Moyenne: "+toto.moyenne());
	}
}
