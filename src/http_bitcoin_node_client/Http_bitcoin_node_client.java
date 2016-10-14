/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http_bitcoin_node_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richard
 */
public class Http_bitcoin_node_client {
    
    //TODO - read in the list of IP addresses from the crawler
    private final static String ip_Array = "http://94.242.209.229/";
    
//    private final static String ip_address = ip_Array;

    public static void main(String[] args) {

//      if (args.length < 1) {
//          System.out.println("Usage : SimpleHttpClient <url>");
//          return;
//      }
        try {
            URL url;
            url = new URL(ip_Array);
            String host = url.getHost();
            String path = url.getPath();
            int port = url.getPort();
            if (port < 80) {
                port = 80;
            }

            //Construct and send the HTTP request
            String request = "GET " + path + " HTTP/1.1\n";
            request += "host: " + host;
            request += "\n\n";

            // Open a TCP connection
            Socket socket = new Socket(host, port);
            // Send the request over the socket
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print(request);
            writer.flush();
            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String next_record = null;
            while ((next_record = reader.readLine()) != null) {
                System.out.println(next_record);
            }
            
            
//            TODO - Save all http requests that contain data to a text file or something
            socket.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Http_bitcoin_node_client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Http_bitcoin_node_client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
