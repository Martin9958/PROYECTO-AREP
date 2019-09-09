package edu.escuelaing.arem.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.escuelaing.arem.connection.errorManagement.errorHttpManagement;

public class ResourcePetition {

    /**
     *
     */
    private Socket clientSocket;

    /**
     *
     * @param resourcePath
     * @param clientSocket
     */
    public ResourcePetition(String resourcePath, Socket clientSocket){
        this.clientSocket = clientSocket;
        if(resourcePath.toLowerCase().contains("/reflection/")){
            pojoResource(resourcePath,clientSocket);
        }else if(resourcePath.toLowerCase().contains(".css")){
            cssResource(resourcePath, clientSocket);
        }else if(resourcePath.toLowerCase().contains(".jpg") || resourcePath.toLowerCase().contains(".png")
                || resourcePath.toLowerCase().contains(".img") || resourcePath.toLowerCase().contains(".gif")){
            imagesResources(resourcePath, clientSocket);
        }else if(resourcePath.toLowerCase().contains(".html")){
            httpResources(resourcePath, clientSocket);
        }else if(resourcePath.toLowerCase().contains("/pojo/app")){
            pojoResourceHttp(clientSocket);
        }else{
            errorHttpManagement errorHttpManagement = new errorHttpManagement(501, clientSocket);
        }
    }

    private void pojoResourceHttp(Socket clientSocket) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/arep.html"));
            StringBuilder outputLine = new StringBuilder();
            outputLine.append("HTTP/1.1 200 OK\r\n");
            outputLine.append("Content-Type: text/"+"html"+"\n");
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

    /**
     *
     * @param resourcePath
     * @param clientSocket
     */
    private void pojoResource(String resourcePath, Socket clientSocket) {
        try{
            String[] params = resourcePath.split("/");
            Class pokemon  = Class.forName("edu.escuelaing.arem.connection.reflection."+params[2]);
            Boolean parameter = true;
            String response = null;
            String[] split = null;
            preparePOJOComponent(params,clientSocket,pokemon,parameter,response,split);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param resourcePath
     * @param clientSocket
     */
    private void httpResources(String resourcePath, Socket clientSocket) {
        prepareWebComponent(resourcePath,clientSocket,"html");
    }

    /**
     *
     * @param resourcePath
     * @param clientSocket
     */
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

    /**
     *
     * @param resourcePath
     * @param clientSocket
     */
    private void cssResource(String resourcePath, Socket clientSocket) {
        prepareWebComponent(resourcePath,clientSocket,"css");
    }

    /**
     *
     * @param resourcePath
     * @param clientSocket
     * @param format
     */
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

    /**
     *
     * @param resourcePath
     * @param clientSocket
     * @param format
     */
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

    /**
     * 
     * @param param
     * @param clientSocket
     * @param pokemon
     * @param parameter
     * @param response
     * @param split
     */
    private void preparePOJOComponent(String[] param, Socket clientSocket, Class pokemon, Boolean parameter, String response, String[] split){
        try{
            if(param.length==5){
                split = param[4].split("&");
            }else{
                parameter = false;
            }
            ArrayList<Method> metodos = new ArrayList<>(Arrays.asList(pokemon.getMethods()));
            HashMap<String,Method> map = new HashMap<>();
            for(Method m: metodos){
                map.put(m.getName(),m);
            }
            if(parameter){
                response = (String) map.get(param[3]).invoke(pokemon.newInstance(),split);
            }else{
                response = (String) map.get(param[3]).invoke(pokemon.newInstance());
            }
            System.out.println(response);
            PrintStream responseWeb = new PrintStream(clientSocket.getOutputStream());
            responseWeb.println("HTTP/1.1 200 OK\r\n"+"Content-Type: text/html\r\n"+"\r\n");
            if(parameter){
                responseWeb.println(response);
            }else{
                responseWeb.println(response);
            }
            responseWeb.flush();
            responseWeb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
