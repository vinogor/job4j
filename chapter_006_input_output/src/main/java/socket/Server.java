package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        System.out.println("создаём входные/выходные потоки...");
        InputStream inputStream = this.socket.getInputStream(); // входной поток байтов
        OutputStream outputStream = this.socket.getOutputStream(); // выходной поток байтов

        // символьные потоки
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter out = new PrintWriter(outputStream, true);

        System.out.println("начало работы СЕРВЕРА...");
        System.out.println();

        String ask;

        do {
            System.out.println("ожидаем команды КЛИЕНТА...");
            ask = in.readLine(); // стоим пока что-нибудь не придёт
            System.out.println("получено сообщение: " + ask);

            switch (ask) {
                case "привет":
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println("This is loooong msg!");
                    break;
                case "пока":
                    out.println("выключаем сервер");
                    break;
                case "как дела?":
                    out.println("лучше всех");
                    break;
                case "время":
                    out.println(new Date());
                    break;
                default:
                    out.println("ничего не понятно, повторите");
                    break;
            }
            out.println(); // признак завершения сообщения сервера
        } while (!"пока".equals(ask));
        System.out.println("выключаем сервер");
    }

    public static void main(String[] args) {
        int port = 8888;
        try {
            System.out.println("создаём сервер...");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("ожидаем подключение к серверу...");
            Socket socket = serverSocket.accept();
            System.out.println("подключение получено");
            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}