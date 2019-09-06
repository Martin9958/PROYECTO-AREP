package edu.escuelaing.arem.connection;

import java.net.ServerSocket;
import java.net.Socket;

import edu.escuelaing.arem.socket.SocketHttpClient;
import edu.escuelaing.arem.socket.SocketHttpServer;

public class ServerService {

    private SocketHttpServer server = new SocketHttpServer();
    private SocketHttpClient client = new SocketHttpClient();

    public void serverStart(){
        ServerSocket serverSocket = server.createASocketHttpServer();
        Socket clientSocket = client.createASocketHttpClient(serverSocket);

    }

}
