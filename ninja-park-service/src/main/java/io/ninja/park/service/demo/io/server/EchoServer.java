/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author romgzy
 *
 */
public class EchoServer {
    // 端口
    private final int port = 8000;
    private ServerSocket serverSocket ;
    
    public EchoServer() throws IOException{
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }
  
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        new EchoServer ().service();
    }
    
    public String echo(String msg){
        return "echo" + msg;
    }

    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn = socket.getInputStream() ;
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    
    public void service (){
        while (true){
            Socket socket = null;
            try {
              socket = serverSocket.accept() ;  
              System.out.println("New connection accepted" + socket.getInetAddress() + ":" + socket.getPort());
              BufferedReader br = getReader(socket);
              PrintWriter pw = getWriter(socket);
              String msg = null;
              while ((msg = br.readLine() ) != null){
                  System.out.println(msg);
                  pw.println(echo(msg));
                  if(msg.equals("bye")){
                      break;
                  }
              }
            }catch (Exception ex){
                ex.printStackTrace();
            }finally{
                try{
                    if (socket != null){
                        socket.close();
                        System.out.println("close");
                        break;
                    }
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
