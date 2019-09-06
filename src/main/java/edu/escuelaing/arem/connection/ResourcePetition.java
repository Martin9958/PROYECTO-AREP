package edu.escuelaing.arem.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import edu.escuelaing.arem.connection.errorManagement.errorHttpManagement;

public class ResourcePetition {

    private Socket clientSocket;

    public ResourcePetition(String resourcePath, Socket clientSocket){
        this.clientSocket = clientSocket;
        if(resourcePath.toLowerCase().contains(".reflection")){
            reflectionResource(resourcePath, clientSocket);
        }else if(resourcePath.toLowerCase().contains(".css")){
            cssResource(resourcePath, clientSocket);
        }else if(resourcePath.toLowerCase().contains(".jpg") || resourcePath.toLowerCase().contains(".png")
                || resourcePath.toLowerCase().contains(".img") || resourcePath.toLowerCase().contains(".gif")){
            imagesResources(resourcePath, clientSocket);
        }else if(resourcePath.toLowerCase().contains(".html")){
            httpResources(resourcePath, clientSocket);
        }else{
            errorHttpManagement errorHttpManagement = new errorHttpManagement(501, clientSocket);
        }
    }

    private void httpResources(String resourcePath, Socket clientSocket) {
        prepareWebComponent(resourcePath,clientSocket,"html");
    }

    private void imagesResources(String resourcePath, Socket clientSocket) {
        if(resourcePath.toLowerCase().contains(".jpg")){
            prepareImageComponent(resourcePath,clientSocket,"jpg");
        }else if(resourcePath.toLowerCase().contains(".png")){
            prepareImageComponent(resourcePath,clientSocket,"png");
        }else if(resourcePath.toLowerCase().contains(".img")){
            prepareImageComponent(resourcePath,clientSocket,"img");
        }else{
            prepareImageComponent(resourcePath,clientSocket,"gif");
        }
    }

    private void cssResource(String resourcePath, Socket clientSocket) {
        prepareWebComponent(resourcePath,clientSocket,"css");
    }

    private void prepareWebComponent(String resourcePath, Socket clientSocket, String format){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/" + resourcePath));
            StringBuilder outputLine = new StringBuilder();
            outputLine.append("HTTP/1.1 200 OK\r\n");
            outputLine.append("Content-Type: text/"+format+"\n");
            outputLine.append("\r\n\r\n");
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine.append(bfRead);
            }
            PrintWriter out = new PrintWriter(
                    this.clientSocket.getOutputStream(), true);
            out.println(outputLine.toString());
            out.close();

        }catch (IOException ex){
            errorHttpManagement errorHttpManagement = new errorHttpManagement(404, clientSocket);
        }
    }

    private void prepareImageComponent(String resourcePath, Socket clientSocket, String format){
        try {
            File graphicResource= new File(System.getProperty("user.dir"),"src/main/resources/" +resourcePath);
            FileInputStream inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);

            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/"+format+"\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
        } catch (IOException ex){
            errorHttpManagement errorHttpManagement = new errorHttpManagement(404, clientSocket);
        }
    }

    private void reflectionResource(String resourcePath, Socket clientSocket) {
    }

}
