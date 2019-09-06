package edu.escuelaing.arem.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHttpClient {

    /**
     *
     */
    public SocketHttpClient(){

    }

    /**
     *
     * @param server
     * @return
     */
    public Socket createASocketHttpClient(ServerSocket server){
        Socket client = null;
        try{
            System.out.println("<.........Client ready to connect with the server........>");
            client = server.accept();
        } catch (IOException e) {
            System.out.println("<.........Connection failed with the server........>");
            e.printStackTrace();
        }
        return client;
    }
}
