import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server
{
    public static void main (String[] args)
    {
        int port = 1427;
        try
        {
            ServerSocket server = new ServerSocket(port);
            ExecutorService executor = Executors.newFixedThreadPool(10);
            while(true)
            {
                Socket client = server.accept();
                executor.execute(new MyThread (client));
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
}

class MyThread extends Thread
{
        Socket client;
        MyThread(Socket c)
        {
                client = c;
        }

        public void run()
        {
            int n = 0; //Total Requests.
            int m = 0; //Successful Requests.
            InetAddress inet = client.getInetAddress();
            System.out.println("Client hostname: " + inet.getHostName());
            System.out.println("Client IP: " + inet.getHostAddress());

            try
            {
                DataInputStream dataIs = new DataInputStream(client.getInputStream());
                String inFile = dataIs.readUTF();
                System.out.println("File requested is: " + inFile);

                File sendFile = new File(inFile);

                if(sendFile.exists())
                {
                    n++;
                    m++;
                }
                else
                {
                    n++;
                }
                System.out.println("Total Request: " + n);
                System.out.println("Successful Requests: " + m);

                byte[] fileBytes = new byte[4096];

                FileInputStream fileIs = new FileInputStream(inFile);
                BufferedInputStream buffIs = new BufferedInputStream(fileIs);

                buffIs.read(fileBytes, 0, fileBytes.length);
                client.getOutputStream().write(fileBytes,0,fileBytes.length);



                client.close();
                dataIs.close();
                fileIs.close();
                buffIs.close();
            }
            catch(IOException ex)
            {
                System.out.println(ex);
            }

        }
}
