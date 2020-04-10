

import java.io.*;
import java.net.*;
import business.*;
import javax.swing.table.DefaultTableModel;

public class UserHandler implements Runnable{
    private Socket user;
    private BufferedReader in;
    private PrintWriter out;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private UserDataStructure userDataStructure;
    
    public UserHandler(Socket clientSocket) throws IOException{
        this.user = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(user.getInputStream()));
        this.outputStream = user.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.out = new PrintWriter(outputStream, true);
    }
    
    @Override
    public void run() {
        try{
            while(true){
                String request = in.readLine();
                String[] segments = request.split(" ");
                
                if(segments[0].equals("LogIn")){
                    userDataStructure = new UserDataStructure(segments[1], segments[2]);
                    
                    new LogInRequest().userRequest(userDataStructure);
                    objectOutputStream.writeObject(userDataStructure);
                    System.out.println("Data Sent!");
                }else if(segments[0].equals("SignUp")){
                    UserDataStructure uSD = new UserDataStructure(segments[1], segments[2], segments[3], segments[4], segments[5]);
                    
                    out.println(new SignUpRequest().userRequest(uSD));
                    System.out.println("Data Sent!");
                }else if(segments[0].equals("Retrieve")){
                    DefaultTableModel defaultTableModel = (DefaultTableModel) new GroceryListWork().populateRequest(userDataStructure, Integer.parseInt(segments[1]));
                    
                    objectOutputStream.writeObject(defaultTableModel);
                    System.out.println("Data Sent!");
                }else if(segments[0].equals("Search")){
                    out.println(new GroceryListWork().requestItemSearch(segments[2], Integer.parseInt(segments[1]), userDataStructure));
                    System.out.println("Data Sent!");
                }else if(segments[0].equals("Modify")){
                   // ItemInformation itemInformation = new ItemInformation(Long.parseLong(segments[1]), segments[2], Integer.parseInt(segments[3]), 
                   //         Integer.parseInt(segments[4]), segments[5], segments[6], segments[7], Integer.parseInt(segments[8]));
                   // out.println(new GroceryListWork().requestItemModification(itemInformation, userDataStructure));
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
