

import java.io.*;
import java.net.*;
import business.*;

public class UserHandler implements Runnable{
    private Socket user;
    private BufferedReader in;
    private PrintWriter out;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    
    public UserHandler(Socket clientSocket) throws IOException{
        this.user = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(user.getInputStream()));
        this.outputStream = user.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.out = new PrintWriter(user.getOutputStream(), true);
    }
    
    @Override
    public void run() {
        try{
            while(true){
                String request = in.readLine();
                String[] segments = request.split(" ");
                
                if(request.contains("LogIn")){
                    UserDataStructure uSD = new UserDataStructure(segments[1], segments[2]);
                    
                    new LogInRequest().userRequest(uSD);
                    objectOutputStream.writeObject(uSD);
                    System.out.println("Data Sent!");
                }else  if(request.contains("SignUp")){
                    UserDataStructure uSD = new UserDataStructure(segments[1], segments[2], segments[3], segments[4], segments[5]);
                    
                    out.println(new SignUpRequest().userRequest(uSD));
                    System.out.println("Data Sent!");
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
            System.err.println("A user has disconnected from the server");
            ServerMain.disconnectUser(this);
            Thread.currentThread().interrupt();
        }
    }
    
}
