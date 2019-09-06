package edu.escuelaing.arem.connection.errorManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 */
public class errorHttpManagement {

    /**
     *
     */
    private Integer codeStatusError;

    /**
     *
     */
    private Socket client;

    /**
     *
     * @param codeStatusError
     * @param client
     */
    public errorHttpManagement(Integer codeStatusError, Socket client){
        this.codeStatusError = codeStatusError;
        this.client = client;
        createErrorThrow();
    }

    /**
     *
     */
    public void createErrorThrow(){
        if(this.codeStatusError.equals(501)){
            errorCreation(501, "Method Not Implemented");
        }else if(this.codeStatusError.equals(404)){
            errorCreation(404, "Resource Nout found");
        }
    }

    /**
     *
     * @param codeStatusError
     * @param message
     */
    public void errorCreation(Integer codeStatusError, String message){
        PrintWriter out;
        try {
            out = new PrintWriter(this.client.getOutputStream(), true);
            String outputLine = "HTTP/1.1 " + codeStatusError + " "+ message +"\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>"+ codeStatusError + " "+ message +"</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error "+ codeStatusError + ":"+ message +"</h1>"
                    + "</body>"
                    + "</html>";

            out.println(outputLine);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
