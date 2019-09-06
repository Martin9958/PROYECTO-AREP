package edu.escuelaing.arem.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import edu.escuelaing.arem.socket.SocketHttpClient;
import edu.escuelaing.arem.socket.SocketHttpServer;

public class ServerService {

    /**
     *
     */
    private String resourcePath = "";

    /**
     *
     */
    public void serverStart(){
        SocketHttpServer server = new SocketHttpServer();
        SocketHttpClient client = new SocketHttpClient();
        ServerSocket serverSocket = server.createASocketHttpServer();
        Socket clientSocket = client.createASocketHttpClient(serverSocket);
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line ;
            while((line = inputReader.readLine())!=null){
                if(!inputReader.ready()){
                    break;
                }
                if(line.contains("GET")){
                    this.resourcePath = line.split(" ")[1];
                    this.getResourcePath();
                }
                System.out.println("Received  in the server: " + line);
            }
            if(this.resourcePath!=null){
                ResourcePetition resourcePetition = new ResourcePetition(resourcePath,clientSocket);
            }
            inputReader.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String getResourcePath(){
        return "The resource is: " + this.resourcePath;
    }

}
