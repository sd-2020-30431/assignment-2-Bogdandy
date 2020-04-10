package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServerMain {
    private static final int port = 9898;
    
    private static final ArrayList<UserHandler> users = new ArrayList<>();
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);
    
    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(port);
        
        while(true){
            System.out.println("[Server] Waiting for user to establish connection...");
            Socket client = listener.accept();
            System.out.println("[Server] User connected!");
            
            UserHandler userThread = new UserHandler(client);
            users.add(userThread);
            
            pool.execute(userThread);
        }
    }
    
    public static void disconnectUser(UserHandler user){
        users.remove(user);
    }
}

