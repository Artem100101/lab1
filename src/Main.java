import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static String line = "Hello1";

    public static void main(String[] args) {
        try {
            try {
                int a = 0;
                server = new ServerSocket(80);
                System.out.println("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        String word = in.readLine();
                        if (!word.equals("GET / HTTP/1.1")) {
                            line = word;

                        }
                        out.write(line);
                        a++;
                        out.flush();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        in.close();
                        out.close();
                    }
                }
            } finally {
                System.out.println("Сервер закрыт!");
                clientSocket.close();
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
} 