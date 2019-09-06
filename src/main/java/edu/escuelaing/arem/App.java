package edu.escuelaing.arem;

import edu.escuelaing.arem.connection.ServerService;

/**
 *
 *
 */
public class App 
{
    /**
     *
     * @param args
     */
    public static void main( String[] args )
    {
        ServerService serverService = new ServerService();
        while(true){
            serverService.serverStart();
        }
    }
}
