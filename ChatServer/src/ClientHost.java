import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHost implements Runnable{
	
	protected boolean kill = false;
	
	 public void run(){
		 try{
		  Socket client = new Socket("83.84.180.129", 12321);

		  BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		  PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		  
		  Scanner scanner = new Scanner(System.in);
		  
		  
		  System.out.print("Name: ");
		  String name = scanner.nextLine();
		  
		  out.println(name);
		  
		  new Thread( new Runnable() {
		   public void run(){ while(true){try{
			   if(kill) break;
		       if(in.ready()){
		    	   System.out.println(in.readLine());
		       }
		     }catch(Exception e){e.printStackTrace();}
		    }}}).start();
		  
		  
		  String input;
		  while(true) {
		   if(scanner.hasNextLine()) {
		    input = scanner.nextLine();
		    out.println(input);
		    if(input.equals("QUIT")) break;
		   
		   }
		  }
		  scanner.close();
		  client.close();
		  kill = true;
		 } catch (Exception e){
			 e.printStackTrace();
		 }
	 }
}
