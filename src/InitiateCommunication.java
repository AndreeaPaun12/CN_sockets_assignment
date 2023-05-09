import java.io.*;
import java.net.*;

public class InitiateCommunication {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java InitiateCommunication <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        Socket socket = new Socket("localhost", port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("1");
        System.out.println("Sent initial value: 1");
        socket.close();
    }
}