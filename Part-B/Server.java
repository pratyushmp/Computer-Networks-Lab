package practice;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String args[]) throws Exception
	{
		ServerSocket sersock = new ServerSocket(4000);
		System.out.println("Server ready for connection");
		
		Socket sock = sersock.accept();
		System.out.println("Connection successful and waiting for chatting");
		
		InputStream istream = sock.getInputStream();
		BufferedReader fileread = new BufferedReader(new InputStreamReader(istream));
		
		String fname = fileread.readLine();
		BufferedReader contentRead = new BufferedReader(new FileReader(fname));
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream,true);
		
		String str;
		
		while((str = contentRead.readLine()) != null)
			pwrite.println(str);
		
		sock.close();
		sersock.close();
		pwrite.close();
		fileread.close();
		contentRead.close();
	}
}
