//package Server770;

//file name: Server770.java
//Iyar 5770
//Levian Yehonatan
import java.io.*;
import java.net.*;
import java.util.List;

class UseServer70
{
    public static void main(String[] arg)
    {
        new Server770();
    }
}

class Server770 extends Thread 	   //the parallel server
{

   // List<Dialog770> clients;

    int DEFAULT_PORT = 770;
    ServerSocket listenSocket;
    Socket clientSockets;
    ControlFrame gui;

    public Server770()   // constructor of a TCP server
    {
        try
        {
            listenSocket = new ServerSocket(DEFAULT_PORT);
            gui = new ControlFrame();
        } catch (IOException e)    //error
        {
            System.out.println("Problem creating the server-socket");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        System.out.println("Server starts on port " + DEFAULT_PORT);
        start();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                clientSockets = listenSocket.accept();
                System.out.println("client has connected to server ");
               gui.addClient(new Dialog770(clientSockets, this));
            }

        } catch (IOException e)
        {
            System.out.println("Problem listening server-socket");
            System.exit(1);
        }

        System.out.println("end of server");
    }

}

