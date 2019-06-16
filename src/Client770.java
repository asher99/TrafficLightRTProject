//package Client770;

//File name Client770.java
//Eiar 770
//Levian Yehonatan
import java.io.*;
import java.net.*;

class Client770		/// !! change to server IP name or address !! //
{
//    String SERVERHOST = "147.161.105.71"; 10.7.9.222

    String SERVERHOST = "localhost";
    int DEFAULT_PORT = 770;
    Socket clientSocket = null;
    BufferedReader bufferSocketIn;
    PrintWriter bufferSocketOut;
    BufferedReader keyBoard;
    ClientWin770 myOutput;
    String line;
    BuildTrafficLight m_junction;
    Event64 tcpQueue;

    public void doit()
    {
        try
        {
            // request to server
            clientSocket = new Socket(SERVERHOST, DEFAULT_PORT);

            // Init streams to read/write text in this socket
            bufferSocketIn = new BufferedReader(
                    new InputStreamReader(
                    clientSocket.getInputStream()));
            bufferSocketOut = new PrintWriter(
                    new BufferedWriter(
                    new OutputStreamWriter(
                    clientSocket.getOutputStream())), true);

            tcpQueue = new Event64();
            m_junction = new BuildTrafficLight(tcpQueue);
            //m_junction.startJunction();



//  	   Init streams to read text from the keyboard
//	   keyBoard = new BufferedReader(
//	   new InputStreamReader(System.in));


          /*  myOutput = new ClientWin770("Client  ", this);

            // notice about the connection
            myOutput.printMe("Connected to " + clientSocket.getInetAddress() +
                    ":" + clientSocket.getPort());*/

           while (true)
            {
                line = bufferSocketIn.readLine(); // reads a line from the server

                if (line.equals("A")){
                    tcpQueue.sendEvent(Phase.PHASE_A);
                }
                else if (line.equals("B")){
                    tcpQueue.sendEvent(Phase.PHASE_B);
                }
                else if (line.equals("C")){
                    tcpQueue.sendEvent(Phase.PHASE_C);
            }
                else if(line.equals("Enter Shabbos")){
                    tcpQueue.sendEvent(Phase.ENTER_SHABBOS);
                }
                else if(line.equals("Enter Weekday")){
                    tcpQueue.sendEvent(Phase.ENTER_WEEKDAY);
                }
                if (line == null)  // connection is closed ?  exit
                {
          //          myOutput.printMe("Connection closed by the Server.");
                    break;
                }
          //      myOutput.printOther(line); // shows it on the screen
                if (line.equals("end"))
                {
                    break;
                }
            }
        } catch (IOException e)
        {
          //  myOutput.printMe(e.toString());
            System.err.println(e);
        } finally
        {
            try
            {
                if (clientSocket != null)
                {
                    clientSocket.close();
                }
            } catch (IOException e2)
            {
            }
        }
      //  myOutput.printMe("end of client ");
      //  myOutput.send.setText("Close");

        System.out.println("end of client ");
    }

    public static void main(String[] args)
    {
        Client770 client = new Client770();
        client.doit();
    }
}
