package tp9;

import java.io.IOException;
import java.util.Scanner;

//* : all modifications are indicated with a star.

public class ChatConsole {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		boolean isServer = (args[0].equals("true")? true : false);
		Chat chat = new Chat( isServer , 12345);
		if(isServer){
			String msg = chat.waitForMessage();
			System.out.println(msg);
		}
		while(true){
			System.out.print(">");
			String msg = sc.nextLine();
			if(msg.equals("STOP")){ // * transformation de msg=="STOP" avec equals
				chat.sendMessage("STOP");
				sc.close();
				chat.closeConnection(); // *
				System.exit(0);
			}
			chat.sendMessage(msg);
			msg = chat.waitForMessage();
			System.out.println(msg);
			
		}
		
	}

}
