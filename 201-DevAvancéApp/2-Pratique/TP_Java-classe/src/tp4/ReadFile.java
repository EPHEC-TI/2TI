package tp4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(new File("test.txt"));
			while(input.hasNextLine()) {
				System.out.println(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
