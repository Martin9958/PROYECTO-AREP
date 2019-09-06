package edu.escuelaing.arem.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketHttpServer {

    /**
     *
     */
    public SocketHttpServer() {
    }

    /**
     *
     * @return
     */
    public ServerSocket createASocketHttpServer(){
        ServerSocket server = null;
        try{
            server = new ServerSocket(getPort());
            System.out.println("<-------- Server connection established with the port " + getPort() +"-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }

    /**
     *
     * @return
     */
    public static int getPort(){
        if(System.getenv("PORT")!=null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
