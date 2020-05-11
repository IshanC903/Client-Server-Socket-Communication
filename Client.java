//Ishan Chaurasia - 101011068
//Reece Pillenger - 101013264

import java.net.*;
import java.io.*;

public class Client
{
        public static void main (String[] args)
        {
                String host = args[0];
                int port = Integer.parseInt(args[1]);
                String file = args[2];
                try
                {
                        Socket socket = new Socket(host, port);
                        System.out.println("Client Socket Created");

                        DataInputStream dataIs = new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOs = new DataOutputStream(socket.getOutputStream());

                        dataOs.writeUTF(file);

                        byte[] fileBytes = new byte[4096];
                        FileOutputStream fileOs = new FileOutputStream(file);
                        InputStream inputS = socket.getInputStream();
                        BufferedOutputStream buffOs = new BufferedOutputStream(fileOs);

                        int reader;
                        while((reader = inputS.read(fileBytes)) > 0)
                        {
                                fileOs.write(fileBytes, 0, reader);
                        }

                        buffOs.write(fileBytes, 0, reader);
                        System.out.println(fileBytes);
                        buffOs.flush();

                        System.exit(0);
                        System.out.println("Download Complete.");
                }
                catch (IOException ex)
                {
                        System.out.println(ex);
                }
        }
}
