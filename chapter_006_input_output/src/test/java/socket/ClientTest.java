package socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private static final String nextLine = System.getProperty("line.separator");

    private void startClient(String output, String expected) throws IOException {

        Socket socket = mock(Socket.class); // создаём заглушку
        // учим заглушку реагировать

//        Scanner console = mock(Scanner.class);
//        when(console.nextLine()).thenReturn(nextLine);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(output.getBytes()); // ???
        // изменить станд ввод/вывод ???
        when(socket.getOutputStream()).thenReturn(out);

        ByteArrayInputStream in = new ByteArrayInputStream(expected.getBytes());
        when(socket.getInputStream()).thenReturn(in);

        new Client(socket).start();

        assertThat(in.toString(), is(expected));
    }

    @Test
    public void startTest1() throws IOException {
    }
}