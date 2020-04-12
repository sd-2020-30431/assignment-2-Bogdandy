package server.business;

import java.io.*;
import static java.lang.System.out;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class UserHandler implements Runnable{
    private Socket user;
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
    }
    
    @Override
    public void run() {
        try{
            while(true){
                String request = (String)objectInputStream.readObject();
                String[] segments = request.split(" ");
                
                if(segments[0].equals("LogIn")){
                    userDataStructure = new UserDataStructure(segments[1], segments[2]);
                    
                    objectOutputStream.writeObject(new LogInRequest().userRequest(userDataStructure));
                    objectOutputStream.flush();
                }else if(segments[0].equals("SignUp")){
                    UserDataStructure uSD = new UserDataStructure(segments[1], segments[2], segments[3], segments[4], segments[5]);
                    
                    objectOutputStream.writeObject(new SignUpRequest().userRequest(uSD));
                    objectOutputStream.flush();
                }else if(segments[0].equals("Retrieve")){
                    DefaultTableModel defaultTableModel = (DefaultTableModel) new GroceryListWork().populateRequest(userDataStructure, Integer.parseInt(segments[1]));
                    
                    objectOutputStream.writeObject(defaultTableModel);
                    objectOutputStream.flush();
                }else if(segments[0].equals("Search")){
                    objectOutputStream.writeObject(new GroceryListWork().requestItemSearch(segments[2], Integer.parseInt(segments[1]), userDataStructure));
                    objectOutputStream.flush();
                }else if(segments[0].equals("Modify")){
                    ItemInformation itemInformation = new ItemInformation(Long.parseLong(segments[1]), 
                            segments[2], Integer.parseInt(segments[3]), Integer.parseInt(segments[4]),
                            (Date)objectInputStream.readObject(), (Date)objectInputStream.readObject(), 
                            (Date)objectInputStream.readObject(), Integer.parseInt(segments[5]));
                    
                    objectOutputStream.writeObject(new GroceryListWork().requestItemModification(itemInformation, userDataStructure));
                    objectOutputStream.flush();
                }else if(segments[0].equals("AddItem")){
                    ItemInformation itemInformation = new ItemInformation(Long.parseLong(segments[1]), 
                            segments[2], Integer.parseInt(segments[3]), Integer.parseInt(segments[4]),
                            (Date)objectInputStream.readObject(), (Date)objectInputStream.readObject(), 
                            (Date)objectInputStream.readObject(), Integer.parseInt(segments[5]));
                    
                    objectOutputStream.writeObject(new GroceryListWork().requestItemInsertion(itemInformation, userDataStructure));
                    objectOutputStream.flush();
                }else if(segments[0].equals("RemoveItem")){
                    ItemInformation itemInformation = new ItemInformation(Long.parseLong(segments[1]), 
                            segments[2], Integer.parseInt(segments[3]), Integer.parseInt(segments[4]),
                            (Date)objectInputStream.readObject(), (Date)objectInputStream.readObject(), 
                            (Date)objectInputStream.readObject(), Integer.parseInt(segments[5]));
                    
                    objectOutputStream.writeObject(new GroceryListWork().requestItemRemoval(itemInformation, userDataStructure));
                    objectOutputStream.flush();
                }else if(segments[0].equals("ClearList")){
                    objectOutputStream.writeObject(new GroceryListWork().requestListClear(Integer.parseInt(segments[1]), userDataStructure));
                    objectOutputStream.flush();
                }else if(segments[0].equals("Report")){
                    List<ItemInformation> groceryList = new GroceryListWork().requestGroceryListData(userDataStructure);
                    AbstractFactory abstractFactory;
                    abstractFactory = ReportProvider.getFactory(segments[1]);
                    abstractFactory.create(segments[1], groceryList);
                }else if(request.contains("quit")){
                    objectOutputStream.close();
                    objectInputStream.close();
                    ServerMain.disconnectUser(this);
                    Thread.currentThread().interrupt();
                    break;
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
