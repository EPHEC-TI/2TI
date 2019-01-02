package tp9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	public static void main(String[] args) {
		ServerSocket s;
		Socket soc;
		BufferedReader in;
		PrintWriter out;
		
		try {
			s = new ServerSocket(8080);
			soc = s.accept();
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);
			
			while(true) {
				String str = in.readLine();
				if("END".equals(str)) break;
				System.out.println("Echo: "+str);
			}
			in.close();
			out.close();
			soc.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
