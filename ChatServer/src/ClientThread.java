import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{

	Socket socket;
	PrintWriter out;
	BufferedReader in;
	String name;
	
	public ClientThread(Socket s) throws IOException{
		socket = s;
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		try {
		name = in.readLine();
		//System.out.println(name + " joined!");
		Main.printMessage(name + " joined!", name);
		out.println("There are " + (Main.clients.size()-1) + " other users online!");
		while(true){
				if(!socket.isConnected()) break;
				if(in.ready()){
					String input = in.readLine();
					if(input.startsWith("QUIT"))break;
					if(!input.isEmpty()) Main.printMessage(name + ": " + input, name);
				}
		}
		socket.close();
		Main.clients.remove(this);
		System.out.println(name + " DISCONNECTED!");
		}catch(Exception e){e.printStackTrace();}	
	}
}
