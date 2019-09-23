package socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private static final String nextLine = System.getProperty("line.separator");

    @Test
    public void startTest1() throws IOException {

        Socket socket = mock(Socket.class);

        // сообщения серверу
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);

        // ответы от сервера
        ByteArrayInputStream in = new ByteArrayInputStream(
            (
                Joiner.on(nextLine).join(
                    "ответ1",
                    "",
                    "ответ2",
                    "",
                    ""
                )
            )
                .getBytes());
        when(socket.getInputStream()).thenReturn(in);

        // ввод в консоль
        ByteArrayInputStream inForConsole = new ByteArrayInputStream(
            (
                Joiner.on(nextLine).join(
                    "привет",
                    "пока",
                    ""
                )
            )
                .getBytes());

        new Client(socket, inForConsole).start();

        // отправили на сервер то, что ввели в консоль
        // плюс уведомление о завершении работы клиента
        assertThat(out.toString(), is
            (
                Joiner.on(nextLine).join(
                    "привет",
                    "пока",
                    "выключение клиента",
                    ""
                )
            )
        );
    }
}