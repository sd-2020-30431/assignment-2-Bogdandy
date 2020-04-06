package server;

import client.ClientWIP;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    private ServerSocket serverSocket;

    public void start(int port)  throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }

    public void stop()  throws IOException{
        serverSocket.close();
    }

    private class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(ClientWIP.class.getName()).log(Level.SEVERE, null, ex);
            }

            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientWIP.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientWIP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

