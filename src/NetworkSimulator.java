import java.io.*;
import java.net.*;

public class NetworkSimulator {

    private int sendPort;
    private int receivePort;

    public NetworkSimulator(int sendPort, int receivePort) {
        this.sendPort = sendPort;
        this.receivePort = receivePort;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(receivePort);
        Socket clientSocket = null;
        int value = 0;

        while (value != 100) {
            if (clientSocket == null) {
                clientSocket = serverSocket.accept();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String receivedValue = in.readLine();
            value = Integer.parseInt(receivedValue);
            System.out.println("Received value: " + value);

            if (value == 100) {
                break;
            }

            value++;

            Socket sendSocket = new Socket("localhost", sendPort);
            PrintWriter out = new PrintWriter(sendSocket.getOutputStream(), true);
            out.println(value);
            System.out.println("Sent value: " + value);

            clientSocket = null;
        }

        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java NetworkSimulator <sendPort> <receivePort>");
            System.exit(1);
        }

        int sendPort = Integer.parseInt(args[0]);
        int receivePort = Integer.parseInt(args[1]);
        NetworkSimulator simulator = new NetworkSimulator(sendPort, receivePort);
        simulator.start();
    }
}
