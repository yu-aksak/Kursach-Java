package Client;

import Client.Windows.EnterWindow;

import java.io.*;
import java.net.*;

public class Client {
    public static String clientMessage;
    public static Socket socket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static boolean connected = false;

    public static void Connect()
    {
        clientMessage = "";

        try {
            socket = new Socket(InetAddress.getLocalHost(), 8000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            connected = true;
        } catch (UnknownHostException var1) {
            connected = false;
            System.err.println("Address not available" + var1);
        } catch (IOException var2) {
            connected = false;
            System.err.println("I/О thread error" + var2);
        }
    }

    public static void Disconnect() {
        try {
            if (in != null) {
                in.close();
            }

            if (out != null) {
                out.close();
            }

            if (socket != null) {
                socket.close();
            }

            connected = false;
        } catch (IOException var1) {
            var1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connect();
        EnterWindow window = new EnterWindow();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}
