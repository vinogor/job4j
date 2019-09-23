package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;
    private final InputStream inForConsole;

    public Client(Socket socket, InputStream inputStream) {
        this.socket = socket;
        this.inForConsole = inputStream;
    }

    public void start() throws IOException {

        System.out.println("создаём входные/выходные потоки...");
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter out = new PrintWriter(outputStream, true);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

//        InputStream inForConsole = System.in;
        Scanner console = new Scanner(inForConsole);

        System.out.println("начало работы КЛИЕНТА...");
        System.out.println();

        String clientMsg;
        do {
            System.out.println("Введите команду: ");
            clientMsg = console.nextLine();

            out.println(clientMsg);
            String str;

            // позволяент получать сообщения в несколько строк
            // окончание сообщения - пустая строка
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println("пришёл ответ сервера: " + str);
            }
        } while (!"пока".equals(clientMsg));
        out.println("выключение клиента");
        System.out.println("выключение клиента");
    }

    public static void main(String[] args) throws IOException {
        int port = 8888;
        String ip = "127.0.0.1";

        System.out.println("получаем IP адрес из строки...");
        InetAddress inetAddress = InetAddress.getByName(ip);
        System.out.println("подключаемся к серверу...");
        Socket socket = new Socket(inetAddress, port);

        Client client = new Client(socket, System.in);
        client.start();
    }
}