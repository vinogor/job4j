package socket;

import com.google.common.base.Joiner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private static final String nextLine = System.getProperty("line.separator");

    private void testServer(String input, String expected) throws IOException {

        Socket socket = mock(Socket.class); // создаём заглушку
        // учим заглушку реагировать
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        new Server(socket).start();

        assertThat(out.toString(), is(expected));
    }

    @Test
    public void startTest1() throws IOException {
        // не очень удобный способ сложить строки
        // String.format("привет%sпока", nextLine)
        this.testServer(
            "пока",
            "выключаем сервер" + nextLine + nextLine
        );
    }

    @Test
    public void startTest2() throws IOException {
        this.testServer(
            Joiner.on(nextLine).join(
                "привет",
                "пока"
            ),
            Joiner.on(nextLine).join(
                "Hello, dear friend, I'm a oracle.",
                "This is loooong msg!",
                "",
                "выключаем сервер",
                "",
                ""
            )
        );
    }

    @Test
    public void startTest3() throws IOException {
        this.testServer(
            Joiner.on(nextLine).join(
                "unsupported",
                "пока"
            ),
            Joiner.on(nextLine).join(
                "ничего не понятно, повторите",
                "",
                "выключаем сервер",
                "",
                ""
            )
        );
    }
}