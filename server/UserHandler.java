package server;

import java.io.*;
import java.net.*;
import business.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class UserHandler implements Runnable{
    private Socket user;
    private BufferedReader in;
    private PrintWriter out;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private UserDataStructure userDataStructure;
    
    public UserHandler(Socket clientSocket) throws IOException{
        this.user = clientSocket;
        this.outputStream = user.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.inputStream = user.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);
        this.out = new PrintWriter(outputStream, true);
        this.in = new BufferedReader(new InputStreamReader(inputStream));
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
                    objectOutputStream.flush();
                }else if(segments[0].equals("SignUp")){
                    UserDataStructure uSD = new UserDataStructure(segments[1], segments[2], segments[3], segments[4], segments[5]);
                    
                    out.println(new SignUpRequest().userRequest(uSD));
                }else if(segments[0].equals("Retrieve")){
                    DefaultTableModel defaultTableModel = (DefaultTableModel) new GroceryListWork().populateRequest(userDataStructure, Integer.parseInt(segments[1]));
                    
                    objectOutputStream.writeObject(defaultTableModel);
                    objectOutputStream.flush();
                }else if(segments[0].equals("Search")){
                    out.println(new GroceryListWork().requestItemSearch(segments[2], Integer.parseInt(segments[1]), userDataStructure));
                }else if(segments[0].equals("Modify")){
                    ItemInformation itemInformation = (ItemInformation)objectInputStream.readObject();
                    
                    out.println(new GroceryListWork().requestItemModification(itemInformation, userDataStructure));
                }else if(segments[0].equals("AddItem")){
                    ItemInformation itemInformation = (ItemInformation)objectInputStream.readObject();
                    
                    out.println(new GroceryListWork().requestItemInsertion(itemInformation, userDataStructure));
                }else if(segments[0].equals("ClearList")){
                    out.println(new GroceryListWork().requestListClear(Integer.parseInt(segments[1]), userDataStructure));
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
            System.err.println("[Server] A user has disconnected from the server");
            ServerMain.disconnectUser(this);
            Thread.currentThread().interrupt();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
