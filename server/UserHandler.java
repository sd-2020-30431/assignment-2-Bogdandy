package server;

import java.io.*;
import java.net.*;

public class UserHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    
    public UserHandler(Socket clientSocket) throws IOException{
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }
    
    @Override
    public void run() {
        try{
            while(true){
                String request = in.readLine();
                
                if(request.contains("date")){
                    out.println(ServerMain.getDate());
                }else if(request.contains("quit")){
                    out.close();
                    in.close();
                    ServerMain.disconnectUser(this);
                    Thread.currentThread().interrupt();
                    break;
                }else{
                    out.println("Not a valid command!");
                }
            }
        }catch(IOException ex){
            System.err.println("IO exception in User Handler.");
            ex.printStackTrace();
        }
    }
    
}
