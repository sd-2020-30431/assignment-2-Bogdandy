package user;

import java.net.*;
import java.io.*;

public class User {
    private static final String serverIP ="127.0.0.1";
    private static final int serverPort = 9898;
    
    
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket(serverIP, serverPort);
        
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader commandRequest = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        while(true){
            System.out.println("> ");
            String command = commandRequest.readLine();
            
            out.println(command);
        
            if(command.equals("quit"))
                break;
            
            String serverResponse = input.readLine();
            System.out.println("Server sends: " + serverResponse);
        }
        
        socket.close();
        System.exit(0);
    }
}
