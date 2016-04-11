import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
	
	public static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public static boolean running = true;
	
	
	public static void main(String[] args){
		new Thread(new ClientHost()).start();
		
		try{
			ServerSocket ss = new ServerSocket(12321);
			while(running){
				ClientThread ct = new ClientThread(ss.accept());
				new Thread(ct).start();
				clients.add(ct);
				//System.out.println(ct.socket.toString() + " CONNECTED");
			}
			ss.close();
		} catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}


	public static void printMessage(String string, String name) {
		for(ClientThread ct : clients){
			if(ct.name != name)ct.out.println(string);
		}
	}
	
}
